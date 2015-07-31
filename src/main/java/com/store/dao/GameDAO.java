package com.store.dao;

import com.store.models.Game;
import com.store.repository.GameRepository;
import com.store.ws.bean.GameWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class GameDAO {

    @Autowired
    private GameRepository gameRepository;

    public List<GameWS> findGamesByCategory(String categoryId){

        List<Game> gamesList = gameRepository.findByCategoryId(Integer.parseInt(categoryId));
        List<GameWS> gameWsList = new ArrayList<>();
        for (Game game : gamesList) {
            GameWS gameWS = new GameWS();
            gameWS.setId(game.getId());
            gameWS.setName(game.getName());
            gameWS.setLicensorName(game.getLicensorName());
            gameWS.setDefaultImageUrl(game.getDefaultImageUrl());
            gameWsList.add(gameWS);
        }

        return gameWsList;
    }

    public GameWS findGameById(String gameId){
        Game gameModel = gameRepository.findOne(Integer.parseInt(gameId));

        if (gameModel == null) {
            return null;
        }

        GameWS gameWS = new GameWS();
        gameWS.setId(gameModel.getId());
        gameWS.setName(gameModel.getName());
        gameWS.setDefaultImageUrl(gameModel.getDefaultImageUrl());
        gameWS.setLicensorName(gameModel.getLicensorName());
        gameWS.setDescription(gameModel.getDescription());

        return gameWS;
    }
}
