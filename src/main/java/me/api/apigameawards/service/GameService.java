package me.api.apigameawards.service;

import java.util.List;

import org.springframework.stereotype.Service;

import me.api.apigameawards.domain.model.Game;

@Service
public interface GameService {

	List<Game> findAll();
	
	Game findById(Long id);
	
	void insert(Game game);
	
	void update(Long id, Game game);
	
	void delete(Long id);

	void vote(Long id);
}
