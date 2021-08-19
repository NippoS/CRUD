public class main {
    public static void main(String[] args) {
        SkillRepository sR = new SkillRepository();

        System.out.println(sR.getById(1L));
        System.out.println("Полный список: "+sR.getAll());
        sR.save(new Skill(null, "python"));
        System.out.println("Добавление: "+sR.getAll());
        sR.update(new Skill(3L, "python3"));
        System.out.println("Изменение: "+sR.getAll());
        sR.deleteById(3L);
        System.out.println("Удаление: "+sR.getAll());
    }
}
