package org.dersbian.canalisis

enum class TokenType(
    inline val value: CharSequence = "",
    inline val precedence: Int = 0,
    inline val una_precedence /*unary precedence*/: Int = 0
) {
    //lexer
    NULL,
    PLUS("+", 1),
    MINUS("-", 1),
    MULTIPLY("*", 2),
    DIVIDE("/", 2),
    LPAREN("("),
    RPAREN(")"),
    GREATER_EQUAL(">="),
    GREATER(">"),
    LESS_EQUAL("<="),
    LESS("<"),
    EQUAL_EQUAL("=="),
    EQUAL("="),
    NOT_EQUAL("!="),
    NOT("!"),
    AND("&"),
    OR("|"),
    AND_AND("&&"),
    OR_OR("||"),
    MODULO("%"),
    NUMBER, IDENTIFIER, EOF,

    //Parser
    LITERAL_EXPRESION,
    BINARY_EXPRESION,
    PARENTESIZED_EXPRESSION
}

inline fun TokenType.getBinOperatorPecedence(): Int = when (this) {
    TokenType.MULTIPLY, TokenType.DIVIDE -> 2
    TokenType.PLUS, TokenType.MINUS -> 1
    else -> 0
}