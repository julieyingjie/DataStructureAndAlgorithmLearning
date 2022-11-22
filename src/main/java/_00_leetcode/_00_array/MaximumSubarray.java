package _00_leetcode._00_array;

/**
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 * Task: 求出这个数组中，哪个子数组的和最大，返回这个和的值
 * 最好的思路：利用DP: dynamic programming
 * dynamic: in DP, it can be understood as "changing state"
 * Step1: Define state (the state is the solution of the original problem, subproblem)
 *        eg. define what dp(i) means
 *
 * Step2: Set initial state (boundary)
 *        eg. set the value of dp(0)
 *
 * Step3: Determine the state transition equation
 *       eg.  determine the relationship between dp(i) and dp(i - 1)
 */

public class MaximumSubarray {

    //最好的一个解法: maxSubArray3 的优化版本
    // 创造对象需要的时间，非常大，高过for loop 的时间。
    // 所以，想办法用变量，来替换创造对象
    public int maxSubArray4(int[] nums){
        //Step1: Define state
        int dp = nums[0];
        int max = dp;

        for (int i = 1; i < nums.length; i++) {
            //Step3: Determine the state transition equation
            //if (dp[i - 1] <= 0 dp[i] = nums[i])
            //if (dp[i - 1] > 0 dp[i] = nums[i] + dp[i - 1])

            if (dp <= 0) dp = nums[i];
            else dp = dp + nums[i];

            max = Math.max(dp, max);
        }
        return max;
    }


    //比较好的一个解法: 利用DP
    public int maxSubArray3(int[] nums){
        //Step1: Define state
        int[] dp = new int[nums.length];

        //Step2: Set initial state (boundary)
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            //Step3: Determine the state transition equation
            //if (dp[i - 1] <= 0 dp[i] = nums[i])
            //if (dp[i - 1] > 0 dp[i] = nums[i] + dp[i - 1])

            if (dp[i-1] <= 0) dp[i] = nums[i];
            else dp[i] = dp[i-1] + nums[i];

            max = Math.max(dp[i], max);
        }

        return max;
    }


    // 也不是很完美
    public int maxSubArray2(int[] nums) {

        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                max = Math.max(sum, max);
            }
        }
        return max;
    }

    // 下面这种解法会出现：Time Limited Error ， 3个for loop， 时间复杂度巨大
    public int maxSubArray1(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end ; i++) {
                    sum += nums[i];
                }
                max = Math.max(sum, max);
            }
        }
        return max;
    }

}
