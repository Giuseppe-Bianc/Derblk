package org.dersbian.canalisis

class LiteralExpression(inline val literal: Token) : Expression() {
    override val type: TokenType = TokenType.LITERAL_EXPRESION
    override inline fun getChildren(): List<Node> = listOf(literal)
}