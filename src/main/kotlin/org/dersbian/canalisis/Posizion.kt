package org.dersbian.canalisis

data class Position(
    inline val startPosition: Int,
    inline val endPosition: Int,
    inline val startLine: Int,
    inline val endLine: Int
) {
    init {
        require(startPosition >= 0) { " startPosition must be >= 0" }
        require(endPosition >= startPosition) { " endPosition must be >= startPosition" }
        require(startLine > 0) { " startLine must be > 0" }
        require(endLine >= startLine) { " endLine must be >= startLine" }
    }

    constructor(startPosition: Int, endPosition: Int, startLine: Int) : this(
        startPosition,
        endPosition,
        startLine,
        startLine
    )

    constructor(startPosition: Int, startLine: Int) : this(
        startPosition,
        startPosition + 1,
        startLine,
        startLine
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false

        if (startPosition != other.startPosition) return false
        if (endPosition != other.endPosition) return false
        if (startLine != other.startLine) return false
        return endLine == other.endLine
    }

    override fun hashCode(): Int {
        var result = startPosition
        result = 31 * result + endPosition
        result = 31 * result + startLine
        result = 31 * result + endLine
        return result
    }

    override fun toString(): String {
        return "startPosition: $startPosition, endPosition: $endPosition, startLine: $startLine, endLine: $endLine"
    }
}
