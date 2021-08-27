package CRUD.controller;

import CRUD.model.Skill;
import CRUD.repository.SkillRepository;
import CRUD.repository.gson.JsonSkillRepositoryImpl;

import java.util.List;

public class SkillController {
    private final SkillRepository skillRepo = new JsonSkillRepositoryImpl();

    public Skill getById(Long id) {
        return skillRepo.getById(id);
    }

    public List<Skill> getAll() {
        return skillRepo.getAll();
    }

    public Skill save(Skill skill) {
        return skillRepo.save(skill);
    }

    public Skill update(Skill skill) {
        return skillRepo.update(skill);
    }

    public void deleteById(Long id) {
        skillRepo.deleteById(id);
    }
}
