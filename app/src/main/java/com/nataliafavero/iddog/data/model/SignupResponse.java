package com.nataliafavero.iddog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nataliafavero on 06/03/18.
 */

public class SignupResponse {

    private User user;

    public User getUser(){
        return user;
    }

    public class User {
        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("email")
        @Expose
        private String email;

        @SerializedName("token")
        @Expose
        private String token;

        public String getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getToken() {
            return token;
        }
    }
}
