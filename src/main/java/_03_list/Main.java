package _03_list;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);

        while (list.size() != 0){
            System.out.println(list.remove(0));
        }


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
