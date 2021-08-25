package CRUD.repository;

import CRUD.model.Skill;
import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SkillRepository {
    private static final String SKILLS_JSON = "skills.json";
    private final String path = "src\\main\\resources\\";

    public Skill getById(Long id) {

        return readJSON().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Skill> getAll() {

        return readJSON();
    }

    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            skill.setId(generateMaxId(readJSON()));
        }
        writeFile(SKILLS_JSON, toJSON(addSkill(readJSON(), skill)));
        return readJSON().stream().filter(n -> n.getName().equals(skill.getName())).findFirst().orElse(null);
    }

    public Skill update(Skill skill) {
        writeFile(SKILLS_JSON, toJSON(changeSkill(readJSON(), skill)));
        return readJSON().stream().filter(n -> n.getName().equals(skill.getName())).findFirst().orElse(null);
    }

    public void deleteById(Long id) {
        writeFile(SKILLS_JSON, toJSON(readJSON().stream().filter(n -> !(n.getId().equals(id))).collect(Collectors.toList())));
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

    private String toJSON(List<Skill> list) {
        Gson gson = new Gson();
        String skill = gson.toJson(list);
        return skill;
    }

    private List<Skill> addSkill(List<Skill> list, Skill skill) {
        list.add(skill);
        return list;
    }

    private List<Skill> changeSkill(List<Skill> list, Skill skill) {
        list.stream().filter(s -> s.getId().equals(skill.getId())).findFirst().orElse(null).setName(skill.getName());
        return list;
    }

    private List<Skill> readJSON() {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        return new Gson().fromJson(readFile(SKILLS_JSON), targetClassType);
    }

    private Long generateMaxId(List<Skill> list) {
        if (list.size() == 0){
            return 1L;
        }
        return list.get(list.size() - 1).getId() + 1;
    }
}
