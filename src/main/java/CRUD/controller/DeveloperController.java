package CRUD.controller;

import CRUD.model.Developer;
import CRUD.repository.DeveloperRepository;
import CRUD.repository.gson.JsonDeveloperRepositoryImpl;

import java.util.List;

public class DeveloperController {
    private final DeveloperRepository devRepo= new JsonDeveloperRepositoryImpl();

    public Developer getById(Long id){
        return devRepo.getById(id);
    }
    public List<Developer> getAll(){
        return devRepo.getAll();
    }
    public Developer save(Developer developer){
        return devRepo.save(developer);
    }
    public Developer update(Developer developer){
        return devRepo.update(developer);
    }
    public void deleteById(Long id){
        devRepo.deleteById(id);
    }
}
