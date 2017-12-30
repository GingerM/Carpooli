package ma.carpooli.carpooli;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import ma.carpooli.carpooli.utils.PreferencesHelper;
import timber.log.Timber;

/**
 * Created by MouadS on 19/11/2017.
 */

public class CarpooliApplication extends Application {

    public ArrayList<LiftData> ld;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        PreferencesHelper.init(this);
        PreferencesHelper.getInstance().initDeviceId(this);

        ld = new ArrayList<>();

    /*if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);*/

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
