package com.cpcd.microservices.app.lti.microserviciolti.models.entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="lti_key")
public class LtiKey  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int key_id;

    @Column(name = "key_key")
    private String keykey;

    private String key_sha256;

    private String secret;

    private String email;



    public LtiKey(){

    }



    public LtiKey(String key_key, String secret, String email) {
        this.keykey = key_key;
        this.secret = secret;
        this.email = email;
        this.key_sha256 = DigestUtils.sha256Hex(key_key);

    }

    public int getKey_id() {
        return key_id;
    }

    public void setKey_id(int key_id) {
        this.key_id = key_id;
    }

    public String getKey_key() {
        return keykey;
    }

    public void setKey_key(String key_key) {
        this.keykey = key_key;
    }

    public String getKey_sha256() {
        return key_sha256;
    }

    public void setKey_sha256(String key_sha256) {
        this.key_sha256 = key_sha256;
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

    public void setEmail(String dni) {
        this.email = email;
    }
}
