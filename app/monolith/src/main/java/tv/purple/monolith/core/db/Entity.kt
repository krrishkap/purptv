package tv.purple.monolith.core.db

interface Entity<D> {
    fun getKey(): String
    fun getData(): D
    fun copy(data: D): Entity<D>
}