package org.dersbian.canalisis

abstract class Node {
    abstract val type: TokenType
    abstract val children: List<Node>
}