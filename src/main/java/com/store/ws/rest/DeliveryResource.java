package com.store.ws.rest;

import com.store.dao.DeliveryDAO;
import com.store.models.User;
import com.store.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    @GET
    @Path("download/{userid}/{gameid}")
    @Produces(MediaType.APPLICATION_JSON)
    public DeliveryWS getGameDetail(@PathParam("userid") String userId,
                                    @PathParam("gameid") String gameId) {
        User user = userRepository.findByNumber(userId);
        deliveryDAO.saveDelivery(String.valueOf(user.getId()), gameId);
        DeliveryWS deliveryWS = new DeliveryWS();
        deliveryWS.setApkUrl("https://www.apkfiles.com/download/135550/banana-kong.apk?key=b42d564a352a8a37cb94078a9ee3d0c9");
        return deliveryWS;
    }

}
