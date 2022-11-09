package _00_leetcode._01_list;

/**
 * 234. Palindrome Linked List
 * https://leetcode.com/problems/palindrome-linked-list/
 * Given the head of a singly linked list,
 * return true if it is a palindrome or false otherwise.
 *
 * Requirement: O(n) time and O(1) space
 */

public class PalindromeLinkedList {

    /**
     * si思路：
     * 1. 借助快慢双指针的想法去找到链表的中点
     * 2. 反转右半部分
     * 3. 设置两个指针，分别从两端向中间靠近，进行回文判断
     * 4. 恢复现场（再次反转右半部分）
     * @param head
     * @return boolean
     */

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        // 1. 借助快慢双指针的想法去找到链表的中点
        ListNode midNode = getMidNode(head);
        // 2. 反转右半部分
        ListNode rightHead = reverseList(midNode.next);
        ListNode oldRightHead = rightHead;
        // 3. 设置两个指针，分别从两端向中间靠近，进行回文判断
        ListNode leftHead = head;
        boolean isPalindrom = true;

        while (rightHead != null){
            if (leftHead.val != rightHead.val ){

                isPalindrom = false;
                break;
            }
            leftHead = leftHead.next;
            rightHead = rightHead.next;
        }

        // 4. 恢复现场（再次反转右半部分）
        reverseList(oldRightHead);

        return isPalindrom;
    }

    private ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head){

        ListNode newHead = null;

        while (head != null){ // non recursion way  头插的方式
            ListNode tempHead = head.next;
            head.next = newHead;
            newHead = head;
            head = tempHead;
        }

        return newHead;

    }
}
