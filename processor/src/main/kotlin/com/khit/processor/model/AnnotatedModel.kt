package com.khit.processor.model

import com.google.devtools.ksp.getClassDeclarationByName
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.ClassKind
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.khit.processor.util.PAGE_ANNOTATION
import com.squareup.kotlinpoet.asClassName
import kotlin.reflect.KClass

/**
 * Represents the data necessary to create an adapter.
 */
internal data class AnnotatedModel(
    val className: String,
    val packageName: String,
    val members: List<ParameterModel>,
) {
    companion object {
        fun from(logger: KSPLogger, symbol: KSAnnotated): AnnotatedModel? {
            if (symbol !is KSClassDeclaration) {
                logger.error("$PAGE_ANNOTATION can only be applied to classes!")
                return null
            }

            val primaryConstructor: KSFunctionDeclaration? = symbol.primaryConstructor
            if (primaryConstructor == null) {
                logger.error("You need to provide a primary constructor with arguments!")
                return null
            }

            if (symbol.classKind != ClassKind.CLASS) {
                logger.error("$PAGE_ANNOTATION can only be applied to classes!")
                return null
            }

            val fileName: String = symbol.simpleName.asString()
            val packageName: String = symbol.packageName.asString()

            val parameterModels: MutableList<ParameterModel> = mutableListOf()
            primaryConstructor.parameters.forEachIndexed { index, ksValueParameter ->
                // TODO add support for default args.
                val parameterModel = ParameterModel.from(ksValueParameter, index, logger)
                if (parameterModel != null) {
                    parameterModels.add(parameterModel)
                }
            }

            return AnnotatedModel(fileName, packageName, parameterModels)
        }
    }

}