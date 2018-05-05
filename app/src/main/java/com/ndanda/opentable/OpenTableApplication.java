package com.ndanda.opentable;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.ndanda.opentable.di.ApplicationComponent;
import com.ndanda.opentable.di.ApplicationModule;
import com.ndanda.opentable.di.DaggerApplicationComponent;

/**
 * Created by ndanda on 4/8/2018.
 */

public class OpenTableApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }

    }

    public ApplicationComponent getApplicationComponent() {
         return applicationComponent;
    }
}
