import com.hadaralex.khit.annotations.Key
import com.hadaralex.khit.annotations.Page

@Page
data class Item(
    @Key(name = "id") val id: Double,
    @Key(name = "name") val name: String,
    @Key(name = "available") val available: Boolean = true,
)
