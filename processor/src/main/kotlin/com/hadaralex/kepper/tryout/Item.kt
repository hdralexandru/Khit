package com.hadaralex.kepper.tryout

import com.hadaralex.kepper.TableColumn

data class Item(
    @TableColumn(columnName = "id")
    val id: Int,
    @TableColumn(columnName = "name")
    val name: String,
    @TableColumn(columnName = "available")
    val available: Boolean,
)
