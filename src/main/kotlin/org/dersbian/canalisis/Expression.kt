package org.dersbian.canalisis

abstract class Expression : Node() {
    companion object {
        fun prettyPrint(node: Node, indent: String = "", isLast: Boolean = false, isFirst: Boolean = true) {
            printColoredText(
                "$indent${if (isFirst) node.type else if (isLast) "└──${node.type}" else "├──${node.type}"}" +
                        if (node is Token && node.value != null) " ${node.value}" else "",
                "gray"
            )
            val newIndent = if (!isFirst) indent + if (isLast) "   " else "│  " else ""
            val lastChild = node.getChildren().lastOrNull()
            node.getChildren().forEach { prettyPrint(it, newIndent, it == lastChild, false) }
        }

        fun printColoredText(message: String, color: String = "white") {
            val reset = "\u001B[0m"
            val colorCode = when (color) {
                "black" -> "\u001B[30m"
                "dark_red" -> "\u001B[31m"
                "dark_green" -> "\u001B[32m"
                "dark_yellow" -> "\u001B[33m"
                "dark_blue" -> "\u001B[34m"
                "dark_purple" -> "\u001B[35m"
                "dark_cyan" -> "\u001B[36m"
                "gray" -> "\u001B[37m"
                "red" -> "\u001B[91m"
                "green" -> "\u001B[92m"
                "yellow" -> "\u001B[93m"
                "blue" -> "\u001B[94m"
                "purple" -> "\u001B[95m"
                "cyan" -> "\u001B[96m"
                "white" -> "\u001B[97m"
                else -> ""
            }
            println("${colorCode}${message}${reset}")
        }
    }
}
