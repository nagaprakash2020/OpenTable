package com.ndanda.opentable.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ndanda.opentable.OpenTableApplication;
import com.ndanda.opentable.R;
import com.ndanda.opentable.binding.FragmentDataBindingComponent;
import com.ndanda.opentable.databinding.ReviewsFragmentBinding;
import com.ndanda.opentable.utils.AutoClearedValue;
import com.ndanda.opentable.viewmodel.ResultsViewModel;



import javax.inject.Inject;

public class ReviewsFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    AutoClearedValue<ReviewsFragmentBinding> binding;
    AutoClearedValue<ResultsAdapter> adapter;

    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    public ReviewsFragment() {
        // Required empty public constructor
    }

    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((OpenTableApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ReviewsFragmentBinding reviewsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.reviews_fragment, container, false);
        binding = new AutoClearedValue<>(this,reviewsFragmentBinding);

        //Adapter for recycler view
        ResultsAdapter resultsAdapter = new ResultsAdapter(dataBindingComponent);
        this.adapter = new AutoClearedValue<>(this, resultsAdapter);
        binding.get().reviewsRecyclerView.setAdapter(resultsAdapter);

        return binding.get().getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(isAdded()){

            // On change of getReviewResults. Update the reviews_fragment layout.
            ViewModelProviders.of(getActivity(), viewModelFactory).get(ResultsViewModel.class).getReviewResults().observe(getActivity(), listResource -> {

                if(listResource != null){
                    switch (listResource.status){
                        case LOADING:
                            // Sets the progress bar visibility to true and visibility of error message text view to false.
                            binding.get().setLoading(true);
                            binding.get().setIsError(false);
                            break;
                        case SUCCESS:
                            // Sets the progress bar visibility to false and visibility of error message text view to false.
                            binding.get().setLoading(false);
                            binding.get().setIsError(false);
                            break;
                        case ERROR:
                            // Sets the progress bar visibility to false and visibility of error message text view to true.
                            binding.get().setLoading(false);
                            binding.get().setIsError(true);
                            break;
                    }
                    if(listResource.data != null && !listResource.data.isEmpty())
                        adapter.get().replace(listResource.data);
                }

            });
        }
    }
}
