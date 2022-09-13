package com.cpcd.microservices.app.lti.microserviciolti.models.request;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AccessLTIUser implements Serializable {

    @NotBlank
    private String idunidad;

    @NotBlank
    private String origin;

    public AccessLTIUser() {
    }

    public String getIdunidad() {
        return idunidad;
    }

    public void setIdunidad(String idunidad) {
        this.idunidad = idunidad;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
