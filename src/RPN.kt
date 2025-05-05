enum class CharType{LETTER,DIGIT,OTHER,SPACE}
fun String.splitMath(): List<String>{
    val result = mutableListOf<String>()
    var prevCharType: CharType? = null
    fold(""){acc, ch ->
        val charType = when{
            ch.isLetter() -> CharType.LETTER
            ch.isDigit() -> CharType.DIGIT
            ch.isWhitespace() -> CharType.SPACE
            else -> CharType.OTHER
        }
        if (/*добавить символ в строку*/ ){
            acc + ch
        }else{
            result.add(acc)
            "" + ch
        }

    }
    return result
}
fun main(){
    println("2x^2+sin(x) * x2".splitMath())
    // ["2","x","^","2","+","sin","(","x",")","*","x2"]
//    println(listOf(3, 2, 5, 4, 7).fold(0){ acc, value -> if (value % 2 == 0) acc - value else acc + value })
}