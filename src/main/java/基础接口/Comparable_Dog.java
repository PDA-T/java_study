package »ù´¡½Ó¿Ú;

public class Comparable_Dog {
    private Integer id;
    private String name;
    private int age;

    public Integer getId() {
        return id;
    }

    public Comparable_Dog setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Comparable_Dog setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Comparable_Dog setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "Comparable_Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
