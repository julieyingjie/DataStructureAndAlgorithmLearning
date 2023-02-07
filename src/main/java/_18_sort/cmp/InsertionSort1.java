package _18_sort.cmp;

import _18_sort.Sort;
import _18_sort.utils.Integers;

public class InsertionSort1<T extends Comparable<T>> extends Sort<T> {
    public static void main(String[] args) {
        Integer[] randomArr = Integers.random(10, 1, 100);
        Integers.println(randomArr);
        new InsertionSort1().sort(randomArr);
        Integers.println(randomArr);
    }

    @Override
    protected void sort() {
        // 逻辑上理解为将其分为两部分，前部分为已经排好序的，后部分为待排的。
        // 第一张牌，已经有序了，所以从第二张开始查看
        for (int i = 1; i < array.length; i++) {

            int cur = i;
            while (cur > 0 && cmp(cur, cur-1) < 0){
                swap(cur, cur-1);
                cur--;
            }
        }
    }
}












