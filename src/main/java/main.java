public class main {
    public static void main(String[] args) {
        SkillRepository sR = new SkillRepository();

        System.out.println(sR.getById(1L));
        System.out.println("Полный список: " + sR.getAll());
        System.out.println("New Skill: " + sR.save(new Skill(null, "python")));
        System.out.println("Добавление: " + sR.getAll());
        System.out.println("Update Skill: " + sR.update(new Skill(3L, "python3")));
        System.out.println("Изменение: " + sR.getAll());
        sR.deleteById(3L);
        System.out.println("Удаление: "+sR.getAll());
    }
}


/*
1. Изменить генерацию id ++++
2. Изменить сигнатуру метода save и update ++++
3. Изменить delete ++++
4. Изменить модификаторы доступа в SkillRepository ++++
5. Добавить константу ++++
 */