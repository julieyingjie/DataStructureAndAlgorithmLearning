package _00_leetcode._00_array;

/**
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 * 注意稳定性
 * 用3指针
 * 从后向前移动
 */

public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n){

        int i1 = m - 1;
        int i2 = n - 1;

        int cur = nums1.length - 1;
        while (i2 >= 0){
            if (i1 >= 0 && (nums1[i1] > nums2[i2])) nums1[cur--] = nums1[i1--];
            else nums1[cur--] = nums2[i2--];
        }
    }
}
