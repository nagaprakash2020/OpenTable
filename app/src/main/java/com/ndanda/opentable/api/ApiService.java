package com.ndanda.opentable.api;

import android.arch.lifecycle.LiveData;

import com.ndanda.opentable.data.Reviews;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ndanda on 4/10/2018.
 */

public interface ApiService {

    @GET("reviews/dvd-picks.json?order=by-date")
    LiveData<ApiResponse<Reviews>> getReviews();
}