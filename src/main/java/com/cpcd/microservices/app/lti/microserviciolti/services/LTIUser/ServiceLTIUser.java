package com.cpcd.microservices.app.lti.microserviciolti.services.LTIUser;

import com.cpcd.microservices.app.lti.microserviciolti.models.entity.LtiKey;
import com.cpcd.microservices.app.lti.microserviciolti.models.request.NewLTIUserRequest;

import java.util.Optional;

public interface ServiceLTIUser {
    public LtiKey crearNuevoUsuario(NewLTIUserRequest newLTIUserRequest) throws ServiceLTIUserException;

    public Optional<LtiKey> findByEmail(String email);

    public Optional<LtiKey> findByKey_key(String keykey);

    public LtiKey update(LtiKey ltiKey);

    public void deleteByEmail(String email);
}
