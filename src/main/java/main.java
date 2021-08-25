import CRUD.model.Developer;
import CRUD.model.Skill;
import CRUD.model.Team;
import CRUD.repository.DeveloperRepository;
import CRUD.repository.SkillRepository;
import CRUD.repository.TeamRepository;

public class main {
    public static void main(String[] args) {
        testAll();
    }

    public static void testAll(){
        skillTest();
        devTest();
        teamTest();
    }

    public static void skillTest() {
        System.out.println("SKILL:");
        SkillRepository sR = new SkillRepository();
        System.out.println(sR.getById(1L));
        System.out.println("Полный список: " + sR.getAll());
        System.out.println("New Skill: " + sR.save(new Skill(null, "python")));
        System.out.println("Добавление: " + sR.getAll());
        System.out.println("Update Skill: " + sR.update(new Skill(3L, "python3")));
        System.out.println("Изменение: " + sR.getAll());
        sR.deleteById(3L);
        System.out.println("Удаление: " + sR.getAll());
    }
    public static void devTest() {
        System.out.println("DEV:");
        DeveloperRepository dR = new DeveloperRepository();
        System.out.println(dR.getById(1L));
        System.out.println("Полный список: " + dR.getAll());
        System.out.println("New Dev: " + dR.save(new Developer(null, "python", "c++", null)));
        System.out.println("Добавление: " + dR.getAll());
        System.out.println("Update Dev: " + dR.update(new Developer(3L, "python3","c++3", null )));
        System.out.println("Изменение: " + dR.getAll());
        dR.deleteById(3L);
        System.out.println("Удаление: " + dR.getAll());
    }
    public static void teamTest() {
        System.out.println("TEAM:");
        TeamRepository tR = new TeamRepository();
        System.out.println(tR.getById(1L));
        System.out.println("Полный список: " + tR.getAll());
        System.out.println("New Team: " + tR.save(new Team(null, "python", null)));
        System.out.println("Добавление: " + tR.getAll());
        System.out.println("Update Team: " + tR.update(new Team(3L, "python3", null)));
        System.out.println("Изменение: " + tR.getAll());
        tR.deleteById(3L);
        System.out.println("Удаление: " + tR.getAll());
    }
}