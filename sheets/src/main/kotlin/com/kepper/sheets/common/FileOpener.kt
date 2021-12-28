package com.kepper.sheets.common

import com.kepper.commons.model.KepperFile
import com.kepper.sheets.AbsolutFilePath

internal interface FileOpener {
    suspend fun open(filepath: AbsolutFilePath): KepperFile

    fun close()
}