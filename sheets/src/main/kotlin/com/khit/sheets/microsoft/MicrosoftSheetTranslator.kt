package com.khit.sheets.microsoft

import com.khit.commons.model.KhitPage
import org.apache.poi.ss.usermodel.Sheet

internal class MicrosoftSheetTranslator {
    fun translate(sheet: Sheet): KhitPage {
        return MicrosoftPage(sheet)
    }
}