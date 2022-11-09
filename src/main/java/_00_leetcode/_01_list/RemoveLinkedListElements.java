package _00_leetcode._01_list;

/**
 * 203.Remove Linked List Elements
 * https://leetcode.com/problems/remove-linked-list-elements/
 *
 * Given the head of a linked list and an integer val,
 * remove all the nodes of the linked list that has Node.val == val, and return the new head.
 */

public class RemoveLinkedListElements {

    /**
     * Advanced Version of 1
     * Use Dummy node
     * 考虑的情况少x
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {

        ListNode dummyNode = new ListNode(0);
        ListNode newTail = dummyNode;

        while(head != null){
            if (head.val != val) newTail = newTail.next = head;

            head = head.next;
        }

        newTail.next = null;

        return dummyNode.next;
    }

    /**
     * 核心思路：
     * 创建新链表
     */
    public ListNode removeElements1(ListNode head, int val) {

        ListNode newHead = null;
        ListNode newTail = null;

        while(head != null){
            if (head.val != val){
                if (newTail == null){
                    newHead = head;
                    newTail = head;
                }else {
                    newTail.next = head;
                    newTail = head;
                }
            }
          head = head.next;
        }

        if (newTail == null) return null;
        else newTail.next = null;

        return newHead;
    }
}
