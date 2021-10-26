import com.hadaralex.kepper.XcelSheet
import kepper.Kepper

@XcelSheet(sheetName = "foo")
class Foo(
    val id: String,
)

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello from main!")
        Kepper.sayHello()
    }
}