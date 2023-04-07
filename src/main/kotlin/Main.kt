import org.dersbian.canalisis.Evaluator
import org.dersbian.canalisis.syntax.Expression
import org.dersbian.canalisis.syntax.SyntaxTree

private const val INPUT =
    "(()( % 3 + 4 * 2 - ( 1 - 5 ) / 2 == != >= % <= < > = & && || % -22 | \r\n 2 + 0x33 \n inter = 077"
private const val INPUTSS = INPUT + INPUT + INPUT + INPUT


fun main() {
    val input = "1 - 5 + 4 + (2 + 3 + (0x33 + 077) / -2)"
    val expression = SyntaxTree.parse(input)
    println("$input = ${Evaluator(expression.root).evaluate()}")
    Expression.prettyPrint(expression.root)
}