package com.example.atish_sbnri.repository;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.paging.PagedList;

import com.example.atish_sbnri.model.GithubDataModel;
import com.example.atish_sbnri.repository.network.NetworkRepos;
import com.example.atish_sbnri.repository.network.paging.NetReposDataSourceFactory;
import com.example.atish_sbnri.repository.storage.GithubDataRoomDataBase;

import rx.schedulers.Schedulers;

public class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();
    private static DataRepository instance;
    final private NetworkRepos network;
    final private GithubDataRoomDataBase database;
    final private MediatorLiveData liveDataMerger;

    private DataRepository(Context context) {

        NetReposDataSourceFactory dataSourceFactory = new NetReposDataSourceFactory();

        //Initialize
        network = new NetworkRepos(dataSourceFactory, boundaryCallback);
        database = GithubDataRoomDataBase.getInstance(context.getApplicationContext());


        //Save in DB

        liveDataMerger = new MediatorLiveData<>();
        liveDataMerger.addSource(network.getPagedData(), value -> {
            liveDataMerger.setValue(value);
            Log.d(TAG, value.toString());
        });

        // Save Data into DB
        dataSourceFactory.getRepos().
                observeOn(Schedulers.io()).
                subscribe(repo -> {
                    database.dataDAO().insertRepo(repo);
                });

    }

    private PagedList.BoundaryCallback<GithubDataModel> boundaryCallback = new PagedList.BoundaryCallback<GithubDataModel>() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            liveDataMerger.addSource(database.getRepos(), value -> {
                liveDataMerger.setValue(value);
                liveDataMerger.removeSource(database.getRepos());
            });
        }
    };

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DataRepository(context);
        }
        return instance;
    }

    public LiveData<PagedList<GithubDataModel>> getRepos() {
        return liveDataMerger;
    }


}
