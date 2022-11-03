package _00_leetcode._01_list;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {

    // recursion way
    public static ListNode reverseListRecursion(ListNode head){

        // base case
        if (head == null || head.next == null) return head;

        // other cases
        ListNode newHead = reverseList(head.next);

        ListNode tail = head.next;
        tail.next = head;
        head.next = null;

        return newHead;
    }

    // non recursion way  头插的方式
    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode newHead = null;

        while (head != null){
            ListNode tempHead = head.next;
            head.next = newHead;
            newHead = head;
            head = tempHead;
        }

        return newHead;

    }

}
