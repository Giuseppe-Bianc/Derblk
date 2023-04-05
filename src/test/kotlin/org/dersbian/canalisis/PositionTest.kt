package org.dersbian.canalisis

import org.dersbian.canalisis.Position
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PositionTest {
    var position = Position(1, 1)

    @BeforeEach
    fun setUp() {
        position = Position(1, 3, 1)
    }

    @Test
    fun testEquals() {
        Assertions.assertEquals(Position(1, 3, 1, 1), position)
    }

    @Test
    fun testToString() {
        Assertions.assertEquals("startPosition: 1, endPosition: 3, startLine: 1, endLine: 1", position.toString())
    }

    @Test
    fun getStartPosition() {
        Assertions.assertEquals(1, position.startPosition)
    }

    @Test
    fun getEndPosition() {
        Assertions.assertEquals(3, position.endPosition)
    }

    @Test
    fun getStartLine() {
        Assertions.assertEquals(1, position.startLine)
    }

    @Test
    fun getEndLine() {
        Assertions.assertEquals(1, position.endLine)
    }
}