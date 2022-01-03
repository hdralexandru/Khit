package com.example.demo

import com.hadaralex.khit.annotations.Key
import com.hadaralex.khit.annotations.Page

@Page
data class Grocery(
    @Key(name = "id") val id: Double,
)