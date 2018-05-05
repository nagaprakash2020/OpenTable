package com.ndanda.opentable.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ndanda.opentable.AppExecutors;
import com.ndanda.opentable.api.ApiResponse;
import com.ndanda.opentable.api.ApiService;
import com.ndanda.opentable.data.Results;
import com.ndanda.opentable.data.Reviews;
import com.ndanda.opentable.vo.Resource;

import java.util.List;

import javax.inject.Inject;

public class OpenTableRepository {

    private final AppExecutors appExecutors;
    private final ApiService apiService;
    private ReviewsDao reviewsDao;

    @Inject
    public OpenTableRepository(AppExecutors appExecutors, ApiService apiService, ReviewsDao reviewsDao) {
        this.appExecutors = appExecutors;
        this.apiService = apiService;
        this.reviewsDao = reviewsDao;
    }

    public LiveData<Resource<List<Results>>> getReviews() {

        return new NetworkBoundResource<List<Results>,Reviews>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Reviews item) {
                reviewsDao.insertReviewResults(item.getResults());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Results> data) {
                // Always fetch data. This can be later modified based on the requirement.
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Results>> loadFromDb() {
                return reviewsDao.getReviewResults();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Reviews>> createCall() {
                return apiService.getReviews();
            }
        }.asLiveData();
    }
}
