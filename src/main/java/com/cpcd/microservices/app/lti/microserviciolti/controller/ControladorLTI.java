package com.cpcd.microservices.app.lti.microserviciolti.controller;

import com.cpcd.microservices.app.lti.microserviciolti.models.entity.LtiKey;
import com.cpcd.microservices.app.lti.microserviciolti.models.request.NewLTIUserRequest;
import com.cpcd.microservices.app.lti.microserviciolti.models.request.UpdateLTIUserRequest;
import com.cpcd.microservices.app.lti.microserviciolti.services.LTIUser.ServiceLTIUser;
import com.cpcd.microservices.app.lti.microserviciolti.services.LTIUser.ServiceLTIUserException;
import com.cpcd.microservices.app.servicescommons.token.ModelToken;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class ControladorLTI {
    @Autowired
    private ServiceLTIUser serviceLTIUser;

    private Logger log = LoggerFactory.getLogger(ControladorLTI.class);

    @GetMapping("/email")
    public ResponseEntity<?> devolverUsuarioLTI(@RequestHeader("Authorization") String token) {
        ModelToken jwtService = new ModelToken(token);
        String email = jwtService.getCorreo();
        Optional<LtiKey> cu = serviceLTIUser.findByEmail(email);

        if (cu.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cu.get());
        }
    }

    @PostMapping
    public ResponseEntity<?> addUsuario(@RequestHeader("Authorization") String token,
                                        @Valid @RequestBody NewLTIUserRequest newLTIUserRequest,
                                        BindingResult result) {
        ModelToken jwtService = new ModelToken(token);
        String email = jwtService.getCorreo();
        if (result.hasErrors()) {
            return validar(result);
        }

        try {
            newLTIUserRequest.setEmail(email);
            LtiKey cu = serviceLTIUser.crearNuevoUsuario(newLTIUserRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(cu);
        } catch (ServiceLTIUserException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editarLTUUsuario(@RequestHeader("Authorization") String token,
                                              @Valid @RequestBody UpdateLTIUserRequest request, BindingResult result) {
        ModelToken jwtService = new ModelToken(token);
        String email = jwtService.getCorreo();
        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<LtiKey> cu = serviceLTIUser.findByEmail(email);

        if (cu.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LtiKey ltiKey = cu.get();
        ltiKey.setSecret(request.getSecret());
        ltiKey.setKey_sha256(DigestUtils.sha256Hex(request.getKey_key()));
        ltiKey.setKey_key(request.getKey_key());
        serviceLTIUser.update(ltiKey);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> borrarUsuario(@RequestHeader("Authorization") String token) {
        ModelToken jwtService = new ModelToken(token);
        String email = jwtService.getCorreo();
        serviceLTIUser.deleteByEmail(email);
        return ResponseEntity.noContent().build();

    }

    protected ResponseEntity<?> validar(BindingResult result) {

        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
