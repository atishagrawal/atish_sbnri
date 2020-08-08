
package com.example.atish_sbnri.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Actual table structure and model (based on JSON Response from the API)
 */
@Entity(tableName = "repo_data")
public class GithubDataModel {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("open_issues_count")
    @Expose
    private Integer openIssuesCount;

    //License
    @SerializedName("license")
    @Expose
    @Embedded
    private License license;

    //Permissions
    @SerializedName("permissions")
    @Expose
    @Embedded
    private Permissions permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }


    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public static class License {

        @SerializedName("name")
        @Expose
        private String licenseName;


        public String getLicenseName() {
            return licenseName;
        }

        public void setLicenseName(String licenseName) {
            this.licenseName = licenseName;
        }
    }

    public static class Permissions {

        @SerializedName("admin")
        @Expose
        private Boolean admin;
        @SerializedName("push")
        @Expose
        private Boolean push;

        public Boolean getAdmin() {
            return admin;
        }

        public void setAdmin(Boolean admin) {
            this.admin = admin;
        }

        public Boolean getPush() {
            return push;
        }

        public void setPush(Boolean push) {
            this.push = push;
        }

        public Boolean getPull() {
            return pull;
        }

        public void setPull(Boolean pull) {
            this.pull = pull;
        }

        @SerializedName("pull")
        @Expose
        private Boolean pull;

    }

}
