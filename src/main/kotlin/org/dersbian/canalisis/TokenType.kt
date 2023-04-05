package org.dersbian.canalisis

enum class TokenType(inline val value: CharSequence) {
    //lexer
    NULL(""),
    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"),
    LPAREN("("), RPAREN(")"),
    GREATER_EQUAL(">="), GREATER(">"), LESS_EQUAL("<="), LESS("<"), EQUAL_EQUAL("=="), EQUAL("="), NOT_EQUAL("!="),
    NOT("!"), AND("&"), OR("|"), ANDAND("&&"), OROR("||"),
    NUMBER(""), IDENTIFIER(""), EOF(""), MODULO("%"),

    //Parser
    LITERAL_EXPRESION(""),
    BINARY_EXPRESION(""),
    PARENTESIZED_EXPRESSION("")
}