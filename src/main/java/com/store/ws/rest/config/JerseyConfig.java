package com.store.ws.rest.config;

import com.store.ws.rest.DeliveryResource;
import com.store.ws.rest.GameResource;
import com.store.ws.rest.SubscriptionResource;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(GameResource.class);
        register(DeliveryResource.class);
        register(SubscriptionResource.class);
    }

}
