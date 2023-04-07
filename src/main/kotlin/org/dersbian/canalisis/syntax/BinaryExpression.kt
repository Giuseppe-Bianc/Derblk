package org.dersbian.canalisis.syntax

class BinaryExpression(inline val left: Expression, inline val operator: Token, inline val right: Expression) :
    Expression() {
    override val type: TokenType = TokenType.BINARY_EXPRESION
    override val children: List<Node> = listOf(left, operator, right)
}