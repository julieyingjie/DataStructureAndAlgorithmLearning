package _00_leetcode._00_array;

/**
 * @author Julie
 * https://leetcode.com/problems/sort-colors/
 * Given an array nums with n objects colored red, white, or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 * use 2 pointers, less, initialize -1 and more, initialize 10
 * another pointer: current
 */
public class SortColors {

    public static void main(String[] args) {
        int[] arr = new int[]{10, 4, 77, 97, -5, 23, 45, 6, 2};
        new SortColors().divideArray(arr, 45);
    }

    public void sortColors(int[] nums) {
        divideArray(nums, 1);
    }

    public void divideArray(int[] nums, int pivot){
        divideArray(nums, 0, nums.length - 1, pivot);
    }

    public void divideArray(int[] nums, int left, int right, int pivot){ // 可以对数组的局部进行分组
        int less = left - 1;
        int more = right + 1;
//        int current = 0;
//        while (current != more)

        while (left < more){
            if (nums[left] < pivot){
               swap(nums, ++less, left++);
            }else if (nums[left] > pivot){
                swap(nums, --more, left);
            }else left++;
        }

    }

    private void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
