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
    private static final String TEAM_JSON = "teams.json";
    private final String path = "src\\main\\resources\\";

    public Team getById(Long id) {

        return readJSON().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Team> getAll() {

        return readJSON();
    }

    public Team save(Team team) {
        if (team.getId() == null) {
            team.setId(1L);
            getId(team);
        }
        writeFile(TEAM_JSON, toJSON(addSkill(readJSON(), team)));
        return readJSON().stream().filter(n -> n.getName().equals(team.getName())).findFirst().orElse(null);
    }

    public Team update(Team team) {
        writeFile(TEAM_JSON, toJSON(changeSkill(readJSON(), team)));
        return readJSON().stream().filter(n -> n.getName().equals(team.getName())).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        writeFile(TEAM_JSON, toJSON(readJSON().stream().filter(n -> !(n.getId().equals(id))).collect(Collectors.toList())));
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

    // Конвертация в JSON Объект
    private String toJSON(List<Team> list) {
        Gson gson = new Gson();
        String team = gson.toJson(list);
        return team;
    }

    // Добавление skill
    private List<Team> addSkill(List<Team> list, Team team) {
        list.add(team);
        return list;
    }

    //Апдейт skill
    private List<Team> changeSkill(List<Team> list, Team team) {
        int idSkill = idCopy(list, team);
        list.get(idSkill - 1).setName(team.getName());
        return list;
    }

    // Определение Id
    private Team getId(Team team) {
        if (idCopy(readJSON(), team) != 0) {
            team.setId(team.getId() + 1);
            getId(team);
        }
        return team;
    }

    //    Проверка совпадения id у skill
    private int idCopy(List<Team> list, Team team) {
        int idCopy = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(team.getId())) {
                idCopy = list.indexOf(list.get(i)) + 1;
            }
        }
        return idCopy;
    }

    // Чтение файла
    private List<Team> readJSON() {
        Type targetClassType = new TypeToken<ArrayList<Team>>() {
        }.getType();
        List<Team> targetCollection = new Gson().fromJson(readFile(TEAM_JSON), targetClassType);
        return targetCollection;
    }
}
