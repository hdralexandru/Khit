package com.example.demo

import com.hadaralex.khit.annotations.Key
import com.hadaralex.khit.annotations.Page

@Page
class Grocery(
    @Key(name = "id") val id: Double,
)