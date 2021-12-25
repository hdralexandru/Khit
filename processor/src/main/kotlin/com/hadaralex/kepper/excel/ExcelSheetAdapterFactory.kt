package com.hadaralex.kepper.excel

import com.hadaralex.kepper.adapter.SheetAdapter
import com.hadaralex.kepper.exceptions.KepperException
import java.lang.reflect.Type
import kotlin.jvm.Throws

internal class ExcelSheetAdapterFactory: SheetAdapter.Factory {

    // TODO add a specific class
    @Throws(KepperException::class)
    private fun checkTypeIsValidOrThrow(type: Type) {

    }

    @Throws(KepperException::class)
    override fun create(type: Type): SheetAdapter<*> {
        // Step 1: Check if the type is a valid one (class, not interface etc)
        // TODO
        checkTypeIsValidOrThrow(type)

        TODO()
    }
}