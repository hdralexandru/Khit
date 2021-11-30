package com.hadaralex.kepper.file

import java.lang.reflect.Type
import kotlin.reflect.KClass
import org.apache.poi.ss.usermodel.Sheet

class SheetReader<T> internal constructor(
    val sheet: Sheet,
){

}