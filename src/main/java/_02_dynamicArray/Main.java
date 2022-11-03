package _02_dynamicArray;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(10 + i);
        }
        list.remove(4);
        list.add(null);
        int index = list.indexOf(null);
        System.out.println(index);
        System.out.println(list.contains(10));
        System.out.println(list.contains(null));
        System.out.println(list.set(1,100));
//        System.out.println(list.size());
//        System.out.println(list.size());
//        list.add(100);
//        list.add(1, 10);
//        list.add(0, 30);
//        list.add(1,20);
////        list.add(5, 10); this is for test the exception
//        System.out.println(list.get(3));
//        System.out.println(list.size());
//        list.clear();
//        System.out.println(list.isEmpty());

    }
}
