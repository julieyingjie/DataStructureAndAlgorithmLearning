package _00_leetcode._01_list;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head){

        // base case
        if (head == null || head.next == null) return head;

        // other cases
        ListNode newHead = reverseList(head.next);

        ListNode tail = head.next;
        tail.next = head;
        head.next = null;

        return newHead;
    }

}
