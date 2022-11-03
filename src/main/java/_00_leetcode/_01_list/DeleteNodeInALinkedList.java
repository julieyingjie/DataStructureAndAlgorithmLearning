package _00_leetcode._01_list;

/**
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * 237. Delete Node in a Linked List
 */

public class DeleteNodeInALinkedList {
  public void deleteNode(ListNode node){
      if (node == null || node.next == null) return;
      // node.next == null 的处理？是因为：The node to be deleted is in the list and is not a tail node.
      node.val = node.next.val;
      node.next = node.next.next;
  }
}
