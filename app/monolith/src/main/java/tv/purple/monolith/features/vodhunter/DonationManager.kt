package tv.purple.monolith.features.vodhunter

import io.reactivex.disposables.CompositeDisposable
import tv.purple.monolith.component.api.repository.NopRepository
import tv.purple.monolith.core.LoggerWithTag
import tv.twitch.android.core.user.TwitchAccountManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DonationManager @Inject constructor(
    private val nopRepository: NopRepository,
    private val twitchAccountManager: TwitchAccountManager
) {
    private val disposables = CompositeDisposable()
    private val logger = LoggerWithTag("DonationManager")

    private var donatorIds: MutableSet<Int> = mutableSetOf()

    fun isDonator(userId: Int): Boolean {
        if (userId <= 0) {
            return false
        }

        return donatorIds.contains(userId)
    }

    fun isCurrentUserDonator(): Boolean {
        return isDonator(twitchAccountManager.userId)
    }

    fun updateDonators() {
        disposables.add(
            nopRepository.getDonators().subscribe({
                donatorIds.addAll(it.map { item -> item.userId })
                logger.debug("Updated --> $donatorIds")
            }, Throwable::printStackTrace)
        )
    }
}