package com.hadaralex.kepper

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.hadaralex.kepper.util.EXCEL_SHEET_ANNOTATION

internal class KepperAnnotationProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation(EXCEL_SHEET_ANNOTATION)

        symbols.forEach {
            // Note: we use warn because it logs on all builds
            logger.warn("Found symbol ${it.location}")
        }

        return emptyList()
    }
}