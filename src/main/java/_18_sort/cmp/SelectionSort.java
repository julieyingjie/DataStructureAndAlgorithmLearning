package _18_sort.cmp;

import _18_sort.Sort;
import _18_sort.utils.Integers;

public class SelectionSort<T extends Comparable<T>> extends Sort<T> {

    public static void main(String[] args) {
        Integer[] randomArr = Integers.random(10, 1, 100);
        Integers.println(randomArr);
        new SelectionSort().sort(randomArr);
        Integers.println(randomArr);
    }
    @Override
    protected void sort() {
        for (int i = array.length - 1; i > 0 ; i--) {// 控制循环的轮数
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) { // 控制没轮内的对比的次数

                if (cmp(maxIndex, j) < 0){
                    maxIndex = j;
                }
            }
            swap(maxIndex, i);
        }

    }
}
