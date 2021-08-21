public class Skill {
    private Long id;
    private String name;

    public Skill(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Skill() {
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
