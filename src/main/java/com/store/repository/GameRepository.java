package com.store.repository;

import com.store.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer>{

    List<Game> findByCategoryId(int categoryId);

}
