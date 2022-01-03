import com.hadaralex.khit.Khit
import com.kepper.commons.RowReadResult
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

        val adapter = Khit.adapter(Item::class.java)
//        val adapter = Khit.adapter(Grocery::class.java)

        runBlocking {
            val xlsxFile = reader.loadFile()
            val items = adapter.readSheet(xlsxFile.pages().first())
            for (item in items) {
                when (item) {
                    is RowReadResult.Failure -> println("[Failure] ${item.reason}")
                    is RowReadResult.Success -> println("[Success] ${item.data}")
                }
            }

            xlsxFile.close()
        }
    }
}