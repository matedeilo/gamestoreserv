package com.store.ws.rest;

import com.store.models.Result;
import com.store.models.Subscription;
import com.store.models.SubscriptionRequest;
import com.store.models.User;
import com.store.repository.SubscriptionRepository;
import com.store.repository.UserRepository;
import io.iron.ironmq.Client;
import io.iron.ironmq.Message;
import io.iron.ironmq.Queue;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/subscription")
public class SubscriptionResource {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    UserRepository userRepository;

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public javax.ws.rs.core.Response createSubscription(SubscriptionRequest subscriptionRequest) throws IOException{

        getQueue().push(subscriptionRequest.getNumber() + "," + String.valueOf(subscriptionRequest.getType()));

        return javax.ws.rs.core.Response.status(200).build();
    }

    @GET
    @Path("sync")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Subscription> syncSubscription() throws IOException{

        Queue queue = getQueue();
        List<Subscription> subscriptions = new ArrayList<>();
        try {

            for (int i = 0; i < queue.getInfoAboutQueue().getSize() ; i++) {
                Message mensaje = queue.get();
                System.out.println(mensaje.getBody());
                String[] parts = mensaje.getBody().split(",");
                Subscription subscription = new Subscription();
                String number = parts[0];
                User user = userRepository.findByNumber(number);
                subscription.setUser_id(user.getId());
                subscription.setType(parts[1]);
                subscription.setStatus(1);
                subscriptions.add(subscription);
                subscriptionRepository.save(subscription);
            }

        } catch (IOException e) {
            throw new WebApplicationException(Response.SC_INTERNAL_SERVER_ERROR);
        }
        return subscriptions;
    }

    public Queue getQueue(){
        Client client = new Client("55bcdbc59802440006000031", "WthAlTc4S0NCsG4_D9ZDjQpa50c");
        Queue queue = client.queue("gamestore_queue2");
        return queue;
    }
}
