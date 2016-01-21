package it.polimi.diceH2020.WS4GSPN.repository;

import org.springframework.data.repository.CrudRepository;

import it.polimi.diceH2020.WS4GSPN.model.Simulation;
import it.polimi.diceH2020.WS4GSPN.model.SimulationsManager;

public interface SimulationsRepository extends CrudRepository<Simulation, Long> {
	Iterable<Simulation> findBySimulationsManager(SimulationsManager simulationsManager);
}
