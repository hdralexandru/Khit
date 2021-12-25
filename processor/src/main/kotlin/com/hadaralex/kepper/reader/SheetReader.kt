package com.hadaralex.kepper.reader

import com.hadaralex.kepper.file.KepperSheet

interface SheetReader {
    fun readHeader(kepperSheet: KepperSheet)
}