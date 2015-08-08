package com.store.repository;

import com.store.models.Game;
import com.store.models.Subscription;
import com.store.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{

    User findByNumber(String number);

}
