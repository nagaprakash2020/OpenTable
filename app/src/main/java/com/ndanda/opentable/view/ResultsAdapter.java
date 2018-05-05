package com.ndanda.opentable.view;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ndanda.opentable.R;
import com.ndanda.opentable.common.DataBoundListAdapter;
import com.ndanda.opentable.data.Results;
import com.ndanda.opentable.databinding.ReviewSingleRowBinding;

public class ResultsAdapter extends DataBoundListAdapter<Results, ReviewSingleRowBinding> {

    private DataBindingComponent dataBindingComponent;

    public ResultsAdapter(DataBindingComponent dataBindingComponent) {
        this.dataBindingComponent = dataBindingComponent;
    }

    @Override
    protected ReviewSingleRowBinding createBinding(ViewGroup parent) {
        ReviewSingleRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.review_single_row, parent, false, dataBindingComponent);
        return binding;
    }

    @Override
    protected void bind(ReviewSingleRowBinding binding, Results item) {
        binding.setResults(item);
    }

    @Override
    protected boolean areItemsTheSame(Results oldItem, Results newItem) {
        return false;
    }

    @Override
    protected boolean areContentsTheSame(Results oldItem, Results newItem) {
        return false;
    }
}
