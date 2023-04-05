package org.dersbian.canalisis

abstract class Node {
    abstract val type: TokenType
    abstract fun getChildren(): List<Node>
}