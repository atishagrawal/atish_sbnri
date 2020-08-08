package com.example.atish_sbnri.repository.storage;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.atish_sbnri.model.GithubDataModel;

import java.util.List;


@Dao
public interface GithubDataDao {
    //Replace older data with the new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRepo(GithubDataModel resultModel);

    //Basic query to fetch data
    @Query("SELECT * from repo_data ORDER BY id ASC")
    List<GithubDataModel> getStoredRepos();
}
