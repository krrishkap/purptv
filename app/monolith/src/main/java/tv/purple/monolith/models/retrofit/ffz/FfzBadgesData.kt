package tv.purple.monolith.models.retrofit.ffz

import tv.purple.monolith.models.retrofit.ffz.FfzBadge

data class FfzBadgesData(
    val badges: List<FfzBadge>,
    val users: HashMap<String, HashSet<Int>>
)
