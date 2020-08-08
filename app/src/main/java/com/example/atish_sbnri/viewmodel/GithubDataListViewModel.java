package com.example.atish_sbnri.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.atish_sbnri.model.GithubDataModel;
import com.example.atish_sbnri.repository.DataRepository;

public class GithubDataListViewModel extends AndroidViewModel {

    private DataRepository repository;

    public GithubDataListViewModel(@NonNull Application application) {
        super(application);
        repository = DataRepository.getInstance(application);
    }

    public LiveData<PagedList<GithubDataModel>> getRepos() {
        return repository.getRepos();
    }


}
