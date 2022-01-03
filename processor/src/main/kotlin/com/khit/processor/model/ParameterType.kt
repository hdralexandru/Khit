package com.khit.processor.model

import com.google.devtools.ksp.symbol.KSDeclaration

internal enum class ParameterType {
    STRING,
    DOUBLE,
    BOOLEAN,
    UNSUPPORTED;

    val kotlinType: String get() = when(this) {
        STRING -> "String"
        DOUBLE -> "Double"
        BOOLEAN -> "Boolean"
        UNSUPPORTED -> "Nothing" // it will throw
    }

    companion object {
        private val KSDeclaration.isBoolean get() = this.simpleName.asString() == "Boolean"
        private val KSDeclaration.isString get() = this.simpleName.asString() == "String"
        private val KSDeclaration.isDouble get() = this.simpleName.asString() == "Double"

        private fun KSDeclaration.toParameterType() = when {
            isBoolean -> ParameterType.BOOLEAN
            isString -> ParameterType.STRING
            isDouble -> ParameterType.DOUBLE
            else -> ParameterType.UNSUPPORTED
        }

        fun from (ksDeclaration: KSDeclaration): ParameterType = ksDeclaration.toParameterType()
    }
}