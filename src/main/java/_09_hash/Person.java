package _09_hash;

import java.util.HashMap;
import java.util.Objects;

public class Person {
    private int age;
    private float height;
    private String name;

    public Person() {}

    public Person(int age, float height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", height=" + height +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode(){

        int hashCode = Integer.hashCode(this.age);
        hashCode = hashCode * 31 + Float.hashCode(this.height);
        hashCode = hashCode * 31 + (this.name != null ? this.name.hashCode() : 0);

        return hashCode;
    }

    /**
     * 重写equals方法的原因：当发生hash 冲突时，用来比较两个key是否相同
     * 假设又一个index的位置上链接了多个node(bucket)
     * 这时我们新插入一个数据，让它生成自己的hash code, 生成的hash code 通过reminder的运算，得到bucket array所对应的下标
     * 假设新插入数据的key 所对应的index和之前的数据的key 所对应的index相同
     * 既然index相同，我们就需要拿到这个key 和 list上的多个node （bucket）所对应的key进行比较
     * 如果同一个key,则发生覆盖 i.e. p1 和 p3
     * 如果不同，则在list上进行追加 i.e. p2
     *
     * 在jdk的hashMap中，为啥不直接用hash code进行比较，进而确定他们不是同一个对象呢？
     *
     * 不能，因为
     * 1。 不同类型的数据可能对应相同的hash code: "Dal" --> 123; 123 --> 123
     * 2。 同一类型的数据，也有可能对应相同的hash code: 比如person ，有三个值。
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.age == person.age &&
                Float.compare(person.height, height) == 0 &&
                Objects.equals(name, person.name);
    }


    public static void main(String[] args) {
//        Person p1 = new Person(54, 17.7f, "jeff");
//        Person p2 = new Person(45, 18.2f, "pony");
//        Person p3 = new Person(54, 17.7f, "jeff");
//
//        System.out.println(p1.hashCode());
//        System.out.println(p2.hashCode());
//        System.out.println(p3.hashCode());
//
//        HashMap<Person, Integer> map = new HashMap<>();
//        map.put(p1, 999);
//        map.put(p2, 222);
//        map.put(p3, 555);
//
//        System.out.println(map.size());
        // 如果什么都不重写，p1,p3的hashcode的值，和内存地址相关。所以一定不是同样的东西。size 一定是3
        // 如果只重写了hashCode() 那么map.size = 3；
        //如果只写了equals(Object o)  那么map.size() = 2/3
        //如果重写了hashCode()，equals(Object o)  那么map.size() = 2

//        System.out.println(p1 == p3); // false

        test();
    }

    public static void test(){
        Person p1 = new Person(54, 17.7f, "jeff");
//        Person p2 = new Person(45, 18.2f, "pony");
        Person p3 = new Person(54, 17.7f, "jeff");
        HashMap<Object, Integer> map  = new HashMap<>();
        map.put(p1, 111);
        map.put("test", 222);
        map.put(p3, 333);
        System.out.println(map.size());


    }
}
