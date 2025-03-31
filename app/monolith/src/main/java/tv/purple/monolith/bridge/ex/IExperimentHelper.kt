package tv.purple.monolith.bridge.ex

import io.reactivex.Observable
import tv.twitch.android.provider.experiments.ChannelExperiment
import tv.twitch.android.provider.experiments.Experiment
import tv.twitch.android.provider.experiments.ExperimentHelper
import tv.twitch.android.provider.experiments.MiniExperimentModel
import tv.twitch.android.provider.experiments.RemoteConfigurable

interface IExperimentHelper {
    fun getGroupForExperimentOrg(var1: Experiment?, var2: String?): String?

    fun getModelForExperimentIdOrg(var1: String?): MiniExperimentModel?

    fun getTreatmentForExperimentIdOrg(var1: String?, var2: String?): String?

    fun getUpdatedRemoteConfigurablesObservableOrg(): Observable<Set<RemoteConfigurable?>?>?

    fun isInGroupForMultiVariantExperimentOrg(var1: Experiment?, var2: String?): Boolean

    fun isInOnGroupForBinaryExperimentOrg(var1: Experiment?): Boolean

    fun isInRestrictedLocaleForExperimentOrg(var1: Experiment?): Boolean

    fun overrideGroupForExperimentOrg(var1: RemoteConfigurable?, var2: String?)

    fun refreshExperimentsOrg(var1: Int)

    fun refreshIfNeededOrg(var1: Int)

    fun updateEnabledGroupsForActiveExperimentsOrg(): Set<RemoteConfigurable?>?

    fun isInDefaultControlGroupOrg(experiment: Experiment): Boolean

    fun getGroupForChannelExperimentOrg(var1: ChannelExperiment?, var2: String?): String?

    fun getHook(): ExperimentHelper
}