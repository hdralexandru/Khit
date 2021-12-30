package com.hadaralex.kepper.generated

import com.hadaralex.kepper.annotations.TableColumn

data class Item(
    @TableColumn(columnName = "id")
    val id: Double,
    @TableColumn(columnName = "name")
    val name: String,
    @TableColumn(columnName = "available")
    val available: Boolean,
)
