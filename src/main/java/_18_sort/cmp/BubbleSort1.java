package _18_sort.cmp;

import _18_sort.utils.Integers;

public class BubbleSort1 {
    public static void main(String[] args) {
        Integer[] randomArr = Integers.random(10, 1, 100);
        Integers.println(randomArr);
        new BubbleSort1().sort(randomArr);
        Integers.println(randomArr);

    }
    
    public void sort(Integer [] arr){

        for (int i = arr.length - 1; i > 0 ; i--) {// 控制循环的轮数
            // sorted 是为了优化不再去排那些已经有序了的数字
//            boolean sorted = true;

            // 第二种优化方式
            int soretedIndex = 0;
            for (int j = 1; j <= i; j++) { // 控制没轮内的对比的次数

                if (arr[j] < arr[j - 1]){
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
//                    sorted = false;
                    soretedIndex = j;
                }
            }

//            if (sorted) break;

            i = soretedIndex;
            
        }
    }
}
