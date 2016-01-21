package it.polimi.diceH2020.WS4GSPN.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.polimi.diceH2020.WS4GSPN.model.SimulationsManager;

@Repository
public interface SimulationsManagerRepository extends CrudRepository<SimulationsManager, Long>{
	Iterable<SimulationsManager> findByModel(String model);
}

