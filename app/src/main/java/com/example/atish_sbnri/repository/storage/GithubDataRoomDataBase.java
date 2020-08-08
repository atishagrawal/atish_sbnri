package com.example.atish_sbnri.repository.storage;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atish_sbnri.model.GithubDataModel;
import com.example.atish_sbnri.utils.Constants;

@Database(entities = {GithubDataModel.class}, version = 1)
public abstract class GithubDataRoomDataBase extends RoomDatabase {
    private static GithubDataRoomDataBase instance;

    public abstract GithubDataDao dataDAO();

    private static final Object sLock = new Object();
    private LiveData<PagedList<GithubDataModel>> reposData;

    public static GithubDataRoomDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        GithubDataRoomDataBase.class, Constants.DATABASE_NAME)
                        .build();
                instance.init();

            }
            return instance;
        }
    }

    private void init() {
        PagedList.Config pagedListConfig = (new PagedList.Config.Builder()).setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build();
        DBReposDataSourceFactory dataSourceFactory = new DBReposDataSourceFactory(dataDAO());
        LivePagedListBuilder livePagedListBuilder = new LivePagedListBuilder(dataSourceFactory, pagedListConfig);
        reposData = livePagedListBuilder.build();
    }

    public LiveData<PagedList<GithubDataModel>> getRepos() {
        return reposData;
    }
}
