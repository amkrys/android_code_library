package com.krys.codelibrary.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserResponse {

    @SerializedName("items")
    @Expose
    public List<Item> items = new ArrayList<>();
    @SerializedName("has_more")
    @Expose
    public Boolean hasMore;
    @SerializedName("backoff")
    @Expose
    public Integer backoff;
    @SerializedName("quota_max")
    @Expose
    public Integer quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    public Integer quotaRemaining;

    public class Item {

        @SerializedName("owner")
        @Expose
        public Owner owner;
        @SerializedName("is_accepted")
        @Expose
        public Boolean isAccepted;
        @SerializedName("score")
        @Expose
        public Integer score;
        @SerializedName("last_activity_date")
        @Expose
        public Integer lastActivityDate;
        @SerializedName("last_edit_date")
        @Expose
        public Integer lastEditDate;
        @SerializedName("creation_date")
        @Expose
        public Integer creationDate;
        @SerializedName("answer_id")
        @Expose
        public Integer answerId;
        @SerializedName("question_id")
        @Expose
        public Integer questionId;
        @SerializedName("content_license")
        @Expose
        public String contentLicense;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item item = (Item) o;
            return Objects.equals(owner, item.owner) &&
                    Objects.equals(isAccepted, item.isAccepted) &&
                    Objects.equals(score, item.score) &&
                    Objects.equals(lastActivityDate, item.lastActivityDate) &&
                    Objects.equals(lastEditDate, item.lastEditDate) &&
                    Objects.equals(creationDate, item.creationDate) &&
                    Objects.equals(answerId, item.answerId) &&
                    Objects.equals(questionId, item.questionId) &&
                    Objects.equals(contentLicense, item.contentLicense);
        }

        @Override
        public int hashCode() {
            return Objects.hash(owner, isAccepted, score, lastActivityDate, lastEditDate, creationDate, answerId, questionId, contentLicense);
        }
    }

    public class Owner {

        @SerializedName("reputation")
        @Expose
        public Integer reputation;
        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("user_type")
        @Expose
        public String userType;
        @SerializedName("profile_image")
        @Expose
        public String profileImage;
        @SerializedName("display_name")
        @Expose
        public String displayName;
        @SerializedName("link")
        @Expose
        public String link;
        @SerializedName("accept_rate")
        @Expose
        public Integer acceptRate;

    }

}


