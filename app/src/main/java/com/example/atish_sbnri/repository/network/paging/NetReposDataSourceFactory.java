package com.example.atish_sbnri.repository.network.paging;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.atish_sbnri.model.GithubDataModel;

import rx.subjects.ReplaySubject;

public class NetReposDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<NetReposPageKeyedDataSource> mutableDataSource;
    private NetReposPageKeyedDataSource netReposPageKeyedDataSource;

    public NetReposDataSourceFactory() {
        this.mutableDataSource = new MutableLiveData<>();
        netReposPageKeyedDataSource = new NetReposPageKeyedDataSource();
    }


    @Override
    public DataSource create() {
        mutableDataSource.postValue(netReposPageKeyedDataSource);
        return netReposPageKeyedDataSource;
    }


    public ReplaySubject<GithubDataModel> getRepos() {
        return netReposPageKeyedDataSource.getRepos();
    }

}
