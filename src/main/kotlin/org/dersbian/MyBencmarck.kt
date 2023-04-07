package org.dersbian
import org.dersbian.canalisis.Lexer
import org.dersbian.canalisis.SyntaxTree
import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.SampleTime, Mode.AverageTime, Mode.Throughput)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
class MyBencmarck {
    private val INPUT =
        "(()( % 3 + 4 * 2 - ( 1 - 5 ) / 2 == != >= % <= < > = & && || % -22 | \r\n 2 + 0x33 \n inter = 077"
    private val INPUTSS = INPUT + INPUT + INPUT + INPUT
    private var lexer: Lexer = Lexer("")

    /* Setting up the lexer with the input string. */
    @Setup
    fun setUp() {
        lexer = Lexer(INPUTSS)
    }

    /* A benchmark function. */
    @Benchmark
    final fun benchmarkLexer() {
        lexer.lex()
    }

    /* A benchmark function. */
    @Benchmark
    final fun benchmarkParser() {
        SyntaxTree.parse("1 - 5 + 4 + (2 + 3 + (0x33 + 077) / 2)")
    }
}