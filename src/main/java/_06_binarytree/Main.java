package _06_binarytree;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        BinarySearchTree<Person> bst = new BinarySearchTree<>(new HeightComparator());
        Person p1 = new Person(20, 1.75);
        Person p2 = new Person(30, 1.85);
        Person p3 = new Person(40, 1.55);
        Person p4 = new Person(10, 1.65);
        bst.add(p1);
        bst.add(p2);
        bst.add(p3);
        bst.add(p4);
    }



}
