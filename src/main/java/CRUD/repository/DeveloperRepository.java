package CRUD.repository;

import CRUD.model.Developer;
import CRUD.model.Skill;
import CRUD.model.Team;
import CRUD.repository.gson.JsonDeveloperRepositoryImpl;
import CRUD.repository.gson.JsonTeamRepositoryImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface DeveloperRepository extends GenericRepository<Developer, Long>{

}
