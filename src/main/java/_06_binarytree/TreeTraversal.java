package _06_binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {
    public static void main(String[] args) {
        Node root = new Node(7, null, null);
        root.left = new Node(4, null, null);
        root.right = new Node(9, null, null);
        root.left.left = new Node(2, null, null);
        root.left.right = new Node(5, null, null);
        root.right.left = new Node(8, null, null);
        root.right.right = new Node(11, null, null);

//        postOrderTraversalByRecursion(root);
//        System.out.println();
//        postOrderTraversal(root);

//        levelOrderTraversal(root);
        System.out.println(height(root));
    }


    // 递归的方式
    private static int height(Node node){
        if (node == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        int levelSize = 1;

        while (!queue.isEmpty()){
            Node poll = queue.poll();
            levelSize--;

            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);

            if (levelSize == 0){//意味着当前层遍历完了，即将遍历下一层
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }




    private static class Node {
        public int val;
        public Node left;
        public Node right;
//        public Node parent;


        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    // 递归
    public static void preOrderTraversalByRecursion(Node head) {
        //base case;
        if (head == null) return;

        // 递归
        System.out.print(head.val + " ");
        preOrderTraversalByRecursion(head.left);
        preOrderTraversalByRecursion(head.right);

    }

    /**
     * 借用stack
     * 有右先压右
     * 有左再压左
     * 节点为空时，再去看stack 是否空，不空就pop,空了就结束遍历
     * @param head
     */
    public static void preOrderTraversal(Node head) {
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();

            System.out.print(pop.val + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }


    public static void inOrderTraversalByRecursion(Node head) {
        //base case;
        if (head == null) return;

        // 递归                                     
        inOrderTraversalByRecursion(head.left);
        System.out.print(head.val + " ");
        inOrderTraversalByRecursion(head.right);

    }

    /**
     *思路：
     * 1. Set node = root
     * 2. loo the following operations
     * if node != null
     *    push node
     *    Set node = node.left
     *
     *    if node == null
     *    (1) if the stack is empty, end the travesal
     *    (2) if the stack is not empty, pop the top of the stack,
     *        and assign it to node
     *    visit each node
     *    Set node = node.right
     * @param head
     */
    public static void inOrderTraversal(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();

        if (head != null) {

            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.val + " ");
                    head = head.right;
                }
            }
        }
    }

    /**
     * @param head
     */

    public static void postOrderTraversalByRecursion(Node head){
        if (head == null) return;

        postOrderTraversalByRecursion(head.left);

        postOrderTraversalByRecursion(head.right);

        System.out.print(head.val + " ");
    }

    public static void postOrderTraversal(Node head){
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        Stack<Node> helpStack = new Stack<>();

        stack.push(head);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            helpStack.push(pop);

            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }

        while (!helpStack.isEmpty()) System.out.print(helpStack.pop().val + " ");
    }

    public static void levelOrderTraversal(Node head){
        if (head == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.print(poll.val + " ");
            if (poll.left != null){
                queue.offer(poll.left);
            }
            if (poll.right != null){
                queue.offer(poll.right);
            }

        }
    }

}