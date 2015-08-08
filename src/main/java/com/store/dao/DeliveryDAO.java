package com.store.dao;

import com.store.models.DeliveryUser;
import com.store.repository.DeliveryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DeliveryDAO {

    @Autowired
    DeliveryUserRepository deliveryUserRepository;

    public void saveDelivery(String userId, String gameId){
        DeliveryUser deliveryUser = new DeliveryUser();
        deliveryUser.setUser_id(Integer.parseInt(userId));
        deliveryUser.setGame_id(Integer.parseInt(gameId));
        deliveryUser.setDate(new Date());
        deliveryUserRepository.save(deliveryUser);
    }

}
