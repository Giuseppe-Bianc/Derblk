package org.dersbian.canalisis.syntax

abstract class Node {
    abstract val type: TokenType
    abstract val children: List<Node>
}