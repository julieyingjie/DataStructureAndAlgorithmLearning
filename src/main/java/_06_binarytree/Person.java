package _06_binarytree;

public class Person implements Comparable<Person>{

    public int age;
    public double height;

    @Override
    public int compareTo(Person p) {
        return this.age - p.age;
    }

    public Person(int age, double height) {
        this.age = age;
        this.height = height;
    }
}
