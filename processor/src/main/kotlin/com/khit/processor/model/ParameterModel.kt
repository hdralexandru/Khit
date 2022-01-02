package com.khit.processor.model

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSValueParameter

internal data class ParameterModel(
    val name: String,
    val positionInConstructor: Int,
    val type: ParameterType,
) {
    companion object {
        fun from(parameter: KSValueParameter, index: Int, logger: KSPLogger): ParameterModel? {
            val name = parameter.name?.asString() ?: return null
            val type = ParameterType.from(parameter.type.resolve().declaration)
            logger.warn("Parameter: $name of type $type")
            return if (type == ParameterType.UNSUPPORTED) {
                logger.error("Unsupported type detected. Skipping...")
                null
            } else {
                ParameterModel(name, index, type)
            }
        }
    }
}