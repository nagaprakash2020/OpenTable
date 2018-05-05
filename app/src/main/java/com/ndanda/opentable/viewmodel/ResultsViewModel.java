package com.ndanda.opentable.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.ndanda.opentable.api.ApiResponse;
import com.ndanda.opentable.data.Results;
import com.ndanda.opentable.data.Reviews;
import com.ndanda.opentable.repository.OpenTableRepository;
import com.ndanda.opentable.utils.AbsentLiveData;
import com.ndanda.opentable.vo.Resource;

import java.util.List;
import java.util.Locale;

public class ResultsViewModel extends ViewModel {

    private OpenTableRepository repository;
    private LiveData<Resource<List<Results>>> reviewResultsApiResponse;
    private MediatorLiveData<Resource<List<Results>>> reviewResults = new MediatorLiveData<>();

    public ResultsViewModel(OpenTableRepository repository) {
        this.repository = repository;

        // Get favorite events
        reviewResultsApiResponse = repository.getReviews();

        // reviewResults will be updated when reviewResultsApiResponse is updated.
        reviewResults.addSource(reviewResultsApiResponse, search -> {
            if(reviewResultsApiResponse != null && reviewResultsApiResponse.getValue() != null)
            reviewResults.setValue(reviewResultsApiResponse.getValue());
        });


    }

    public MediatorLiveData<Resource<List<Results>>> getReviewResults() {
        return reviewResults;
    }
}
