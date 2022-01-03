package com.khit.sheets

import com.khit.commons.exceptions.UnsupportedKhitException
import com.khit.commons.model.KhitFile
import com.khit.sheets.microsoft.MicrosoftFileOpener

class KhitFileReader private constructor(
    private val fileType: FileType,
    private val path: AbsolutFilePath,
) {
    suspend fun loadFile(): KhitFile = when (fileType) {
        FileType.MICROSOFT -> MicrosoftFileOpener()
        FileType.OPEN_OFFICE -> throw UnsupportedKhitException("")
    }.open(path)

    class Builder {
        var type: FileType? = null
        var path: AbsolutFilePath? = null

        fun build(): KhitFileReader {
            val type = requireNotNull(type) { "You must specify the type of the file." }
            val path = requireNotNull(path) { "You must specify the path of the file." }

            return KhitFileReader(type, path)
        }
    }

    companion object {
        inline operator fun invoke(crossinline func: Builder.() -> Unit): KhitFileReader {
            val builder = Builder()
            func(builder)
            return builder.build()
        }
    }
}