package _00_leetcode._01_list;

/**
 * https://leetcode.com/problems/linked-list-cycle/
 * 141. Linked List Cycle
 * Use the fast and slow pointer to solve
 */

public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null){ // 1. has cycle: 条件是while(true)
                  // 2. no cycle：fast 来控制  fast.next == null 就意味着无环
            slow = head.next;
            fast = head.next.next;

            if (slow == fast) return true;
        }

        return false;
    }
}
