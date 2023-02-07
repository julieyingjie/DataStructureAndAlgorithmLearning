package _18_sort.cmp;

/**
 * unstable sorting method
 */

import _18_sort.Sort;
import _18_sort.utils.Integers;

public class HeapSort <T extends Comparable<T>> extends Sort<T> {
    public static void main(String[] args) {
        Integer[] randomArr = Integers.random(10, 1, 100);
        Integers.println(randomArr);
        new HeapSort().sort(randomArr);
        Integers.println(randomArr);
    }

    private int heapSize;

    /**
     *
     */
    @Override
    protected void sort() {
        // 1. Heapify
        heapSize = array.length;

        // 自下而上
        for (int i = (heapSize >> 1) - 1; i >= 0 ; i--) {
            siftDown(i);
        }

        // 2. Repeat the following operations until there is only 1 element in the heap
        //    (1) swap the top and tail element of the heap
        //    (2) Decrease the number of the elements in the heap by 1
        //    (3) do siftDown operation on the 0 position

        while (heapSize > 1){
            swap(0, --heapSize);
            siftDown(0);
        }


    }

    private void siftDown(int index) {

        int half = heapSize >> 1;
        T element = array[index];

        while(index < half){

            int childIndex = (index << 1) + 1;
            T child = array[childIndex]; // 取得值

            int rightChildIndex = childIndex + 1;

            if (rightChildIndex < heapSize && cmp(array[rightChildIndex], child) > 0){
                child = array[childIndex = rightChildIndex];
            }

            if (cmp(element, child) >= 0) break;
            array[index] = child;
            index = childIndex;
        }

        array[index] = element;
    }
}
