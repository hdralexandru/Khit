package com.khit.sheets.common

import com.khit.commons.model.KhitFile
import com.khit.sheets.AbsolutFilePath

internal interface FileOpener {
    suspend fun open(filepath: AbsolutFilePath): KhitFile
}