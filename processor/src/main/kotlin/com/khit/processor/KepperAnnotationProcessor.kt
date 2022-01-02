package com.khit.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.khit.processor.model.AnnotatedModel
import com.khit.processor.util.PAGE_ANNOTATION
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview

@KotlinPoetKspPreview
internal class KepperAnnotationProcessor(
    private val codeGenerator: CodeGenerator,
    private val logger: KSPLogger,
    private val options: Map<String, String>,
) : SymbolProcessor {
    private companion object {
        private val STOP get() = emptyList<KSAnnotated>()
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols: Sequence<KSAnnotated> = resolver.getSymbolsWithAnnotation(PAGE_ANNOTATION)
        val models: MutableList<AnnotatedModel> = mutableListOf()
        for (symbol in symbols) {
            val model = AnnotatedModel.from(logger, symbol, resolver)
            if (model != null) {
                models.add(model)
            }
        }

        models.forEach {
            FileGenerator(it).writeTo(codeGenerator)
        }

        // returning
        return STOP
    }
}