package com.hadaralex.kepper.excel

import com.hadaralex.kepper.excel.utils.toKepperSheet
import com.hadaralex.kepper.file.KepperSheet
import org.apache.poi.ss.usermodel.Sheet

class ExcelSheetIterator(private val originalIterator: Iterator<Sheet>) : Iterator<KepperSheet> {

    override fun next(): KepperSheet = originalIterator.next().toKepperSheet()

    override fun hasNext(): Boolean = originalIterator.hasNext()
}