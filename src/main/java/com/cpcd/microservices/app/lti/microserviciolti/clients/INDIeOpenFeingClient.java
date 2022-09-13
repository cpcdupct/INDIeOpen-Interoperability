package com.cpcd.microservices.app.lti.microserviciolti.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="${feign.indieopen}")
public interface INDIeOpenFeingClient {
    @GetMapping("/access/{unitResource}/unit")
    public Boolean getAuthorizationForUnit(@RequestHeader("X-TenantID") String tenant, @PathVariable String unitResource, @RequestParam String email);

    @GetMapping("/access/{courseResource}/course")
    public Boolean getAuthorizationForCourse(@RequestHeader("X-TenantID") String tenant, @PathVariable String courseResource, @RequestParam String email);

}
