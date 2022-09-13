package com.cpcd.microservices.app.lti.microserviciolti.controller;

import com.cpcd.microservices.app.lti.microserviciolti.clients.INDIeOpenFeingClient;
import com.cpcd.microservices.app.lti.microserviciolti.models.entity.LtiKey;
import com.cpcd.microservices.app.lti.microserviciolti.services.LTIUser.ServiceLTIUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/access")
public class ControladorAccess {
    @Autowired
    private ServiceLTIUser serviceLTIUser;

    @Autowired
    private INDIeOpenFeingClient indIeOpenFeingClient;

    private Logger log = LoggerFactory.getLogger(ControladorAccess.class);

    @GetMapping("/{keykey}/{idunidad}/{origin}")
    public Boolean  acceptUserLTI(@PathVariable String keykey, @PathVariable String idunidad, @PathVariable String origin) {
        Optional<LtiKey> cu = serviceLTIUser.findByKey_key(keykey);

        if (cu.isEmpty()) {
            return false;
        } else {
            LtiKey ltiKey = cu.get();
            if (idunidad.contains("COURSE_")){
                String[] lticaden = idunidad.split("_");
                return indIeOpenFeingClient.getAuthorizationForCourse(origin, lticaden[1],ltiKey.getEmail());
            }else{
                return indIeOpenFeingClient.getAuthorizationForUnit(origin, idunidad,ltiKey.getEmail());
            }
        }
    }
}
