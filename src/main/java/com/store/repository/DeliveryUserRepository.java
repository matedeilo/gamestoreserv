package com.store.repository;

import com.store.models.DeliveryUser;
import com.store.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryUserRepository extends CrudRepository<DeliveryUser, Integer>
{

}
