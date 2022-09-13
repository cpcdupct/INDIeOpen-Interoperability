package com.cpcd.microservices.app.lti.microserviciolti.models.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UpdateLTIUserRequest implements Serializable {
    @NotBlank
    private String key_key;

    @NotBlank
    private String secret;

    public UpdateLTIUserRequest() {
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
}
