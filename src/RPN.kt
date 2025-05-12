
fun examinationPush (prev: Char?, ch:Char) = (prev==null)
    || (prev.isLetter() && ch.isLetter())
    || (prev.isDigit() && ch.isDigit())
    || (prev.isLetter() && ch.isDigit())


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
fun main(){
    println("2x^2+sin(x) * x2y".splitMath())
    // ["2","x","^","2","+","sin","(","x",")","*","x2"]
//    println(listOf(3, 2, 5, 4, 7).fold(0){ acc, value -> if (value % 2 == 0) acc - value else acc + value })
    println("2+2*2".splitMath())

}