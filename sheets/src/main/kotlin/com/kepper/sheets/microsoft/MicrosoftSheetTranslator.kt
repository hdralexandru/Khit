package com.kepper.sheets.microsoft

import com.kepper.commons.model.KepperPage
import org.apache.poi.ss.usermodel.Sheet

internal class MicrosoftSheetTranslator {
    fun translate(sheet: Sheet): KepperPage {
        return MicrosoftPage(sheet)
    }
}