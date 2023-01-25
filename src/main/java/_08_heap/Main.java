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

//            test();
            Integer[] arr = {5, 99, 3, 4, 7, 0, 1, 55, 42, 89};
            topK(arr, 3);
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

        public static void topK(Integer[] data, int k){
            BinaryHeap<Integer> minHeap = new BinaryHeap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            // 建个堆
            for (int i = 0; i < data.length; i++) {
                if (minHeap.size < k){
                    minHeap.add(data[i]);
                }else if (data[i] < minHeap.get()){
                    minHeap.replace(data[i]);
                }

            }

            BinaryTrees.println(minHeap);
        }
}
