import com.hadaralex.khit.Khit
import com.khit.commons.RowReadResult
import com.khit.sheets.AbsolutFilePath
import com.khit.sheets.FileType
import com.khit.sheets.KhitFileReader
import kotlinx.coroutines.runBlocking

internal object Main {
    private const val SHEET_PATH: String = "data/products.xlsx"

    @JvmStatic
    fun main(args: Array<String>) {
        val reader = KhitFileReader {
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