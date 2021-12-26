package cells

sealed interface KepperCell {
    class TypeBoolean(val value: Boolean): KepperCell

    class TypeString(val value: String): KepperCell

    class TypeInt(val value: Int): KepperCell
}