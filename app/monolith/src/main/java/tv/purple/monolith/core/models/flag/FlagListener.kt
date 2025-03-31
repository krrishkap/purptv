package tv.purple.monolith.core.models.flag

interface FlagListener {
    fun onFlagValueChanged(flag: Flag)
}