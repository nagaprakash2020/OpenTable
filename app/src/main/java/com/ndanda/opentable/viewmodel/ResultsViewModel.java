package com.ndanda.opentable.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.ndanda.opentable.data.Results;
import com.ndanda.opentable.repository.OpenTableRepository;
import com.ndanda.opentable.vo.Resource;

import java.util.List;

public class ResultsViewModel extends ViewModel {

    private LiveData<Resource<List<Results>>> reviewResultsApiResponse;
    private MediatorLiveData<Resource<List<Results>>> reviewResults = new MediatorLiveData<>();

    public ResultsViewModel(OpenTableRepository repository) {

        // Get favorite events
        reviewResultsApiResponse = repository.getReviews();

        // reviewResults will be updated when reviewResultsApiResponse is updated.
        reviewResults.addSource(reviewResultsApiResponse, search -> {
            if (reviewResultsApiResponse != null && reviewResultsApiResponse.getValue() != null)
                reviewResults.setValue(reviewResultsApiResponse.getValue());
        });
    }

    public MediatorLiveData<Resource<List<Results>>> getReviewResults() {
        return reviewResults;
    }
}
