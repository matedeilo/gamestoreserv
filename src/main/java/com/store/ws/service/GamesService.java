package com.store.ws.service;

import com.store.ws.bean.GameWS;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface GamesService {

    List<GameWS> getGames(String categoryId);

}
