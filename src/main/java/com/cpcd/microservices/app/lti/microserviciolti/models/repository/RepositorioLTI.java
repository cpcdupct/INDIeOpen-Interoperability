package com.cpcd.microservices.app.lti.microserviciolti.models.repository;

import com.cpcd.microservices.app.lti.microserviciolti.models.entity.LtiKey;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RepositorioLTI extends CrudRepository<LtiKey, String> {

    public Optional<LtiKey> findByEmail(String email);

    public Optional<LtiKey> findByKeykey(String email);

    boolean existsByEmail(String email);

    boolean existsByKeykey(String key_key);

    void deleteByEmail(String email);
}
