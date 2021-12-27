package com.kepper.adapters.generated

import com.hadaralex.kepper.TableColumn

data class Item(
    @TableColumn(columnName = "id")
    val id: Int,
    @TableColumn(columnName = "name")
    val name: String,
    @TableColumn(columnName = "available")
    val available: Boolean,
)
