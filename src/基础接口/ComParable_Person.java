package 基础接口;

public class ComParable_Person implements java.lang.Comparable<ComParable_Person> {
    private Integer id;
    private String name;
    private int age;

    public Integer getId() {
        return id;
    }

    public ComParable_Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ComParable_Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public ComParable_Person setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public int compareTo(ComParable_Person o) {
        /*
         * p1.compareTo(p2)
         * p1>p2:正整数,p1==p2:0,p1<p2:负整数
         */
        if (this.id>o.id) return 1;
        if (this.id==o.id) return 0;
        if (this.id<o.id) return -1;
        // 如果是o.id-this.id就是降序排序
        return this.id-o.id;
    }

    /**
     * 要和compareTo方法结果一致
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ComParable_Person){
            ComParable_Person other=(ComParable_Person) o;
            if (this.id==other.id){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /**
     * 要和equals判断一致
     * @return
     */
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return "ComParable_Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
