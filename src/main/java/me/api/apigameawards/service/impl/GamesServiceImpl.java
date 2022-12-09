package me.api.apigameawards.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import me.api.apigameawards.domain.model.Game;
import me.api.apigameawards.domain.repository.GameRepository;
import me.api.apigameawards.service.GameService;
import me.api.apigameawards.service.exception.BusinessException;
import me.api.apigameawards.service.exception.NoContentException;

@Service
public class GamesServiceImpl implements GameService{

	@Autowired
	private GameRepository repository;
	
	@Override
	public List<Game> findAll() {
		List<Game> games = repository.findAll(Sort.by(Direction.DESC, "votes"));
		return games;
	}

	@Override
	public Game findById(Long id) {
		Optional<Game> game = repository.findById(id);
		return game.orElseThrow(() -> new NoContentException());
	}

	@Override
	public void insert(Game game) {
		if(game.getId() != null) {
			throw new BusinessException("O ID é diferente de NULL na inclusão.");
		}
		repository.save(game);
	}

	@Override
	public void update(Long id, Game game) {
		Game gameDb = findById(id);
		if ( gameDb.getId().equals(gameDb.getId())) {
			repository.save(game);
		} else {
			throw new BusinessException("Os IDs para alteração são divergentes.");
		}
		
	}

	@Override
	public void delete(Long id) {
		Game gameDb = findById(id);
		repository.deleteById(id);
	}

	@Override
	public void vote(Long id) {
		Game gameDb = findById(id);
		gameDb.setVotes(gameDb.getVotes() + 1);
		
		update(id, gameDb);
	}

	
}
