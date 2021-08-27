package CRUD.repository;

import CRUD.model.Skill;
import CRUD.repository.gson.JsonSkillRepositoryImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


public interface SkillRepository extends GenericRepository<Skill, Long> {

}
