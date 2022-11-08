package _00_leetcode._01_list;

public class DivideList {
    public static void main(String[] args) {
        ListNode head = new ListNode(10);
        head.next = new ListNode(4);
        head.next.next = new ListNode(90);
        head.next.next.next = new ListNode(20);
        head.next.next.next.next = new ListNode(30);
        head.next.next.next.next.next = new ListNode(80);
        head.next.next.next.next.next.next = new ListNode(50);

        divideList(head, 40);

    }

    public static ListNode divideList(ListNode head, int pivot){
        ListNode lessHead = null;
        ListNode lessTail = null;

        ListNode equalHead = null;
        ListNode equalTail = null;

        ListNode moreHead = null;
        ListNode moreTail = null;

        ListNode next = null;

        while (head != null){

            next = head.next;
            head.next = null;

            if (head.val < pivot){
                if (lessHead == null){
                    lessHead = head;
                    lessTail = head;
                }else {
                    lessTail.next = head;
                    lessTail = head;
                }
            }else if (head.val == pivot){
                if (equalHead == null){
                    equalHead = head;
                    equalTail = head;
                }else {
                    equalTail.next = head;
                    equalTail = head;
                }

            }else { // head.val > pivot
                if (moreHead == null){
                    moreHead = head;
                    moreTail = head;
                }else {
                    moreTail.next = head;
                    moreTail = head;
                }

            }

            head = next;

        }

        // lessTail --> equalHead
        if (lessTail != null){
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }

        // equalTail --> moreHead
        if (equalTail != null){
            equalTail.next = moreHead;
        }

        return lessHead != null ? lessHead : equalHead != null ? equalHead: moreHead;
    }
}
