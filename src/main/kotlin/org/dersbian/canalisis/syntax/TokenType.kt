package org.dersbian.canalisis.syntax

enum class TokenType(
    inline val value: CharSequence = "",
    inline val precedence: Int = 0,
    inline val unaryPrecedence: Int = 0
) {
    //lexer
    NULL,
    PLUS("+", 1, 3),
    MINUS("-", 1, 3),
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
    PARENTESIZED_EXPRESSION,
    UNARY_EXPRESION
}