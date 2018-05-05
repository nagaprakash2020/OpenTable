package com.ndanda.opentable.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.ndanda.opentable.repository.OpenTableRepository;

import javax.inject.Singleton;

@Singleton
public class OpenTableViewModelFactory implements ViewModelProvider.Factory {

    private final OpenTableRepository repository;

    public OpenTableViewModelFactory(OpenTableRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ResultsViewModel.class))
            return (T) new ResultsViewModel(repository);
        else
            throw new IllegalArgumentException("ViewModel Not Found");

    }
}
