package com.hadaralex.kepper

import com.google.devtools.ksp.processing.*
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
            logger.warn("Found symbol ${it.location}, $it")
            val f = codeGenerator.createNewFile(
                dependencies = Dependencies.ALL_FILES,
                packageName = "sample.hadaralex",
                fileName = "File_${it}"
            )

            with(f) {
                write("/*\n".toByteArray())
                write("File location: ${it.location}".toByteArray())
                write("\n*/".toByteArray())
            }
            f.close()
        }

        return emptyList()
    }
}