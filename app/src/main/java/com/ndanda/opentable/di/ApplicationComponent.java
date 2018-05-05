package com.ndanda.opentable.di;

import android.app.Application;

import com.ndanda.opentable.OpenTableApplication;
import com.ndanda.opentable.view.LandingActivity;
import com.ndanda.opentable.view.ReviewsFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by ndanda on 4/8/2018.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent extends AndroidInjector<OpenTableApplication>{

    Application exposeApplication();
    void inject(LandingActivity landingActivity);
    void inject(ReviewsFragment reviewsFragment);
}
