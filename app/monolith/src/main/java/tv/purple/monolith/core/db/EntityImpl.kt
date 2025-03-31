package tv.purple.monolith.core.db

data class EntityImpl<D>(
    private val key: String,
    private val data: D
) : Entity<D> {
    override fun getKey(): String {
        return key
    }

    override fun getData(): D {
        return data
    }

    override fun copy(data: D): Entity<D> {
        return EntityImpl(getKey(), data)
    }
}