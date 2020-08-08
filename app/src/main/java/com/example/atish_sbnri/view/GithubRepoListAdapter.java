package com.example.atish_sbnri.view;


import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atish_sbnri.R;
import com.example.atish_sbnri.model.GithubDataModel;


public class GithubRepoListAdapter extends PagedListAdapter<GithubDataModel, GithubRepoListAdapter.RepoDataViewHolder> {

    public GithubRepoListAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public RepoDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        RepoDataViewHolder viewHolder = new RepoDataViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RepoDataViewHolder holder, int position) {

        //Get Data from position and populate row layout
        GithubDataModel singleGithubDataModel = getItem(position);
        holder.txtId.setText("" + singleGithubDataModel.getId());
        holder.txtName.setText("" + singleGithubDataModel.getName());
        holder.txtDescription.setText("" + singleGithubDataModel.getDescription());
        holder.txtOpenIssuesCount.setText("Open: " + singleGithubDataModel.getOpenIssuesCount());

        // Color code issues for Green <=5, Orange >5<=10, Red >10
        if (singleGithubDataModel.getOpenIssuesCount() <= 5) {
            holder.txtOpenIssuesCount.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.color_issue_count_green));
        } else if (singleGithubDataModel.getOpenIssuesCount() > 5 && singleGithubDataModel.getOpenIssuesCount() <= 10) {
            holder.txtOpenIssuesCount.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.color_issue_count_orange));
        } else {
            holder.txtOpenIssuesCount.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.color_issue_count));
        }


        if (singleGithubDataModel.getLicense() != null)
            holder.txtLicense.setText("" + singleGithubDataModel.getLicense().getLicenseName());

        String permissions = "";

        permissions += singleGithubDataModel.getPermissions().getAdmin() ? "Admin, " : "";
        permissions += singleGithubDataModel.getPermissions().getPush() ? "Push, " : "";
        permissions += singleGithubDataModel.getPermissions().getPull() ? "Pull," : "";

        if (TextUtils.isEmpty(permissions))
            holder.txtPermissions.setText("None");
        else {
            permissions = permissions.substring(0, permissions.length() - 1);
            holder.txtPermissions.setText(permissions);
        }

    }


    /**
     * Basic DIFF_CALLBACK. Used to convey whether the current data is same or has changed
     */
    private static DiffUtil.ItemCallback<GithubDataModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<GithubDataModel>() {
                @Override
                public boolean areItemsTheSame(GithubDataModel oldItem, GithubDataModel newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(GithubDataModel oldItem, GithubDataModel newItem) {
                    return oldItem.equals(newItem);
                }
            };


    static class RepoDataViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtId;
        private final TextView txtName;
        private final TextView txtDescription;
        private final TextView txtOpenIssuesCount;
        private final TextView txtLicense;
        private final TextView txtPermissions;

        public RepoDataViewHolder(View view) {
            super(view);
            txtId = itemView.findViewById(R.id.txtId);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtOpenIssuesCount = itemView.findViewById(R.id.txtOpenIssuesCount);
            txtLicense = itemView.findViewById(R.id.txtLicense);
            txtPermissions = itemView.findViewById(R.id.txtPermissions);

        }


    }


}
