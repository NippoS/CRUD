import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class SkillRepository {

    private final String path = "src\\main\\resources\\";

    public Skill getById(Long id) {

        return readJSON().stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Skill> getAll() {

        return readJSON();
    }

    public void save(Skill skill) {
        writeFile("skills.json", toJSON(addSkill(readJSON(), skill)));
    }

    public void update(Skill skill) {
        writeFile("skills.json", toJSON(changeSkill(readJSON(), skill)));
    }

    public void deleteById(Long id){
        writeFile("skills.json", toJSON(deleteSkill(readJSON(), id)));
    }





    //      Читает файл (ПОЛНОСТЬЮ)
    public String readFile(String fileName) {
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
    public void writeFile(String fileName, String jsonObj) {
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
    private String toJSON(List<Skill> list) {
        Gson gson = new Gson();
        String skill = gson.toJson(list);
        return skill;
    }

    // Добавление skill
    private List<Skill> addSkill(List<Skill> list, Skill skill) {
        list.add(skill);
        return list;
    }

    //Апдейт skill
    private List<Skill> changeSkill(List<Skill> list, Skill skill) {
        int idSkill = idCopy(list, skill);
        list.get(idSkill - 1).setName(skill.getName());
        return list;
    }

    private List<Skill> deleteSkill(List<Skill> list, Long id){
        list.remove(id.intValue()-1);
        return list;
    }

    public int getId() {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        List<Skill> targetCollection = new Gson().fromJson(readFile("skills.json"), targetClassType);
        return targetCollection.size() + 1;
    }

    //    Проверка совпадения id у skill
    private int idCopy(List<Skill> list, Skill skill) {
        int idCopy = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(skill.getId())) {
                idCopy = list.indexOf(list.get(i)) + 1;
            }
        }
        return idCopy;
    }

    // Чтение файла
    public List<Skill> readJSON() {
        Type targetClassType = new TypeToken<ArrayList<Skill>>() {
        }.getType();
        List<Skill> targetCollection = new Gson().fromJson(readFile("skills.json"), targetClassType);
        return targetCollection;
    }
}
