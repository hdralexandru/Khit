package com.khit.processor.model

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSValueParameter
import com.khit.processor.util.KEY_ANNOTATION

internal data class ParameterModel(
    val name: String,
    val key: String,
    val positionInConstructor: Int,
    val type: ParameterType,
) {
    companion object {
        fun from(parameter: KSValueParameter, index: Int, logger: KSPLogger): ParameterModel? {
            var key: String? = null
            parameter.annotations.forEach {
                val qualifier = it.annotationType.resolve().declaration.qualifiedName?.asString()
                if (qualifier == KEY_ANNOTATION) {
                    it.arguments.forEach { valueArgument ->
                        if (valueArgument.name?.asString() == "name") {
                            key = valueArgument.value.toString()
                        }
                    }
                }
            }
            if (key == null) {
                logger.error("You must provide a key!")
                return null
            }

            val name = parameter.name?.asString() ?: return null
            val type = ParameterType.from(parameter.type.resolve().declaration)
            logger.warn("Parameter: $name of type $type")
            return if (type == ParameterType.UNSUPPORTED) {
                logger.error("Unsupported type detected. Skipping...")
                null
            } else {
                ParameterModel(name, key!!, index, type)
            }
        }
    }
}