package org.dersbian.canalisis.syntax

class UnaryExpression(inline val operator: Token, inline val operand: Expression) :
    Expression() {
    override val type: TokenType = TokenType.UNARY_EXPRESION
    override val children: List<Node> = listOf(operator, operand)
}