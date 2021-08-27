package CRUD.repository.gson;

import CRUD.model.Developer;
import CRUD.repository.DeveloperRepository;
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

public class JsonDeveloperRepositoryImpl implements DeveloperRepository {
    private static final String DEVS_JSON = "developers.json";
    private final String path = "src\\main\\resources\\";

    public Developer getById(Long id) {

        return readJSON().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Developer> getAll() {

        return readJSON();
    }

    public Developer save(Developer developer) {
        if (developer.getId() == null) {
            developer.setId(generateMaxId(readJSON()));
        }
        writeFile(DEVS_JSON, toJSON(addDev(readJSON(), developer)));
        return readJSON().stream().filter(n -> n.getFirstName().equals(developer.getFirstName()))
                .filter(n -> n.getLastName().equals(developer.getLastName())).findFirst().orElse(null);
    }

    public Developer update(Developer developer) {
        writeFile(DEVS_JSON, toJSON(changeDev(readJSON(), developer)));
        return readJSON().stream().filter(n -> n.getFirstName().equals(developer.getFirstName()))
                .filter(n -> n.getLastName().equals(developer.getLastName())).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        writeFile(DEVS_JSON, toJSON(readJSON().stream().filter(n -> !(n.getId().equals(id))).collect(Collectors.toList())));
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

    private String toJSON(List<Developer> list) {
        Gson gson = new Gson();
        String dev = gson.toJson(list);
        return dev;
    }

    private List<Developer> addDev(List<Developer> list, Developer developer) {
        list.add(developer);
        return list;
    }

    private List<Developer> changeDev(List<Developer> list, Developer developer) {
        list.stream().filter(s -> s.getId().equals(developer.getId())).findFirst().orElse(null).setFirstName(developer.getFirstName());
        list.stream().filter(s -> s.getId().equals(developer.getId())).findFirst().orElse(null).setLastName(developer.getLastName());
        return list;
    }

    private List<Developer> readJSON() {
        Type targetClassType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        return new Gson().fromJson(readFile(DEVS_JSON), targetClassType);
    }

    public Long generateMaxId(List<Developer> list) {
        return list.stream().mapToLong(Developer::getId).max().getAsLong()+1;
    }
}
