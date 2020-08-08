package com.example.atish_sbnri.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atish_sbnri.R;
import com.example.atish_sbnri.viewmodel.GithubDataListViewModel;

public class DataFragment extends Fragment {

    private static final String TAG = DataFragment.class.getSimpleName();

    protected GithubDataListViewModel viewModel;

    protected RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewRepos);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Initialize ViewModel
        viewModel = ViewModelProviders.of(getActivity()).get(GithubDataListViewModel.class);

        //Start Observer to listen for Data Changes
        final GithubRepoListAdapter pageListAdapter = new GithubRepoListAdapter();
        viewModel.getRepos().observe(getViewLifecycleOwner(), pageListAdapter::submitList);
        recyclerView.setAdapter(pageListAdapter);


        return view;
    }


}

