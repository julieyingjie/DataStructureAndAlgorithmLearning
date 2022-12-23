package _11_recursion;

/**
 * female cow = 1 ---> 1  y ---> child female
 * child female ---> 4  y ----> adult female cow
 * 每头母牛，每隔一年生出一头小母牛，每个小母牛，每过4年，长成成年可以生育的母牛。
 * 问： 隔n 年后，一共有多少头牛？ （不考虑有牛死的情况）
 */

public class NumsOfCow {

    public static void main(String[] args) {

    }

    public static int numsOfCow0(int n){
        if (n <= 4) return n;

        return numsOfCow0(n - 1) + numsOfCow0(n - 4);
    }

    public static int numsOfCow(int n){
        if (n <= 4) return n;
        int[] arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        return numsOfCow(n, arr);
    }

    private static int numsOfCow(int n, int[] arr) {
        if (arr[n] == 0){
            arr[n] = numsOfCow(n - 1, arr) + numsOfCow(n - 4, arr);
        }
        return arr[n];
    }
}
