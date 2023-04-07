package org.dersbian.canalisis

import kotlin.random.Random

abstract class Expression : Node() {
    companion object {
        fun prettyPrint(node: Node, indent: String = "", isLast: Boolean = true) {
            printColoredText(
                "$indent${if (isLast) "└──" else "├──"}",
                "${node.type}${(node as? Token)?.value?.let { " $it $node" } ?: ""}",
                "gray",
                randomColor())
            val newIndent = if (isLast) "$indent    " else "$indent│   "
            val lastChild = node.children.lastOrNull()
            node.children.forEach { prettyPrint(it, newIndent, it == lastChild) }
        }

        fun printColoredText(message: String, message2: String, color: String = "white", color2: String = "white") {
            val reset = "\u001B[0m"
            val colorCode = colorCode(color)
            val colorCode2 = colorCode(color2)
            println("$colorCode$message$colorCode2$message2$reset")
        }

        inline fun colorCode(color: String) = when (color) {
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

        fun randomColor(): String {
            val colors = listOf(
                "dark_red",
                "dark_green",
                "dark_yellow",
                "dark_blue",
                "dark_purple",
                "dark_cyan",
                "red",
                "green",
                "yellow",
                "blue",
                "purple"
            )
            return colors[Random.nextInt(colors.size)]
        }
    }
}