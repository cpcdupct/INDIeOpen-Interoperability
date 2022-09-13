package com.cpcd.microservices.app.lti.microserviciolti.models.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class NewLTIUserRequest implements Serializable {

    @NotBlank
    private String key_key;

    @NotBlank
    private String secret;

    @NotBlank
    private String email;

    public NewLTIUserRequest() {
    }

    public String getKey_key() {
        return key_key;
    }

    public void setKey_key(String key_key) {
        this.key_key = key_key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
