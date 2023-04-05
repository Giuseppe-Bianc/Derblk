package org.dersbian.canalisis

import java.math.BigInteger
import java.util.regex.Pattern

class Lexer(inline val input: CharSequence) {

    var currentPosition = 0
    var currentLine = 1
    val length: Int = input.length
    val indices: IntRange = input.indices
    inline val currentChar: Char
        inline get() = input.getOrNull(currentPosition) ?: ' '
    inline val currentCharLower: Char
        inline get() = currentChar.lowercaseChar()
    inline val nextChar: Char
        inline get() = input.getOrNull(currentPosition + 1) ?: ' '
    inline val nextCharLower: Char
        inline get() = nextChar.lowercaseChar()

    companion object {
        val isHEXisOCT = booleanArrayOf(false, false)
        val numBuilder = StringBuilder()

        val HEX_VALID_CHARS = setOf(
            ZR, UN, DU, TR, QU, CI, SE, ST, OT, NO, A, B, C, D, E, F,
            A.lowercaseChar(),
            B.lowercaseChar(),
            C.lowercaseChar(),
            D.lowercaseChar(),
            E.lowercaseChar(),
            F.lowercaseChar()
        )
        val OCTAL_VALID_CHARS = setOf(ZR, UN, DU, TR, QU, CI, SE, ST)
        val IDENTIFIER_REGEX = RegexCache.getOrPut("\\w+").toRegex()


        object RegexCache {
            val cache = mutableMapOf<String, Pattern>()

            inline fun getOrPut(pattern: String): Pattern {
                return cache.getOrPut(pattern) { Pattern.compile(pattern) }
            }
        }

        fun resetParseNum() {
            numBuilder.setLength(0)
            isHEXisOCT[0] = false
            isHEXisOCT[1] = false
        }

        inline fun textToBigInt(text: String): BigInteger {
            return when {
                isHEXisOCT[0] -> text.toBigInteger(16)
                isHEXisOCT[1] -> text.toBigInteger(8)
                else -> text.toBigInteger()
            }
        }
    }

    /**
     * It takes a sequence of tokens, and if the last token is a minus sign and the next token is a
     * number, it combines them into a single token
     *
     * @return A list of tokens.
     */
    inline fun lex(): List<Token> = generateSequence { getNextToken() }.takeWhile { it.type != TokenType.EOF }.toList()

    /**
     * It's a function that returns a token, and it does so by incrementing the current position and
     * returning a token based on the current character
     *
     * @return A token.
     */
    inline fun getNextToken(): Token {
        while (currentPosition < length) {
            when {
                currentChar.isWhitespace() -> skipWhitespace()
                currentChar.isDigit() -> return parseNumber()
                currentChar.isLetter() -> return parseIdentifier()
                currentChar == '+' -> {
                    currentPosition++
                    return Token(TokenType.PLUS, Position(currentPosition - 1, currentLine))
                }

                currentChar == '%' -> {
                    currentPosition++
                    return Token(TokenType.MODULO, Position(currentPosition - 1, currentLine))
                }

                currentChar == '-' -> {
                    currentPosition++
                    return Token(TokenType.MINUS, Position(currentPosition - 1, currentLine))
                }

                currentChar == '*' -> {
                    currentPosition++
                    return Token(TokenType.MULTIPLY, Position(currentPosition - 1, currentLine))
                }

                currentChar == '/' -> {
                    currentPosition++
                    return Token(TokenType.DIVIDE, Position(currentPosition - 1, currentLine))
                }

                currentChar == '(' -> {
                    currentPosition++
                    return Token(TokenType.LPAREN, Position(currentPosition - 1, currentLine))
                }

                currentChar == ')' -> {
                    currentPosition++
                    return Token(TokenType.RPAREN, Position(currentPosition - 1, currentLine))
                }

                currentChar == '>' -> return returnTokenOrOther(TokenType.GREATER_EQUAL, TokenType.GREATER)

                currentChar == '<' -> return returnTokenOrOther(TokenType.LESS_EQUAL, TokenType.LESS)

                currentChar == '=' -> return returnTokenOrOther(TokenType.EQUAL_EQUAL, TokenType.EQUAL)

                currentChar == '!' -> return returnTokenOrOther(TokenType.NOT_EQUAL, TokenType.NOT)

                currentChar == '&' -> return returnTokenOrOther('&', TokenType.ANDAND, TokenType.AND)

                currentChar == '|' -> return returnTokenOrOther('|', TokenType.OROR, TokenType.OR)

                else -> {
                    currentPosition++
                    return Token(TokenType.NULL, Position(currentPosition - 1, currentLine))
                }
            }
        }
        return Token(TokenType.EOF, Position(currentPosition, currentPosition, currentLine))
    }

