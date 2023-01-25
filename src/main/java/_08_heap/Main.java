package _08_heap;

import _08_heap.printer.BinaryTrees;


    public class Main {
        public static void main(String[] args) {
            int[] arr = {5, 9, 3, 4, 7, 0, 1, 55, 42, 89};

            BinaryHeap<Integer> heap = new BinaryHeap<>();
            for (int i = 0; i < arr.length; i++) {
                heap.add(arr[i]);
            }

            BinaryTrees.println(heap);

            System.out.println("=============================");
            heap.remove();
            BinaryTrees.println(heap);

            System.out.println("=============================");
            heap.clear();
            BinaryTrees.println(heap);
        }
}
