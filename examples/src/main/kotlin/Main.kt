import com.hadaralex.kepper.XcelSheet
import com.kepper.adapters.generated.Item_SheetAdapter
import com.kepper.adapters.model.RowReadResult
import com.kepper.commons.model.KepperCell
import com.kepper.sheets.AbsolutFilePath
import com.kepper.sheets.FileType
import com.kepper.sheets.KepperFileReader
import kotlinx.coroutines.runBlocking

@XcelSheet(sheetName = "foo")
class Foo(
    val id: String,
)

internal object Main {
    private const val SHEET_PATH: String = "data/products.xlsx"

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = KepperFileReader {
            path = AbsolutFilePath(SHEET_PATH)
            type = FileType.MICROSOFT
        }

        runBlocking {
            val list = reader.loadFile().pages()
            val firstPage = list.first()
            println("list size: ${list.size}")

            println("Headers: ${firstPage.header}")
            val headerSize = firstPage.header.size
            println("Header size: $headerSize")
            val adapter = Item_SheetAdapter()
            val items = adapter.readSheet(firstPage)
            for ((index, item) in items.withIndex()) {
                when (item) {
                    is RowReadResult.Failure -> println("[$index / Exception] ${item.reason}")
                    is RowReadResult.Success -> println("[$index / Success] ${item.data}")
                }
            }
        }
    }

    private val KepperCell.valueType: String
        get() = when (this) {
            KepperCell.Empty -> "empty"
            is KepperCell.TypeBoolean -> this.booleanValue.toString()
            is KepperCell.TypeDouble -> this.doubleValue.toString()
            is KepperCell.TypeString -> this.stringValue
            KepperCell.Unsupported -> "unsupported"
        }
}