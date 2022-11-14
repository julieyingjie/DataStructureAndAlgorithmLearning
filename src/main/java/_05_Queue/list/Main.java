package _05_Queue.list;

import _05_Queue.list._double.DoublyCircularLinkedList;
import _05_Queue.list._single.SingleCircularLinkedList;

public class Main {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test4();

        test5();

//        LinkedList<Integer> list = new LinkedList<>();
//        list.add(11);
//        list.add(22);
//        list.add(33);
//        list.add(44);
//        LinkedList<Person> list = new LinkedList<>();
//        list.add(new Person("A", 10));
//        list.add(new Person("B", 11));
//        list.add(new Person("C", 12));
//        list.add(new Person("D", 13));
//        list.add(new Person("E", 14));
//        System.out.println(list);
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

    public static void test5(){
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<Integer>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }

        //reset
        list.reset();

        while (list.size > 1){
           next(list, 2);
            System.out.println(list.remove());
        }
    }

    public static void next(DoublyCircularLinkedList<Integer> list, int count){
        for (int i = 0; i < count; i++) {
            list.next();
        }
    }

    public static void test4(){
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<Integer>();
        list.add(0,44);
        list.add(1,55);
        list.add(2,66);
//        System.out.println(list.remove(2));
        while (list.size > 0){
            list.remove(0);
        }
        System.out.println(list);
    }

    public static void test3(){
        DoublyCircularLinkedList<Integer> list = new DoublyCircularLinkedList<Integer>();
        list.add(0,44);
        list.add(1,55);
        list.add(2,66);
//        list.remove(0);
//        while (list.size > 0){
//            list.remove(0);
//        }
        System.out.println(list);
    }

    public static void test2(){
        SingleCircularLinkedList<Integer> list = new SingleCircularLinkedList<>();
        list.add(0,44);
        list.add(1,55);
        list.add(2,66);
//        list.remove(0);
        while (list.size > 0){
            list.remove(0);
        }
        System.out.println(list);
    }

    public static void test1(){
        SingleCircularLinkedList<Integer> list = new SingleCircularLinkedList<>();
        list.add(0,66);
        list.add(1,44);
        list.add(2,55);
        System.out.println(list);
    }

}
