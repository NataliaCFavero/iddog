package com.nataliafavero.iddog.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by nataliafavero on 06/03/18.
 */

public class SignupRequest {

    private String email;

    public SignupRequest(String email) {
        this.email = email;
    }
}
