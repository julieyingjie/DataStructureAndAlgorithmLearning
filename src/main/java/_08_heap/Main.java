package _08_heap;

import _08_heap.printer.BinaryTrees;

import java.util.Comparator;


public class Main {
        public static void main(String[] args) {
//            Integer[] arr = {5, 9, 3, 4, 7, 0, 1, 55, 42, 89};
//
//            BinaryHeap<Integer> heap = new BinaryHeap<>();
//            for (int i = 0; i < arr.length; i++) {
//                heap.add(arr[i]);
//            }
//
//            BinaryTrees.println(heap);
//
//            System.out.println("=============================");
//            heap.remove();
//            BinaryTrees.println(heap);
//
//            System.out.println("=============================");
//            heap.clear();
//            BinaryTrees.println(heap);
//            System.out.println(heap.size());

            test();
        }

        public static void test(){
            Integer[] arr = {5, 9, 3, 4, 7, 0, 1, 55, 42, 89};

            BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            }, arr);
            BinaryTrees.println(heap);
        }
}
