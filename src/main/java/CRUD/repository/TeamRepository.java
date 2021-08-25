package CRUD.repository;

import CRUD.model.Team;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamRepository {
    private static final String TEAMS_JSON = "teams.json";
    private final String path = "src\\main\\resources\\";

    public Team getById(Long id) {

        return readJSON().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Team> getAll() {

        return readJSON();
    }

    public Team save(Team team) {
        if (team.getId() == null) {
            team.setId(generateMaxId(readJSON()));
        }
        writeFile(TEAMS_JSON, toJSON(addTeam(readJSON(), team)));
        return readJSON().stream().filter(n -> n.getName().equals(team.getName())).findFirst().orElse(null);
    }

    public Team update(Team team) {
        writeFile(TEAMS_JSON, toJSON(changeTeam(readJSON(), team)));
        return readJSON().stream().filter(n -> n.getName().equals(team.getName())).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        writeFile(TEAMS_JSON, toJSON(readJSON().stream().filter(n -> !(n.getId().equals(id))).collect(Collectors.toList())));
    }


    //      Читает файл (ПОЛНОСТЬЮ)
    private String readFile(String fileName) {
        String fileContent = "";
        try (FileReader fr = new FileReader(getPath(fileName))) {
            while (fr.ready()) {
                fileContent += (char) fr.read();
            }
        } catch (IOException e) {
            System.out.println("Error while file reading: " + e);
        }

        return fileContent;
    }

    //        Записывает файл (ПОЛНОСТЬЮ)
    private void writeFile(String fileName, String jsonObj) {
        try (FileWriter fw = new FileWriter(getPath(fileName))) {
            fw.write(jsonObj);
        } catch (IOException e) {
            System.out.println("Error while file writing: " + e);
        }
    }

    private String getPath(String fileName) {
        File f = new File(path + "\\" + fileName);
        return f.getAbsolutePath();
    }

    private String toJSON(List<Team> list) {
        Gson gson = new Gson();
        String team = gson.toJson(list);
        return team;
    }

    private List<Team> addTeam(List<Team> list, Team team) {
        list.add(team);
        return list;
    }

    private List<Team> changeTeam(List<Team> list, Team team) {
        list.stream().filter(s -> s.getId().equals(team.getId())).findFirst().orElse(null).setName(team.getName());
        return list;
    }

    private List<Team> readJSON() {
        Type targetClassType = new TypeToken<ArrayList<Team>>() {
        }.getType();
        return new Gson().fromJson(readFile(TEAMS_JSON), targetClassType);
    }

    private Long generateMaxId(List<Team> list) {
        if (list.size() == 0){
            return 1L;
        }
        return list.get(list.size() - 1).getId() + 1;
    }
}