    /**
     * If the current character is equal to the next character, return a token of type `type` with a
     * position of the current character and the next character. Otherwise, return a token of type
     * `other` with a position of the current character
     *
     * @param nextC The character that should be next in the stream.
     * @param type The type of the token that we want to return if the next character is the one we're
     * looking for.
     * @param other TokenType - The token type to return if the next character is not the one specified
     * @return A token.
     */
    inline fun returnTokenOrOther(nextC: Char, type: TokenType, other: TokenType): Token {
        currentPosition++
        return if (currentChar == nextC) {
            currentPosition++
            Token(type, Position(currentPosition - 2, currentPosition, currentLine))
        } else Token(other, Position(currentPosition - 1, currentLine))
    }

    inline fun returnTokenOrOther(type: TokenType, other: TokenType): Token {
        currentPosition++
        return if (currentChar == '=') {
            currentPosition++
            Token(type, Position(currentPosition - 2, currentPosition, currentLine))
        } else Token(other, Position(currentPosition - 1, currentLine))
    }

    /**
     * It increments the current position until it finds a non-whitespace character
     */
    inline fun skipWhitespace() {
        while (currentPosition in input.indices && currentChar.isWhitespace()) {
            if (currentChar == '\n') {
                currentLine++
            }
            currentPosition++
        }
    }

    /**
     * It parses a number from the input string and returns a token with the number as its value.
     *
     * @return A Token object.
     */
    inline fun parseNumber(): Token {
        resetParseNum()
        val startIndex = currentPosition

        when (currentChar) {
            ZR -> when {
                currentPosition in indices && (nextCharLower == 'x') -> {
                    currentPosition += 2
                    isHEXisOCT[0] = true
                    while (currentPosition in indices && currentChar in HEX_VALID_CHARS) {
                        numBuilder.append(currentChar)
                        currentPosition++
                    }
                }

                else -> {
                    isHEXisOCT[1] = true
                    while (currentPosition in indices && currentChar in OCTAL_VALID_CHARS) {
                        numBuilder.append(currentChar)
                        currentPosition++
                    }
                }
            }

            else -> {
                var dotFound = false
                for (i in currentPosition until length) {
                    val ch = input[i]
                    if (ch == '.') {
                        if (dotFound) break else dotFound = true
                    } else if (!ch.isDigit()) {
                        break
                    }
                    numBuilder.append(ch)
                    currentPosition++
                }
            }
        }
        val text = numBuilder.toString()
        return Token(TokenType.NUMBER, text, Position(startIndex, currentPosition, currentLine), textToBigInt(text))
    }

    /**
     * It takes a string, and returns a token
     *
     * @return A token object.
     */
    inline fun parseIdentifier(): Token {
        val matchResult: MatchResult = IDENTIFIER_REGEX.find(input, currentPosition)!!
        val identifier = matchResult.value
        currentPosition += identifier.length
        return Token(
            TokenType.IDENTIFIER,
            identifier,
            Position(matchResult.range.first, matchResult.range.last + 1, currentLine),
            null
        )
    }
}