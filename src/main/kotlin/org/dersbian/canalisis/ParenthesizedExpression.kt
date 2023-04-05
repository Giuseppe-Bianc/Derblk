package org.dersbian.canalisis

class ParenthesizedExpression(
    inline val openParenthesisToken: Token,
    inline val expression: Expression,
    inline val closeParenthesisToken: Token
) : Expression() {
    override val type: TokenType = TokenType.PARENTESIZED_EXPRESSION

    override inline fun getChildren(): List<Node> = listOf(openParenthesisToken, expression, closeParenthesisToken)

}