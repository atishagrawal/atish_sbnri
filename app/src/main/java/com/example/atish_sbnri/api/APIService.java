package com.example.atish_sbnri.api;

import com.example.atish_sbnri.model.GithubDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    //URL To fetch Data from Github API

    @GET("repos")
    Call<ArrayList<GithubDataModel>> getReposData(@Query("page") int page, @Query("per_page") int per_page);
}
