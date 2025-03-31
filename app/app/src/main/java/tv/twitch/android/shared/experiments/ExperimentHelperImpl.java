package tv.twitch.android.shared.experiments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Set;

import io.reactivex.Observable;
import tv.purple.monolith.core.bridge.ExperimentHelperHookImpl;
import tv.purple.monolith.bridge.ex.IExperimentHelper;
import tv.purple.monolith.models.exception.VirtualImpl;
import tv.twitch.android.provider.experiments.ChannelExperiment;
import tv.twitch.android.provider.experiments.Experiment;
import tv.twitch.android.provider.experiments.ExperimentHelper;
import tv.twitch.android.provider.experiments.MiniExperimentModel;
import tv.twitch.android.provider.experiments.RemoteConfigurable;

public class ExperimentHelperImpl implements ExperimentHelper, IExperimentHelper {
    private final ExperimentHelper hook = new ExperimentHelperHookImpl(this); // TODO: __INJECT_FIELD

    /* ... */

    @NonNull
    @Override
    public MiniExperimentModel getModelForExperimentIdOrg(@Nullable String str) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @NonNull
    @Override
    public Observable<Set<RemoteConfigurable>> getUpdatedRemoteConfigurablesObservableOrg() { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public boolean isInGroupForMultiVariantExperimentOrg(@NonNull Experiment experiment, @NonNull String str) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public boolean isInOnGroupForBinaryExperimentOrg(@NonNull Experiment experiment) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public boolean isInRestrictedLocaleForExperimentOrg(@NonNull Experiment experiment) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }


    @Nullable
    @Override
    public String getGroupForExperimentOrg(@Nullable Experiment var1, @Nullable String var2) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Nullable
    @Override
    public String getTreatmentForExperimentIdOrg(@Nullable String var1, @Nullable String var2) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public void overrideGroupForExperimentOrg(@Nullable RemoteConfigurable var1, @Nullable String var2) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public void refreshExperimentsOrg(int i) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public void refreshIfNeededOrg(int i) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @NonNull
    @Override
    public Set<RemoteConfigurable> updateEnabledGroupsForActiveExperimentsOrg() { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public boolean isInDefaultControlGroupOrg(@NonNull Experiment experiment) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Nullable
    @Override
    public String getGroupForChannelExperimentOrg(@Nullable ChannelExperiment var1, @Nullable String var2) { // TODO: __RENAME_METHOD
        throw new VirtualImpl();
    }

    @Override
    public String getGroupForChannelExperiment(ChannelExperiment channelExperiment, String s) { // TODO: __INJECT_METHOD
        return hook.getGroupForChannelExperiment(channelExperiment, s);
    }

    @Override
    public String getGroupForExperiment(Experiment experiment, String s) { // TODO: __INJECT_METHOD
        return hook.getGroupForExperiment(experiment, s);
    }

    @Override
    public MiniExperimentModel getModelForExperimentId(String s) { // TODO: __INJECT_METHOD
        return hook.getModelForExperimentId(s);
    }

    @Override
    public String getTreatmentForExperimentId(String s, String s1) { // TODO: __INJECT_METHOD
        return hook.getTreatmentForExperimentId(s, s1);
    }

    @Override
    public Observable<Set<RemoteConfigurable>> getUpdatedRemoteConfigurablesObservable() { // TODO: __INJECT_METHOD
        return hook.getUpdatedRemoteConfigurablesObservable();
    }

    @Override
    public boolean isInDefaultControlGroup(Experiment experiment) { // TODO: __INJECT_METHOD
        return hook.isInDefaultControlGroup(experiment);
    }

    @Override
    public boolean isInGroupForMultiVariantExperiment(Experiment experiment, String s) { // TODO: __INJECT_METHOD
        return hook.isInGroupForMultiVariantExperiment(experiment, s);
    }

    @Override
    public boolean isInOnGroupForBinaryExperiment(Experiment experiment) { // TODO: __INJECT_METHOD
        return hook.isInOnGroupForBinaryExperiment(experiment);
    }

    @Override
    public boolean isInRestrictedLocaleForExperiment(Experiment experiment) { // TODO: __INJECT_METHOD
        return hook.isInRestrictedLocaleForExperiment(experiment);
    }

    @Override
    public void overrideGroupForExperiment(RemoteConfigurable remoteConfigurable, String s) { // TODO: __INJECT_METHOD
        hook.overrideGroupForExperiment(remoteConfigurable, s);
    }

    @Override
    public void refreshExperiments(int i) { // TODO: __INJECT_METHOD
        hook.refreshExperiments(i);
    }

    @Override
    public void refreshIfNeeded(int i) { // TODO: __INJECT_METHOD
        hook.refreshIfNeeded(i);
    }

    @Override
    public Set<RemoteConfigurable> updateEnabledGroupsForActiveExperiments() { // TODO: __INJECT_METHOD
        return hook.updateEnabledGroupsForActiveExperiments();
    }

    @NonNull
    @Override
    public ExperimentHelper getHook() { // TODO: __INJECT_METHOD
        return hook;
    }
}
