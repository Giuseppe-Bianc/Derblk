package org.dersbian.canalisis

class Parser(inline val text: String) {
    val tokens: List<Token> = Lexer(text).lex()
    val _diagnostics: MutableList<String> = mutableListOf()
    var position = 0

    inline val diagnostics: List<String>
        inline get() = _diagnostics

    inline fun peek(offset: Int): Token {
        val index = position + offset
        return if (index >= tokens.size) {
            tokens[tokens.size - 1]
        } else tokens[index]
    }

    inline val current: Token
        inline get() = peek(0)

    inline fun nextToken(): Token {
        val current = current
        position++
        return current
    }

    inline fun matchToken(type: TokenType): Token {
        if (current.type === type) {
            return nextToken()
        }

        _diagnostics.add("ERROR: Unexpected token <${current.type}>, expected <$type>")
        return Token(type, current.position, null)
    }

    inline fun parse(): SyntaxTree {
        val expression = parseExpression()
        val endOfFileToken = matchToken(TokenType.EOF)
        return SyntaxTree(_diagnostics, expression, endOfFileToken)
    }

    inline fun parseExpression(): Expression = parseTerm()

    fun parseTerm(): Expression {
        var left = parseFactor()

        while (current.type === TokenType.PLUS ||
            current.type === TokenType.MINUS
        ) {
            val operatorToken = nextToken()
            val right = parseFactor()
            left = BinaryExpression(left, operatorToken, right)
        }

        return left
    }

    inline fun parseFactor(): Expression {
        var left = parsePrimaryExpression()

        while (current.type === TokenType.MULTIPLY ||
            current.type === TokenType.DIVIDE
        ) {
            val operatorToken = nextToken()
            val right = parsePrimaryExpression()
            left = BinaryExpression(left, operatorToken, right)
        }

        return left
    }

    inline fun parsePrimaryExpression(): Expression {
        return if (current.type === TokenType.LPAREN) {
            val left = nextToken()
            val expression = parseExpression()
            val right = matchToken(TokenType.RPAREN)
            ParenthesizedExpression(left, expression, right)
        } else {
            val numberToken = matchToken(TokenType.NUMBER)
            LiteralExpression(numberToken)
        }
    }
}