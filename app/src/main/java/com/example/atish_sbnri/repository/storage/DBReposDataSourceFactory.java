package com.example.atish_sbnri.repository.storage;


import androidx.paging.DataSource;

public class DBReposDataSourceFactory extends DataSource.Factory {

    private DBReposPageKeyedDataSource dbReposPageKeyedDataSource;

    public DBReposDataSourceFactory(GithubDataDao dao) {
        dbReposPageKeyedDataSource = new DBReposPageKeyedDataSource(dao);
    }

    @Override
    public DataSource create() {
        return dbReposPageKeyedDataSource;
    }

}
