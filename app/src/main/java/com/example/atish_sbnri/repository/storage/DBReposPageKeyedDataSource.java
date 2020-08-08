package com.example.atish_sbnri.repository.storage;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.atish_sbnri.model.GithubDataModel;

import java.util.List;

public class DBReposPageKeyedDataSource extends PageKeyedDataSource<Integer, GithubDataModel> {

    public static final String TAG = DBReposPageKeyedDataSource.class.getSimpleName();
    private final GithubDataDao githubDataDao;

    public DBReposPageKeyedDataSource(GithubDataDao dao) {
        githubDataDao = dao;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, GithubDataModel> callback) {
        Log.d(TAG, "Loading Local Data: " + params.requestedLoadSize);
        List<GithubDataModel> storedRepos = githubDataDao.getStoredRepos();
        if (storedRepos.size() != 0) {
            callback.onResult(storedRepos, 0, 1);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, GithubDataModel> callback) {
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GithubDataModel> callback) {
    }
}
