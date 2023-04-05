package org.dersbian.canalisis

class BinaryExpression(inline val left: Expression, inline val operator: Token, inline val right: Expression) :
    Expression() {
    override val type: TokenType = TokenType.BINARY_EXPRESION
    override inline fun getChildren(): List<Node> = listOf(left, operator, right)
}