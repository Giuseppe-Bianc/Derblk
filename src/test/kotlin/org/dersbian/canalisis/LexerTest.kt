package org.dersbian.canalisis

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LexerTest{
    @Test
    fun testAssignmentExpression() {
        val lexer = Lexer("x = 5 + 3")
        val tokens = lexer.lex()
        Assertions.assertEquals(tokens[0].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[0].text, "x")
        Assertions.assertEquals(tokens[1].type, TokenType.EQUAL)
        Assertions.assertEquals(tokens[2].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[2].text, "5")
        Assertions.assertEquals(tokens[3].type, TokenType.PLUS)
        Assertions.assertEquals(tokens[4].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[4].text, "3")
    }
    @Test
    fun testArithmeticExpressions() {
        val lexer = Lexer("1 + 2 * 3 - 4 / 2")
        val tokens = lexer.lex()
        Assertions.assertEquals(tokens[0].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[0].text, "1")
        Assertions.assertEquals(tokens[1].type, TokenType.PLUS)
        Assertions.assertEquals(tokens[2].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[2].text, "2")
        Assertions.assertEquals(tokens[3].type, TokenType.MULTIPLY)
        Assertions.assertEquals(tokens[4].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[4].text, "3")
        Assertions.assertEquals(tokens[5].type, TokenType.MINUS)
        Assertions.assertEquals(tokens[6].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[6].text, "4")
        Assertions.assertEquals(tokens[7].type, TokenType.DIVIDE)
        Assertions.assertEquals(tokens[8].type, TokenType.NUMBER)
        Assertions.assertEquals(tokens[8].text, "2")
    }

    @Test
    fun testLogicalExpressions() {
        val lexer = Lexer("a && b || !c")
        val tokens = lexer.lex()
        Assertions.assertEquals(tokens[0].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[0].text, "a")
        Assertions.assertEquals(tokens[1].type, TokenType.ANDAND)
        Assertions.assertEquals(tokens[2].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[2].text, "b")
        Assertions.assertEquals(tokens[3].type, TokenType.OROR)
        Assertions.assertEquals(tokens[4].type, TokenType.NOT)
        Assertions.assertEquals(tokens[5].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[5].text, "c")
    }

    @Test
    fun testComparisonExpressions() {
        val lexer = Lexer("x == y != z <= w >= v < u > t")
        val tokens = lexer.lex()
        Assertions.assertEquals(tokens[0].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[0].text, "x")
        Assertions.assertEquals(tokens[1].type, TokenType.EQUAL_EQUAL)
        Assertions.assertEquals(tokens[2].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[2].text, "y")
        Assertions.assertEquals(tokens[3].type, TokenType.NOT_EQUAL)
        Assertions.assertEquals(tokens[4].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[4].text, "z")
        Assertions.assertEquals(tokens[5].type, TokenType.LESS_EQUAL)
        Assertions.assertEquals(tokens[6].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[6].text, "w")
        Assertions.assertEquals(tokens[7].type, TokenType.GREATER_EQUAL)
        Assertions.assertEquals(tokens[8].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[8].text, "v")
        Assertions.assertEquals(tokens[9].type, TokenType.LESS)
        Assertions.assertEquals(tokens[10].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[10].text, "u")
        Assertions.assertEquals(tokens[11].type, TokenType.GREATER)
        Assertions.assertEquals(tokens[12].type, TokenType.IDENTIFIER)
        Assertions.assertEquals(tokens[12].text, "t")
    }

    @Test
    fun testUnsupportedCharacters() {
        val input = "#$^"

        val lexer = Lexer(input)
        val tokens = lexer.lex()

        Assertions.assertEquals(tokens[0].type, TokenType.NULL)
        Assertions.assertEquals(tokens[1].type, TokenType.NULL)
        Assertions.assertEquals(tokens[2].type, TokenType.NULL)
    }

    @Test
    fun inputInsufficiente() {

        val lexer = Lexer("")
        val tokens = lexer.lex()
        Assertions.assertEquals(0, lexer.length)
    }
}