package _03_list;

public class Main {
    public static void main(String[] args) {
//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(11);
//        list.add(22);
//        list.add(33);
//        list.add(44);
        LinkedList<Person> list = new LinkedList<>();
        list.add(new Person("A", 10));
        list.add(new Person("B", 11));
        list.add(new Person("C", 12));
        list.add(new Person("D", 13));
        list.add(new Person("E", 14));
        System.out.println(list);
//        System.out.println(list.indexOf(33));
//        list.set(1, 66);
//        System.out.println(list.remove(3));

//        while (list.size != 0){
//            System.out.println(list.remove(0));
//        }


//        list.add(0,66);

//        SingleLinkedList<Integer> list = new SingleLinkedList<>();
//        list.add(11);
//        list.add(22);
//        list.add(33);
//        list.add(1,44);
//        list.add(4,55);
//        list.remove(2);
//        System.out.println(list);
    }
}
