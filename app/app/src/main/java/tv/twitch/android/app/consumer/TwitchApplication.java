package tv.twitch.android.app.consumer;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import dagger.hilt.android.HiltAndroidApp;
import tv.purple.monolith.bridge.PurpleTVAppContainer;
import tv.purple.monolith.models.IPurpleTVAppContainer;
import tv.purple.monolith.bridge.ex.IPurpleTVAppContainerProvider;
import tv.purple.monolith.models.exception.VirtualImpl;

@HiltAndroidApp
public class TwitchApplication extends Application implements IPurpleTVAppContainerProvider { // TODO: __IMPLEMENT
    private volatile IPurpleTVAppContainer purpleTVAppContainer; // TODO: __ADD_FIELD

    /* ... */

    public final ApplicationDelegate getApplicationDelegate() {
        throw new VirtualImpl();
    }

    /* ... */

    public void onCreate() {
        purpleTVAppContainer = PurpleTVAppContainer.create(this); // TODO: __INJECT_CODE
        purpleTVAppContainer.onBeforeHiltBeforeSuperOnCreate(); // TODO: __INJECT_CODE

//        Intrinsics.checkNotNullParameter(application, "application");
//        TraceUtil.beginSection(TraceUtil.TraceTag.APPLICATION_ON_CREATE);
//        if (BuildConfigUtil.INSTANCE.isDebugConfigEnabled()) {
//            Timber.plant(new LogcatLoggingTree());
//        }
//        UpTimeCalculator.INSTANCE.onAppStarted();
//        ApplicationContext.Companion.getInstance().setContext(application);
//        ThemeManager.Companion.setTheme(application);
//        FirebaseApp.initializeApp(application);
//        CrashReporter.INSTANCE.init(application, ApplicationDelegate.listener);
//        syncLocaleIdentifier();
//        Android11WebViewFix.INSTANCE.maybeSetWebViewDataDirectory(application);

        ApplicationDelegate.Companion.beforeSuperOnCreate(this);
        purpleTVAppContainer.onAfterHiltBeforeSuperOnCreate(); // TODO: __INJECT_CODE
        super.onCreate();
        purpleTVAppContainer.onBeforeHiltAfterSuperOnCreate(); // TODO: __INJECT_CODE
        getApplicationDelegate().afterSuperOnCreate();
        purpleTVAppContainer.onAfterHiltAfterSuperOnCreate(); // TODO: __INJECT_CODE

        getApplicationDelegate().trackApplicationOnCreateLatency(0, 0, 0);

        /* ... */

        throw new VirtualImpl();
    }

    @NonNull
    @Override
    public IPurpleTVAppContainer providePurpleTVAppContainer() { // TODO: __INJECT_METHOD
        if (purpleTVAppContainer == null) {
            // Send toast before throw exception
            Toast.makeText(this, "Failed to apply patches!", Toast.LENGTH_LONG).show();
            throw new IllegalStateException("purpleTVAppContainer is not initialized!");
        }

        return purpleTVAppContainer;
    }
}