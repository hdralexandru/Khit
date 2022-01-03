import com.kepper.commons.KepperAdapter
import com.kepper.commons.RowReadResult
import com.kepper.commons.RowReadResult.Companion.wrapInRowReadResult
import com.kepper.commons.exceptions.KepperException
import com.kepper.commons.model.KepperPage
import com.kepper.commons.model.PageHeader
import com.khit.utils.indexOfOrThrow
import com.khit.utils.RowReader
import kotlin.collections.MutableList

public class PageAdapter_Item_NewVersion : KepperAdapter<Item> {
  public override suspend fun readSheet(page: KepperPage): List<RowReadResult<Item>> {
    val header: PageHeader = page.header
    val idIndex: Int
    val name2Index: Int
    val availableIndex: Int
    try {
      idIndex = header.indexOfOrThrow("id", page.sheetName)
      name2Index = header.indexOfOrThrow("name2", page.sheetName)
      availableIndex = header.indexOfOrThrow("available", page.sheetName)
    } catch (ke: KepperException) {
      return listOf(RowReadResult.Failure(ke))
    }
    val items: MutableList<RowReadResult<Item>> = mutableListOf()
    for (row in page.dataRowIterator) {
      val rowReadResult: RowReadResult<Item> = wrapInRowReadResult {
        val id: Double = RowReader.readDoubleOrThrow(row, idIndex)
        val name2: String = RowReader.readStringOrThrow(row, name2Index)
        val available: Boolean = RowReader.readBooleanOrThrow(row, availableIndex)
        Item(id = id, name = name2, available = available)
      }
      items.add(rowReadResult)
    }
    return items
  }
}
