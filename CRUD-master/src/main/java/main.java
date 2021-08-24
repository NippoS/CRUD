public class main {
    public static void main(String[] args) {
        skillTest();
    }

    public static void skillTest() {
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
}


/*
1. getById ++++
2. getAll ++++
3. save ++++
4. update
5. delete ++++
 */

/*
1. getById ++++
2. getAll ++++
3. save ++++
4. update ++++
5. delete ++++
6. Ошибки у class Team
 */