package com.khit.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.kepper.commons.KepperAdapter
import com.kepper.commons.RowReadResult
import com.kepper.commons.exceptions.KepperException
import com.kepper.commons.model.KepperPage
import com.kepper.commons.model.PageHeader
import com.khit.processor.model.AnnotatedModel
import com.khit.processor.model.ParameterModel
import com.khit.processor.model.ParameterType
import com.khit.utils.NamingUtils
import com.khit.utils.RowReader
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview
import com.squareup.kotlinpoet.ksp.writeTo

@KotlinPoetKspPreview
internal class FileGenerator private constructor(private val model: AnnotatedModel) {
    private var fileSpec: FileSpec? = null

    fun generate(): FileGenerator {
        this.fileSpec = FileSpec.builder(model.packageName, NamingUtils.buildAdapterName(model.className))
            .addType(
                TypeSpec.classBuilder(NamingUtils.buildAdapterName(model.className))
                    .addSuperinterface(
                        ClassName(KepperAdapter::class.java.`package`.name, "KepperAdapter")
                            .plusParameter(ClassName(model.packageName, model.className))
                    ).addFunction(
                        FunSpec.builder("readSheet")
                            .addModifiers(KModifier.SUSPEND, KModifier.OVERRIDE)
                            .addParameter("page", KepperPage::class)
                            .buildReadSheetBody(model)
                            .build()
                    )
                    .build()
            )
            .build()

        return this
    }

    fun writeTo(codeGenerator: CodeGenerator) {
        val fileSpec = this.fileSpec ?: throw IllegalArgumentException(
            "Be sure to first create the fileSpec by calling generate()"
        )

        fileSpec.writeTo(codeGenerator, Dependencies.ALL_FILES)
    }

    private fun FunSpec.Builder.buildReadSheetBody(annotatedModel: AnnotatedModel) = this.apply {
        addStatement("val header: %T = page.header", PageHeader::class)

        readIndexes(annotatedModel)

        val rowReadResultType = ClassName(RowReadResult::class.java.`package`.name, "RowReadResult").parameterizedBy(
            ClassName(annotatedModel.packageName, annotatedModel.className)
        )
        val mutableListType = ClassName("kotlin.collections", "MutableList")
            .parameterizedBy(rowReadResultType)
        addStatement("val items: %T = mutableListOf()", mutableListType)

        addRowInterogation(annotatedModel, rowReadResultType)

        val namedString = StringBuilder()
        for ((index: Int, pModel: ParameterModel) in annotatedModel.members.withIndex()) {
            namedString.append("${pModel.name} = ${pModel.key}")
            if (index < annotatedModel.members.size - 1) {
                namedString.append(", ")
            }
        }
        addStatement("${annotatedModel.className}(${namedString})")
        endControlFlow()
        addStatement("items.add(rowReadResult)")
        endControlFlow()

        returns(ClassName("kotlin.collections", "List").parameterizedBy(rowReadResultType))
        addStatement("return items")
    }

    private fun FunSpec.Builder.addRowInterogation(
        annotatedModel: AnnotatedModel,
        rowReadResultType: ParameterizedTypeName,
    ) = this.apply {
        val wrapIntoResultMember = MemberName("com.kepper.commons.RowReadResult.Companion", "wrapInRowReadResult")
        beginControlFlow("for (row in page.dataRowIterator)")
        beginControlFlow("val rowReadResult: %T = %M", rowReadResultType, wrapIntoResultMember)
        for (model: ParameterModel in annotatedModel.members) {
            val function = if (model.isNullable) model.type.asReadFunctionOrNull else model.type.asReadFunctionOrThrow
            val type = model.type.kotlinType + if (model.isNullable) "?" else ""
            addStatement(
                "val ${model.key}: $type = %T.$function(row, ${model.key}Index)",
                RowReader::class.java
            )
        }
    }

    private fun FunSpec.Builder.readIndexes(annotatedModel: AnnotatedModel) = this.apply {
        val indexOfOrThrowMember = MemberName("com.khit.utils.", "indexOfOrThrow")
        for (model: ParameterModel in annotatedModel.members) {
            addStatement("val ${model.key}Index: Int")
        }
        beginControlFlow("try {")
        for (model: ParameterModel in annotatedModel.members) {
            addStatement("${model.key}Index = header.%M(\"${model.key}\", page.sheetName)", indexOfOrThrowMember)
        }
        nextControlFlow("catch(ke: %T)", KepperException::class.java)
        addStatement("return listOf(%T(ke))", RowReadResult.Failure::class.java)
        endControlFlow()
    }

    private val ParameterType.asReadFunctionOrThrow: String?
        get() = when (this) {
            ParameterType.STRING -> "readStringOrThrow"
            ParameterType.DOUBLE -> "readDoubleOrThrow"
            ParameterType.BOOLEAN -> "readBooleanOrThrow"
            ParameterType.UNSUPPORTED -> null
        }

    private val ParameterType.asReadFunctionOrNull: String?
        get() = when (this) {
            ParameterType.STRING -> "readStringOrNull"
            ParameterType.DOUBLE -> "readDoubleOrNull"
            ParameterType.BOOLEAN -> "readBooleanOrNull"
            ParameterType.UNSUPPORTED -> null
        }


    companion object {
        @JvmStatic
        fun with(annotatedModel: AnnotatedModel): FileGenerator = FileGenerator(annotatedModel)
    }
}