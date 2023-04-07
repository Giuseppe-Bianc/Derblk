package org.dersbian.canalisis

class Parser(inline val text: String) {
    val tokens: List<Token>
    val _diagnostics: MutableList<String> = mutableListOf()
    var position = 0

    init {
        val lexer = Lexer(text)
        tokens = lexer.lex()
        _diagnostics.addAll(lexer.diagnostics)
    }

    inline val diagnostics: List<String>
        inline get() = _diagnostics

    inline val current: Token
        inline get() = peek(0)

    inline val nextToken: Token
        inline get() {
            val current = current
            position++
            return current
        }

    inline fun peek(offset: Int): Token {
        val index = position + offset
        return if (index >= tokens.size) {
            tokens[tokens.size - 1]
        } else tokens[index]
    }


    inline fun matchToken(type: TokenType): Token {
        if (current.type === type) {
            return nextToken
        }

        _diagnostics.add("ERROR: Unexpected token <${current.type}>, expected <$type>")
        return Token(type, current.position, current.line)
    }

    inline fun parse(): SyntaxTree {
        val expression = parseExpression()
        val endOfFileToken = matchToken(TokenType.EOF)
        return SyntaxTree(_diagnostics, expression, endOfFileToken)
    }


    fun parseExpression(parentPrecedence: Int = 0): Expression {
        var left: Expression = parsePrimaryExpression()
        while (true) {
            val precedence = current.type.precedence
            if (precedence == 0 || precedence <= parentPrecedence) break
            val operatorToken = nextToken
            val right = parseExpression(precedence)
            left = BinaryExpression(left, operatorToken, right)
        }

        return left
    }

    inline fun parsePrimaryExpression(): Expression {
        return if (current.type === TokenType.LPAREN) {
            val left = nextToken
            val expression = parseExpression()
            val right = matchToken(TokenType.RPAREN)
            ParenthesizedExpression(left, expression, right)
        } else {
            val numberToken = matchToken(TokenType.NUMBER)
            LiteralExpression(numberToken)
        }
    }
}