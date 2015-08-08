package com.store.ws.rest;

import com.store.dao.DeliveryDAO;
import com.store.ws.bean.DeliveryWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/delivery")
public class DeliveryResource {

    @Autowired
    DeliveryDAO deliveryDAO;

    @GET
    @Path("download/{userid}/{gameid}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeliveryWS getGameDetail(@PathParam("userid") String userId,
                                    @PathParam("gameid") String gameId) {
        deliveryDAO.saveDelivery(userId, gameId);
        DeliveryWS deliveryWS = new DeliveryWS();
        deliveryWS.setApkUrl("/test.apk");
        return deliveryWS;
    }

}
