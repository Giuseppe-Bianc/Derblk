package org.dersbian.canalisis

class Evaluator(inline val root: Expression) {
    inline fun evaluate(): Int = evaluateExpression(root)

    fun evaluateExpression(node: Expression): Int {
        return when (node) {
            is LiteralExpression -> node.literal.value as Int
            is BinaryExpression -> {
                val left = evaluateExpression(node.left)
                val right = evaluateExpression(node.right)

                when (node.operator.type) {
                    TokenType.PLUS -> left + right
                    TokenType.MINUS -> left - right
                    TokenType.MULTIPLY -> left * right
                    TokenType.DIVIDE -> left / right
                    else -> throw Exception("Unexpected binary operator ${node.operator.type}")
                }
            }

            is ParenthesizedExpression -> evaluateExpression(node.expression)
            else -> throw Exception("Unexpected node ${node.type}")
        }
    }
}