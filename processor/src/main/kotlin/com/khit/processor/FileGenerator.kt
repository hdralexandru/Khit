package com.khit.processor

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.kepper.commons.KepperAdapter
import com.kepper.commons.RowReadResult
import com.kepper.commons.model.KepperPage
import com.kepper.commons.model.PageHeader
import com.khit.processor.model.AnnotatedModel
import com.khit.processor.model.ParameterModel
import com.khit.processor.model.ParameterType
import com.khit.processor.util.NamingUtils
import com.khit.processor.util.RowReader
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.MemberName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.plusParameter
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview
import com.squareup.kotlinpoet.ksp.writeTo

@KotlinPoetKspPreview
internal class FileGenerator(private val model: AnnotatedModel) {

    fun writeTo(codeGenerator: CodeGenerator) {
        val fileSpect = FileSpec.builder(model.packageName, NamingUtils.buildAdapterName(model.className))
            .addImport(RowReader::class.java.`package`.name, "RowReader")
            .addType(
                TypeSpec.classBuilder(NamingUtils.buildAdapterName(model.className))
                    .addSuperinterface(
                        ClassName(KepperAdapter::class.java.`package`.name, "KepperAdapter")
                            .plusParameter(ClassName(model.packageName, model.className))
                    ).addFunction(
                        FunSpec.builder("readSheet")
                            .addModifiers(KModifier.SUSPEND, KModifier.OVERRIDE)
                            .returns(ClassName(model.packageName, model.className))
                            .addParameter("page", KepperPage::class)
                            .buildReadSheetBody(model)
                            .build()
                    )
                    .build()
            )
            .build()

        fileSpect.writeTo(codeGenerator, Dependencies.ALL_FILES)
    }

    private fun FunSpec.Builder.buildReadSheetBody(model: AnnotatedModel) = this.apply {
        val indexOfOrThrowMember = MemberName("com.khit.processor.util.PageHeaderExtKt", "indexOfOrThrow")
        addStatement("val header: %T = page.header", PageHeader::class)
        for (pModel: ParameterModel in model.members) {
            addStatement("val ${pModel.key}Index: ${pModel.type.kotlinType} = header.%M(\"${pModel.key}\", sheet.sheetName)",
                indexOfOrThrowMember)
        }
        val rowReadResultType = ClassName(RowReadResult::class.java.`package`.name, "RowReadResult").parameterizedBy(
            ClassName(model.packageName, model.className)
        )
        val mutableListType = ClassName("kotlin.collections", "MutableList")
            .parameterizedBy(rowReadResultType)
        addStatement("val items: %T = mutableList()", mutableListType)

        val wrapIntoResultMember = MemberName("com.kepper.commons.RowReadResult.Companion", "wrapInRowReadResult")
        beginControlFlow("for (row in page.dataRowIterator)")
        beginControlFlow("val rowReadResult: %T = %M", rowReadResultType, wrapIntoResultMember)
        for (pModel: ParameterModel in model.members) {
            addStatement("val ${pModel.key}: ${pModel.type.kotlinType} = RowReader.${pModel.type.asReadFunctionOrThrow}(row, ${pModel.key}Index)")
        }

        val namedString = StringBuilder()
        for ((index: Int, pModel: ParameterModel) in model.members.withIndex()) {
            namedString.append("${pModel.name} = ${pModel.key}")
            if (index < model.members.size - 1) {
                namedString.append(", ")
            }
        }
        addStatement("${model.className}(${namedString.toString()})")
        endControlFlow()
        addStatement("items.add(rowReadResult)")
        endControlFlow()

        addStatement("return items")
    }

    private val ParameterType.asReadFunctionOrThrow: String?
        get() = when (this) {
            ParameterType.STRING -> "readStringOrThrow"
            ParameterType.DOUBLE -> "readDoubleOrThrow"
            ParameterType.BOOLEAN -> "readBooleanOrThrow"
            ParameterType.UNSUPPORTED -> null
        }

}