package com.app.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.app.controller.FamilyController;
import com.app.controller.PartnerController;

@Component
@ApplicationPath("api_rest")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }
    private void registerEndpoints() {
    	register(FamilyController.class);
    	register(PartnerController.class);
    }
}
