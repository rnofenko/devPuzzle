package rn.puzzle.search.hard

class ParseLispExpressionSolver {
    fun evaluate(text: String): Int {
        val expression = parse(text)
        return execute(expression)
    }

    private fun execute(expression: IExpression): Int {
        return expression.execute(Env())
    }

    private fun parse(text: String): IExpression {
        if(text.startsWith(addConst)) {
            return parseAdd(text)
        }
        if(text.startsWith(multConst)) {
            return parseMult(text)
        }
        if(text.startsWith(letConst)) {
            return parseLet(text)
        }

        if(text[0].isDigit() || text[0] == '-') {
            val intValue = text.trim().toInt()
            return ConstExpression(intValue)
        }
        return VariableExpression(text)
    }

    private fun parseLet(text: String): IExpression {
        var t = text.substring(letConst.length, text.lastIndex).trim()
        val expressions = ArrayList<IExpression>()
        while (t.isNotEmpty()) {
            val strArg = getFirstTextArgument(t)
            val localExpression = parse(strArg)
            expressions.add(localExpression)

            t = t.substring(strArg.length).trim()
        }
        return LetExpression(expressions)
    }

    private fun parseMult(text: String): IExpression {
        val t = text.substring(multConst.length, text.lastIndex).trim()
        val strArg1 = getFirstTextArgument(t)
        val strArg2 = t.substring(strArg1.length).trim()
        val exp1 = parse(strArg1)
        val exp2 = parse(strArg2)
        return MultExpression(exp1, exp2)
    }

    private fun parseAdd(text: String): IExpression {
        val t = text.substring(addConst.length, text.lastIndex).trim()
        val strArg1 = getFirstTextArgument(t)
        val strArg2 = t.substring(strArg1.length).trim()
        val exp1 = parse(strArg1)
        val exp2 = parse(strArg2)
        return AddExpression(exp1, exp2)
    }

    private fun getFirstTextArgument(text: String): String {
        if(text.startsWith("(")) {
            var openBrackets = 1
            var i = 1
            while (openBrackets > 0) {
                if(text[i] == '(') {
                    openBrackets++
                }
                else if(text[i] == ')') {
                    openBrackets--
                }
                i++
            }
            return text.substring(0, i)
        }
        val spaceIdx = text.indexOf(' ')
        if(spaceIdx == -1) {
            return text
        }
        return text.substring(0, spaceIdx)
    }

    interface IExpression {
        fun execute(env: Env): Int
    }

    class LetExpression(private val args: List<IExpression>): IExpression {
        override fun execute(env: Env): Int {
            var i = 0
            while (i + 1 < args.size) {
                val variableExpression = args[i] as VariableExpression
                val valueExpression = args[i + 1]
                env.update(variableExpression.name, valueExpression.execute(env.clone()))
                i += 2
            }

            return args.last().execute(env)
        }
    }

    class MultExpression(private val arg1: IExpression, private val arg2: IExpression): IExpression {
        override fun execute(env: Env): Int {
            val v1 = arg1.execute(env.clone())
            val v2 = arg2.execute(env.clone())
            return v1 * v2
        }
    }

    class AddExpression(private val arg1: IExpression, private val arg2: IExpression): IExpression {
        override fun execute(env: Env): Int {
            val v1 = arg1.execute(env.clone())
            val v2 = arg2.execute(env.clone())
            return v1 + v2
        }
    }

    class ConstExpression(val value: Int): IExpression {
        override fun execute(env: Env): Int {
            return value
        }
    }

    class VariableExpression(val name: String): IExpression {
        override fun execute(env: Env): Int {
            return env.get(name, 0)
        }
    }

    class Env(val map: HashMap<String, Int> = HashMap()) {

        fun get(name: String, defValue: Int): Int {
            return map[name] ?: defValue
        }

        fun update(name: String, value: Int) {
            map[name] = value
        }

        fun clone(): Env {
            return Env(map.clone() as HashMap<String, Int>)
        }
    }

    private val addConst = "(add "
    private val multConst = "(mult "
    private val letConst = "(let "
}