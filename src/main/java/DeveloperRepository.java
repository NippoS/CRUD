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

public class DeveloperRepository {
    private static final String DEVELOPERS_JSON = "developers.json";
    private final String path = "src\\main\\resources\\";

    public Developer getById(Long id) {

        return readJSON().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Developer> getAll() {

        return readJSON();
    }

    public Developer save(Developer developer) {
        if (developer.getId() == null) {
            developer.setId(1L);
            getId(developer);
        }
        writeFile(DEVELOPERS_JSON, toJSON(addSkill(readJSON(), developer)));
        return readJSON().stream().filter(n -> n.getFirstName().equals(developer.getFirstName()))
                .filter(n -> n.getLastName().equals(developer.getLastName())).findFirst().orElse(null);
    }

    public Developer update(Developer developer) {
        writeFile(DEVELOPERS_JSON, toJSON(changeSkill(readJSON(), developer)));
        return readJSON().stream().filter(n -> n.getName().equals(developer.getName())).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        writeFile(DEVELOPERS_JSON, toJSON(readJSON().stream().filter(n -> !(n.getId().equals(id))).collect(Collectors.toList())));
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
    private String toJSON(List<Developer> list) {
        Gson gson = new Gson();
        String developer = gson.toJson(list);
        return developer;
    }

    // Добавление skill
    private List<Developer> addSkill(List<Developer> list, Developer developer) {
        list.add(developer);
        return list;
    }

    //Апдейт skill
    private List<Developer> changeSkill(List<Developer> list, Developer developer) {
        int idDeveloper = idCopy(list, developer);
        list.get(idDeveloper - 1).setName(developer.getName());
        return list;
    }

    // Определение Id
    private Developer getId(Developer developer) {
        if (idCopy(readJSON(), developer) != 0) {
            developer.setId(developer.getId() + 1);
            getId(developer);
        }
        return developer;
    }

    //    Проверка совпадения id у skill
    private int idCopy(List<Developer> list, Developer developer) {
        int idCopy = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(developer.getId())) {
                idCopy = list.indexOf(list.get(i)) + 1;
            }
        }
        return idCopy;
    }

    // Чтение файла
    private List<Developer> readJSON() {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        List<Developer> targetCollection = new Gson().fromJson(readFile(DEVELOPERS_JSON), targetClassType);
        return targetCollection;
    }
}
