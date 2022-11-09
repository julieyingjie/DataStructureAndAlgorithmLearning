package _00_leetcode._01_list;

/**
 * 两个单链表的相交问题
 * 单链表可以有环，也可以无环；
 * head1, head2链表可能相交，也可能不相交
 * function(head1, head2)
 * 如果两个链表相交，返回相交的第一个节点，否则，返回null
 * 要求：
 * head1 length = n, head2 length = m, O(n + m) T(n) = O(1)
 * 想法：
 * 1. 判断一个链表是否有环；并且得到入环点
 * 2. 得到2个无环单链表的相交的第一个节点 ： Y 型结构
 *                                     （永远不可能出现： X型结构永远不可能，因为任意一个node的next,都只能有一个）
 * 3. 得到1个无环单链表与1个有环单链表的相交的第一个节点 （空间中压根没有这种结构）
 * 4. 得到2个有环单链表的相交的第一个节点：
 *
 */

public class GetFirstInsertionNode {
    public static void main(String[] args) {
        ListNode head1 = new ListNode(10);
        head1.next = new ListNode(20);
        head1.next.next = new ListNode(30);
        head1.next.next.next = new ListNode(40);
        head1.next.next.next.next = new ListNode(50);
        head1.next.next.next.next.next = new ListNode(60);
        head1.next.next.next.next.next.next = new ListNode(70);

        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(20);
        head2.next.next = new ListNode(30);
        head2.next.next.next = new ListNode(40);
        head2.next.next.next.next =  head1.next.next.next.next;

        ListNode insertionNode = getFirstInsertionNode( head1,  head2);
        System.out.println(insertionNode.val);

    }

    /**
     * 主函数
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode getFirstInsertionNode(ListNode head1, ListNode head2){
        ListNode cycleNode1 = getCycleNode(head1);
        ListNode cycleNode2 = getCycleNode(head2);

        // 判断两个不含环的链表相交
        if (cycleNode1 == null && cycleNode2 == null) return getNoCycleNode(head1, head2);
        if (cycleNode1 != null && cycleNode2 != null) return getTwoCycleInsertionNode(head1,cycleNode1, head2, cycleNode2);

        return null;
    }

    // 4. 两个含环相交有两种情况： 一种是Y型，下面带圈； 一种是一个圈上伸出两根长天线
    //    第二种相交的情况，有两个相交节点，返回哪个都可以
    //    不相交的情况: 直接两个平行的两个含环链表
    public static ListNode getTwoCycleInsertionNode(ListNode head1, ListNode cycleNode1, ListNode head2, ListNode cycleNode2){
        // Y型带圈的情况
        ListNode cur1 = null;
        ListNode cur2 = null;

        if (cycleNode1 == cycleNode2){// 确认是这种Y型带圈的结构
            int difference = 0;
            cur1 = head1;
            cur2 = head2;

            while (cur1.next != cycleNode1){

                difference++;
                cur1 = cur1.next;
            }

            while (cur2.next != cycleNode2){

                difference--;
                cur2 = cur2.next;
            }

            // 指定cur1为长链表
            cur1 = difference > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            difference = Math.abs(difference);

            while (difference != 0){
                difference--;
                cur1 = cur1.next;
            }

            while (cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }

            return cur1;  // return cur2是一样的 cur1,cur2就是相交点

        }else {// 平行不相交结构  或者 两个相交点的结构
            cur1 = cycleNode1.next;
            while (cur1 != cycleNode1){
                if (cur1 == cycleNode2){// 该情况就是两个相交点的结构

                    return cycleNode1; // cycleNode2 也行；任意一个都可以
                }
                cur1 = cur1.next;
            }
            // 平行结构
            return null;
        }
    }

    // 2. Y 型结构
    public static ListNode getNoCycleNode(ListNode head1, ListNode head2){
        ListNode cur1 = head1;
        ListNode cur2 = head2;

        int difference = 0;

        while (cur1.next != null){

            difference++;
            cur1 = cur1.next;
        }

        while (cur2.next != null){

            difference--;
            cur2 = cur2.next;
        }

        // 没有相交
        if (cur1 != cur2) return null;

        // 有相交
        // 则指定cur1为长链表
        cur1 = difference > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        difference = Math.abs(difference);

        while (difference != 0){
            difference--;
            cur1 = cur1.next;
        }

        while (cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;  // return cur2是一样的
    }


    // 1. 判断一个链表是否有环；并且得到入环点
    public static ListNode getCycleNode(ListNode head){

        // 此处，快慢指针的位置非常重要，只能这样，务必记住
        ListNode slow = head.next;
        ListNode fast = head.next.next;

        while (slow != fast){
            if (fast.next == null || fast.next.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }

        // 将快指针拉回
        fast = head;

        while (slow != fast){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
