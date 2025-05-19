import java.util.Stack

fun Char.isFloatDigit() = isDigit() || this == '.'

fun examinationPush (prev: Char?, ch:Char) = (prev==null)
    || (prev.isLetter() && ch.isLetter())
    || (prev.isFloatDigit() && ch.isFloatDigit())
    || (prev.isLetter() && ch.isFloatDigit())


fun String.splitMath(): List<String>{
    val result = mutableListOf<String>()
    var prev:Char?=null
    return result.run{
        add(this@splitMath.fold(""){ acc, ch ->
            if (prev == null || !examinationPush(prev,ch)){
                prev=ch
                result.add(acc)
                "" + ch
            }else{
                acc + ch
            }
        })
        filter{it.isNotBlank()}
    }
}

val String.priority get()=when(this){
    ")","]","}",">" -> -1
    "(","[","{","<" -> 0
    "+","-" -> 1
    "*","/","%" -> 2
    "~" -> 3
    "sin","cos","tg","ctg","ln","lg"->4
    "^"->5
    "!"->6
    else -> Int.MIN_VALUE
}

fun toRpn(expr: List<String>): List<String>{
    val result = mutableListOf<String>()
    val opsOperators = Stack<String>()
    expr.forEach {
        if(it.priority == Int.MIN_VALUE) result.add(it)
        else {
            val el = if(it == "-"
                && (opsOperators.isEmpty()
                        && result.isEmpty()
                        || opsOperators.peek().priority == 0)
                ) "~"
            else it
            if(el.priority == 0 || opsOperators.isEmpty() || el.priority >= opsOperators.peek().priority){
                opsOperators.push(el)
            }
            else{
                while (opsOperators.isNotEmpty() && opsOperators.peek().priority > el.priority){
                    opsOperators.pop().also { op ->
                        if(op.priority > 0) result.add(op)
                    }
                }
                if(el.priority > 0){
                    opsOperators.push(el)
                }
            }
        }
    }
    while(opsOperators.size > 0){
        result.add(opsOperators.pop())
    }
    return result
}

fun main(){
    println("2.5x^2+sin(x) * x2y".splitMath())
    println(toRpn("2+2*2".splitMath()))
    println(toRpn("(~3+2^4)/(2^5-1)".splitMath()))
    println(toRpn("(-3+2!)/(2^5-1)".splitMath()))
    println(toRpn("(-4!+7)".splitMath()))
}