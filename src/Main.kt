import kotlin.math.pow

fun main() {

    //Задача 1
    val solution1Printer = DecisionPrinter(DecimalToBinaryConverter())
    solution1Printer.printAnswer(1, "110011")

    //Задача 2
    val solution2Printer = DecisionPrinter(BinaryToDecimalConverter())
    solution2Printer.printAnswer(2, "51")

    //Задача 3
    val solution3Printer = DecisionPrinter(StringToPalindromeTester())
    solution3Printer.printAnswer(3, "Тест")
}


class BinaryToDecimalConverter : Solver {

    override fun getSolution(variable: String): String {
        var decimalNumber = variable.toInt()
        var result = ""
        while (decimalNumber > 0) {
            result += decimalNumber % 2
            decimalNumber /= 2
        }
        return result.reversed()
    }
}

class DecimalToBinaryConverter : Solver {

    override fun getSolution(variable: String): String {
        var digitsArray = arrayOf<Int>()
        variable.reversed().forEach { digitsArray += it.toString().toInt() }
        var result = 0
        for ((degree, n) in digitsArray.withIndex()) {
            result += (n * 2.0.pow(degree)).toInt()
        }
        return result.toString()
    }
}

class StringToPalindromeTester : Solver {

    override fun getSolution(variable: String): String {
        return if (variable.reversed().lowercase() == variable.lowercase()) {
            "Строка - палиндром"
        } else "Строка не палиндром"
    }
}

interface Solver {
    fun getSolution(variable: String): String
}

class DecisionPrinter(private val solver: Solver) : Solver by solver {
    fun printAnswer(taskNumber: Int, variable: String) {
        println("Ответ к задаче $taskNumber: ${solver.getSolution(variable)}")
    }
}