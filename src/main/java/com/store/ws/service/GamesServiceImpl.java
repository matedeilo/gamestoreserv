package com.store.ws.service;


import com.store.dao.GameDAO;
import com.store.ws.bean.GameWS;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "com.store.ws.service.GamesService", serviceName="gamesService")
public class GamesServiceImpl implements GamesService{

    private GameDAO gameDAO;

    public void setGameDAO(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    @Override
    public List<GameWS> getGames(String categoryId) {
        System.out.println(categoryId);

        return gameDAO.findGamesByCategory(categoryId);
    }

}