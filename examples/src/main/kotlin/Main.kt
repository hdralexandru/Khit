import com.kepper.commons.KepperAdapter
import com.kepper.commons.RowReadResult
import com.kepper.commons.model.KepperCell
import com.kepper.sheets.AbsolutFilePath
import com.kepper.sheets.FileType
import com.kepper.sheets.KepperFileReader
import kotlinx.coroutines.runBlocking

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
            val adapter: KepperAdapter<Item> = PageAdapter_Item()
            val items = adapter.readSheet(list.first())
            for (item in items) {
                when (item) {
                    is RowReadResult.Failure -> println("[Failure] ${item.reason}")
                    is RowReadResult.Success -> println("[Success] ${item.data}")
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