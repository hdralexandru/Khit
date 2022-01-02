import com.kepper.commons.model.KepperCell
import com.kepper.sheets.AbsolutFilePath
import com.kepper.sheets.FileType
import com.kepper.sheets.KepperFileReader

internal object Main {
    private const val SHEET_PATH: String = "data/products.xlsx"

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = KepperFileReader {
            path = AbsolutFilePath(SHEET_PATH)
            type = FileType.MICROSOFT
        }

//        runBlocking {
//            val list = reader.loadFile().pages()
//        }
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