package sheets

interface KeeperSheet {
    val name: String

    /**
     * Returns the index of the first non-empty cell in this sheet.
     */
    val lastHeaderIndex: Int
}