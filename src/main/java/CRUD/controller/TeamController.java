package CRUD.controller;

import CRUD.model.Team;
import CRUD.repository.TeamRepository;
import CRUD.repository.gson.JsonTeamRepositoryImpl;

import java.util.List;

public class TeamController {
    private final TeamRepository teamRepo = new JsonTeamRepositoryImpl();

    public Team getById(Long id) {
        return teamRepo.getById(id);
    }

    public List<Team> getAll() {
        return teamRepo.getAll();
    }

    public Team save(Team team) {
        return teamRepo.save(team);
    }

    public Team update(Team team) {
        return teamRepo.update(team);
    }

    public void deleteById(Long id) {
        teamRepo.deleteById(id);
    }
}
