package org.dersbian.canalisis

//costanti
const val A = 'a'
const val B = 'b'
const val C = 'c'
const val D = 'd'
const val E = 'e'
const val F = 'f'
const val ZR = '0'
const val UN = '1'
const val DU = '2'
const val TR = '3'
const val QU = '4'
const val CI = '5'
const val SE = '6'
const val ST = '7'
const val OT = '8'
const val NO = '9'

/**
 * A token is a type, value, and position.
 * @property {TokenType} type - The type of the token.
 * @property {CharSequence} value - The actual text of the token.
 * @property {Position} position - The position of the token in the source code.
 */
data class Token(
    override inline val type: TokenType,
    inline val text: CharSequence,
    inline val position: Position,
    inline val value: Any?
) : Node() {

    constructor(type: TokenType, position: Position, value: Any?) : this(type, type.value, position, value)

    constructor(type: TokenType, position: Position) : this(type, type.value, position, null)
    override inline fun getChildren(): List<Node> = emptyList()

    override fun toString(): String =
        "Token(type=$type, text='$text', position=$position${if (value == null) " )" else ", value=$value)"}"
}