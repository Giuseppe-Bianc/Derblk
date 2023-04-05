package org.dersbian.canalisis

class SyntaxTree(inline val diagnostics: List<String>, inline val root: Expression, inline val endOfFileToken: Token) {
    companion object {
        inline fun parse(text: String): SyntaxTree = Parser(text).parse()
    }
}