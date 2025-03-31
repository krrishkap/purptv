package tv.purple.monolith.core.models.flag.core

interface Variant {
    fun getDefault(): Variant
    override fun toString(): String

    companion object {
        fun Variant.getVariants(): List<Variant> {
            return this.javaClass.enumConstants?.toList() ?: emptyList()
        }

        fun Variant.isDefault(): Boolean {
            return this == this.getDefault()
        }

        fun List<Variant>.getPosition(variant: Variant): Int {
            return this.indexOf(variant)
        }

        fun Variant.fromString(str: String): Variant? {
            if (this.javaClass.isEnum) {
                return this.getVariants().firstOrNull { it.toString() == str }
            }

            return null
        }
    }
}