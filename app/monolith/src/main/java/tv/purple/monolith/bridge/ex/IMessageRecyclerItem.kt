package tv.purple.monolith.bridge.ex

interface IMessageRecyclerItem {
    fun setHighlightColor(highlightColor: Int?)

    fun getHighlightColor(): Int?

    fun getAlternatingBackground(): Int?

    fun setAlternatingBackground(color: Int?)

    fun skipForAlternatingBackground(): Boolean
}