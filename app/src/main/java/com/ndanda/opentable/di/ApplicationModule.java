package com.ndanda.opentable.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ndanda.opentable.AppExecutors;
import com.ndanda.opentable.OpenTableApplication;
import com.ndanda.opentable.api.ApiService;
import com.ndanda.opentable.repository.ReviewsDao;
import com.ndanda.opentable.repository.OpenTableDatabase;
import com.ndanda.opentable.repository.OpenTableRepository;
import com.ndanda.opentable.utils.LiveDataCallAdapterFactory;
import com.ndanda.opentable.utils.RetrofitApiKeyInterceptor;
import com.ndanda.opentable.viewmodel.OpenTableViewModelFactory;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ndanda on 4/8/2018.
 */

@Module
public class ApplicationModule {

    private final OpenTableApplication application;

    public ApplicationModule(OpenTableApplication openTableApplication){
        application = openTableApplication;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(OpenTableRepository repository){
        return new OpenTableViewModelFactory(repository);
    }

    @Provides
    @Singleton
    AppExecutors providesAppExecutors(){
        return new AppExecutors();
    }


    @Provides
    @Named("dataBase")
    String providesDatabasePath(){
        return "OpenTable.db";
    }

    @Singleton
    @Provides
    OpenTableDatabase provideOpenTableDatabase(Application application, @Named("dataBase") String databaseName){
        return Room.databaseBuilder(
                application,
                OpenTableDatabase.class,
                databaseName)
                .fallbackToDestructiveMigration()
                .build();
    }


    @Singleton
    @Provides
    ReviewsDao provideFavoriteDao(OpenTableDatabase openTableDatabase) {
        return openTableDatabase.reviewsDao();
    }

    @Provides
    @Singleton
    OpenTableRepository provideHomeAloneRepository(AppExecutors appExecutors, ApiService apiService, ReviewsDao reviewsDao){
        return new OpenTableRepository(appExecutors,apiService, reviewsDao);
    }

    @Provides
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpClientBuilder(Interceptor apiKeyInterceptor){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(apiKeyInterceptor);
        httpClient.addInterceptor(logging);

        return httpClient;
    }

    @Provides
    @Named("baseUrl")
    String provideBaseUrl(){
        return "http://api.nytimes.com/svc/movies/v2/";
    }

    @Provides
    @Singleton
    Retrofit provideRetroFit(OkHttpClient.Builder okHttpClientBuilder, @Named("baseUrl") String baseUrl){


        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(okHttpClientBuilder.build())
                .build();

    }

    @Provides
    @Singleton
    Interceptor provideApiKeyInterceptor(){
        return new RetrofitApiKeyInterceptor();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

}
