package com.kepper.sheets

import com.kepper.commons.model.KepperFile
import com.kepper.sheets.microsoft.MicrosoftFileOpener

class KepperFileReader private constructor(
    private val fileType: FileType,
    private val path: AbsolutFilePath,
) {
    suspend fun loadFile(): KepperFile = when (fileType) {
        FileType.MICROSOFT -> MicrosoftFileOpener()
        FileType.OPEN_OFFICE -> TODO()
    }.open(path)

    class Builder {
        var type: FileType? = null
        var path: AbsolutFilePath? = null

        fun build(): KepperFileReader {
            val type = requireNotNull(type) { "You must specify the type of the file." }
            val path = requireNotNull(path) { "You must specify the path of the file." }

            return KepperFileReader(type, path)
        }
    }

    companion object {
        inline operator fun invoke(crossinline func: Builder.() -> Unit): KepperFileReader {
            val builder = Builder()
            func(builder)
            return builder.build()
        }
    }
}