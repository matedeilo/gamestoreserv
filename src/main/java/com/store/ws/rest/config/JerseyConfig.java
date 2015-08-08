package com.store.ws.rest.config;

import com.store.ws.rest.DeliveryResource;
import com.store.ws.rest.GameResource;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GameResource.class);
        register(DeliveryResource.class);
    }

}
