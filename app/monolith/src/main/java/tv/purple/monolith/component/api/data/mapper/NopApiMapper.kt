package tv.purple.monolith.component.api.data.mapper

import tv.purple.monolith.models.data.Donation
import tv.purple.monolith.models.data.badges.impl.BadgeImpl
import tv.purple.monolith.models.data.badges.BadgeSet
import tv.purple.monolith.models.retrofit.nop.DonationsData
import javax.inject.Inject

class NopApiMapper @Inject constructor() {
    fun map(response: DonationsData): BadgeSet {
        return BadgeSet.Builder().apply {
            val defaultBadgeUrl = response.defaultBadgeUrl
            val defaultBadge = BadgeImpl(code = "nopbreak", url = defaultBadgeUrl)

            response.users?.forEach { user ->
                user.userId.toIntOrNull()?.let { userId ->
                    user.badgeUrl?.ifBlank { null }?.let { badgeUrl ->
                        addBadge(
                            badge = BadgeImpl(
                                code = "nopbreak",
                                url = badgeUrl
                            ),
                            userId = userId
                        )
                    } ?: run {
                        addBadge(
                            badge = defaultBadge,
                            userId = userId
                        )
                    }
                }
            }
        }.build()
    }

    fun mapDonators(response: DonationsData): List<Donation> {
        return response.users?.mapNotNull { user ->
            user.userId.toIntOrNull()?.let { userId ->
                Donation(
                    userName = user.userName,
                    displayName = user.displayName,
                    userId = userId,
                    badgeUrl = user.badgeUrl
                )
            }
        } ?: emptyList()
    }

    fun map(lines: List<String>): BadgeSet {
        val r = lines.linesToTriple()

        return BadgeSet.Builder().apply {
            val defaultBadgeUrl = "https://nopbreak.ru/shared/badge.png"
            val defaultBadge = BadgeImpl(code = "nopbreak", url = defaultBadgeUrl)

            r.forEach { pair ->
                val userId = pair.first
                pair.third?.let { badgeUrl ->
                    addBadge(
                        badge = BadgeImpl("nopbreak", url = badgeUrl),
                        userId = userId
                    )
                } ?: run {
                    addBadge(
                        badge = defaultBadge,
                        userId = userId,
                    )
                }
            }
        }.build()
    }

    fun mapDonators(lines: List<String>): List<Donation> {
        return lines.linesToTriple().map {
            Donation(
                userName = it.second,
                displayName = it.second,
                userId = it.first,
                badgeUrl = it.third
            )
        }
    }

    companion object {
        fun List<String>.linesToTriple(): List<Triple<Int, String, String?>> {
            return mapNotNull {
                val r = it.split('@')
                when (r.size) {
                    2 -> Triple(r[0].toInt(), r[1], null)
                    3 -> Triple(r[0].toInt(), r[1], r[2])
                    else -> null
                }
            }
        }
    }
}