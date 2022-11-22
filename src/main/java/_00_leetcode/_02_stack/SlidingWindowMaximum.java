package _00_leetcode._02_stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 * 思路：
 * 两端可以操作的链表，那就是deQueue, java 也提供。
 * 需要 1个从头到尾递减状态的queue, 大 ---> 小， queue中存的是数组的下标。
 * 需要 1个windwos k 长度
 * 需要 1个maxes, 存取最大值
 * 需要 1个指针，来从头到尾遍历数组
 * 步骤：
 * notes:
 * 尾部进入，读取时，不符合递减的话，
 * windows合法后，直接peek   queue的head, 放入maxes
 * 每走一步，拿一次队头
 * queue的队头过期的标准：window的左边index大于queue的队头里存的index
 */

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) return nums;

        // 一个数组，来存最大值
        int[] maxes = new int[nums.length - k + 1];

        // 一个双端进行操作的队列，存储需要的数据的下标
        Deque<Integer> deque = new LinkedList<>();

        // 一个指针，来读取数组中的数据
        for (int i = 0; i < nums.length; i++) {
            // 如果下一个准备加进队列尾部的值，大于目前队尾的值, 则先将这个目前的尾部值从队列中删掉
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]){
                deque.pollLast();
            }
            // 第一个值无条件从队列的尾部加进去, 之后的按照上面的while判断后，加进去
            deque.addLast(i);

            // window 的左端的下标， 由于i一直在走，所以w 也一直向后走
            int w = i - k + 1;

            // 当w左端index是负值，证明还没有形成合法窗口，那就正常向后走就行
            if (w < 0) continue;

            // 目前产生了合法窗口
            if (w > deque.peekFirst()){// 此时队头元素已过期，直接删掉
                deque.removeFirst();
            }
            maxes[w] = nums[deque.peekFirst()];

        }
        return maxes;
    }

}
