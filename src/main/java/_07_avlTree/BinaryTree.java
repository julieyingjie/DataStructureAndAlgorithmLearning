package _07_avlTree;


import _07_avlTree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<E> implements BinaryTreeInfo {

    protected Node<E> root;
    protected int size;

    //访问者模式
    public static abstract class Visitor<E>{
        boolean stop;
        public abstract  boolean visit(E element);
    }


    // number of elements
    public int size(){
        return size;

    }

    public void preOrderTraversal (Visitor<E> visitor){
        if (visitor == null) return;
        preOrderTraversalByRecursion(root, visitor);
    }

    // 递归
    public void preOrderTraversalByRecursion (Node<E> head, Visitor<E> visitor){
        //base case;
        if (head == null || visitor.stop == true) return;

        // 递归
        visitor.stop = visitor.visit(head.element);
        preOrderTraversalByRecursion(head.left, visitor);
        preOrderTraversalByRecursion(head.right, visitor);

    }


    public void preOrderTraversal (){
        preOrderTraversal(root);
    }
    /**
     * 借用stack
     * 有右先压右
     * 有左再压左
     * 节点为空时，再去看stack 是否空，不空就pop,空了就结束遍历
     * @param head
     */
    public void preOrderTraversal (Node<E> head){
        if (head == null) return;

        Stack<Node<E>> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();

            System.out.print(pop.element + " ");
            if (pop.right != null) stack.push(pop.right);
            if (pop.left != null) stack.push(pop.left);
        }
    }


    public void inOrderTraversalByRecursion (Node<E> head){
        //base case;
        if (head == null) return;

        // 递归
        inOrderTraversalByRecursion(head.left);
        System.out.print(head.element + " ");
        inOrderTraversalByRecursion(head.right);
    }


    public void inOrderTraversal (){
        inOrderTraversal(root);
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
    public void inOrderTraversal (Node<E> head){
        if (head == null) return;
        Stack<Node<E>> stack = new Stack<>();

        if (head != null) {

            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.element + " ");
                    head = head.right;
                }
            }
        }
    }

    /**
     * @param head
     */

    public void postOrderTraversalByRecursion (Node<E> head){
        if (head == null) return;

        postOrderTraversalByRecursion(head.left);

        postOrderTraversalByRecursion(head.right);

        System.out.print(head.element + " ");
    }

    public void postOrderTraversal (){
        postOrderTraversal(root);
    }

    public void postOrderTraversal (Node<E> head){
        if (head == null) return;

        Stack<Node<E>> stack = new Stack<>();
        Stack<Node<E>> helpStack = new Stack<>();

        stack.push(head);
        while (!stack.isEmpty()) {
            Node<E> pop = stack.pop();
            helpStack.push(pop);

            if (pop.left != null) stack.push(pop.left);
            if (pop.right != null) stack.push(pop.right);
        }

        while (!helpStack.isEmpty()) System.out.print(helpStack.pop().element + " ");
    }

    public void levelOrderTraversal (){
        levelOrderTraversal(root);
    }

    public void levelOrderTraversal (Node<E> head){
        if (head == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            System.out.print(poll.element + " ");
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }

        }
    }

    // if it is empty or not
    public boolean isEmpty(){
        return size == 0;
    }

    // clear all elements
    public void clear(){
        root = null;
        size = 0;
    }

    // 找predecessor 和 successor
    public Node<E> predecessor(Node<E> node){
        if (node == null) return null;
        //1. node.left != null
        Node<E> p =  node.left;
        if (p != null){
            while (p.right != null){
                p = p.right;
            }
            return p;
        }

        //2. node.left == null && node.parent != null
        while(node.parent != null && node == node.parent.left){
            node = node.parent;
        }
        return node.parent;
    }

    public Node<E> successor(Node<E> node){
        if (node == null) return null;
        //1. node.right != null
        Node<E> p =  node.right;
        if (p != null){
            while (p.left != null){
                p = p.left;
            }
            return p;
        }

        //2. node.right == null && node.parent != null
        while(node.parent != null && node == node.parent.right) node = node.parent;
        return node.parent;
    }


   //无参数表明这是计算整个树的高度
    public int height(){
        return height(root);
    }


    // 该计算算法计算的是某个节点的高度
    // 递归的方式
    private int height1(Node<E> node){
        if (node == null) return 0;

        return Math.max(height(node.left), height(node.right)) + 1;

    }

    // 层序遍历的方式
    private int height(Node<E> node){
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

    public boolean isCompleted(){
        return isComplete(root);
    }

    public boolean isComplete(Node<E> head) {

        if (head == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && node.isLeafNode()) return false;

            // 1. If node.left != null && node.right != null
            //    add node.left and node.right to the queue in sequence
            if (node.hasTwoChildren()) {
                queue.offer(node.left);
                queue.offer(node.right);
            }

            // 2. If node.left == null && node.right != null, return false
            else if (node.left == null && node.right != null) return false;

                // 3. If node.left != null && node.right == null 特殊情况：度为1的点已经出现,叶节点也出现了
            else if (node.left != null && node.right == null) {
                queue.offer(node.left);
                isLeaf = true;
            }

            // 4. node.left == null && node.right == null 叶节点也出现
            else isLeaf = true;
        }
        return true;
    }

//    public boolean isBalanced(){
//
//        return isBalanced(root);
//    }
//
//    public boolean isBalanced(Node<E> head){
//
//        return false;
//    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode =  (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null){
             parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_P(" + parentString + ")";
    }


    protected static class Node<E> {
        protected E element;
        protected Node<E> left;
        protected Node<E> right;
        protected Node<E> parent;

        public Node(E val, Node<E> parent) {
            this.element = val;
            this.parent = parent;
        }

        protected boolean hasTwoChildren() {
            return this.left != null && this.right != null;
        }

        protected boolean isLeafNode() {
            return this.left == null && this.right == null;
        }

        @Override
        public String toString() {
            return " element=" + element + ", parent=" + parent;
        }
    }
}
