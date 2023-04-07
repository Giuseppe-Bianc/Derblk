package org.dersbian.canalisis.syntax

class LiteralExpression(inline val literal: Token) : Expression() {
    override val type: TokenType = TokenType.LITERAL_EXPRESION
    override val children: List<Node> = listOf(literal)
}