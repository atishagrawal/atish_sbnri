package com.example.atish_sbnri.repository.network.paging;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.atish_sbnri.api.RetrofitClient;
import com.example.atish_sbnri.model.GithubDataModel;
import com.example.atish_sbnri.utils.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subjects.ReplaySubject;

public class NetReposPageKeyedDataSource extends PageKeyedDataSource<Integer, GithubDataModel> {

    private static final String TAG = NetReposPageKeyedDataSource.class.getSimpleName();
    private final ReplaySubject<GithubDataModel> reposObservable;

    NetReposPageKeyedDataSource() {
        reposObservable = ReplaySubject.create();
    }


    public ReplaySubject<GithubDataModel> getRepos() {
        return reposObservable;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, GithubDataModel> callback) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize);

        Call<ArrayList<GithubDataModel>> callBack = RetrofitClient.getInstance().getApi().getReposData(Constants.FIRST_PAGE, Constants.PAGE_SIZE);
        callBack.enqueue(new Callback<ArrayList<GithubDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GithubDataModel>> call, Response<ArrayList<GithubDataModel>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), 1, 2);

                    for (int i = 0; i < response.body().size(); i++)
                        reposObservable.onNext(response.body().get(i));


                } else {
                    Log.e("API CALL", response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GithubDataModel>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                callback.onResult(new ArrayList<>(), 1, 2);
            }
        });
    }


    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, final @NonNull LoadCallback<Integer, GithubDataModel> callback) {
        Log.i(TAG, "Loading page " + params.key);


        Call<ArrayList<GithubDataModel>> callBack = RetrofitClient.getInstance().getApi().getReposData(params.key, Constants.PAGE_SIZE);
        callBack.enqueue(new Callback<ArrayList<GithubDataModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GithubDataModel>> call, Response<ArrayList<GithubDataModel>> response) {
                if (response.isSuccessful()) {
                    callback.onResult(response.body(), params.key + 1);

                    for (int i = 0; i < response.body().size(); i++)
                        reposObservable.onNext(response.body().get(i));
                } else {
                    Log.e("API CALL", response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GithubDataModel>> call, Throwable t) {
                String errorMessage;
                if (t.getMessage() == null) {
                    errorMessage = "unknown error";
                } else {
                    errorMessage = t.getMessage();
                }
                callback.onResult(new ArrayList<>(), params.key);
            }
        });
    }


    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GithubDataModel> callback) {

    }
}
