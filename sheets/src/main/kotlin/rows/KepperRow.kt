package rows

import cells.KepperCell

interface KepperRow {
    val size: Int

    val content: List<KepperCell>
}