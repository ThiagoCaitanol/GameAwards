package me.api.apigameawards.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.api.apigameawards.domain.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
