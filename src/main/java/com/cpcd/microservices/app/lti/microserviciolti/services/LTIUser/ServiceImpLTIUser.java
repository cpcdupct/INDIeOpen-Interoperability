package com.cpcd.microservices.app.lti.microserviciolti.services.LTIUser;

import com.cpcd.microservices.app.lti.microserviciolti.models.repository.RepositorioLTI;
import com.cpcd.microservices.app.lti.microserviciolti.models.entity.LtiKey;
import com.cpcd.microservices.app.lti.microserviciolti.models.request.NewLTIUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServiceImpLTIUser implements ServiceLTIUser{
    @Autowired
    private RepositorioLTI repositorioLTI;


    @Override
    @Transactional
    public LtiKey crearNuevoUsuario(NewLTIUserRequest newLTIUserRequest) throws ServiceLTIUserException{
        if (repositorioLTI.existsByEmail(newLTIUserRequest.getEmail()) || repositorioLTI.existsByKeykey(newLTIUserRequest.getKey_key()))
            throw new ServiceLTIUserException("Datos duplicados: " + newLTIUserRequest.getEmail() +  "/" + newLTIUserRequest.getKey_key());

        LtiKey ltiKey = new LtiKey(newLTIUserRequest.getKey_key(),newLTIUserRequest.getSecret(),newLTIUserRequest.getEmail());
        ltiKey = repositorioLTI.save(ltiKey);
        return ltiKey;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LtiKey> findByEmail(String email) {
        return repositorioLTI.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LtiKey> findByKey_key(String keykey) {
        return repositorioLTI.findByKeykey(keykey);
    }

    @Override
    @Transactional
    public LtiKey update(LtiKey ltiKey) {
        return repositorioLTI.save(ltiKey);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        repositorioLTI.deleteByEmail(email);
    }

}
