package _06_binarytree;

import _00_leetcode._04_tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree <E>{

   protected Node<E> root;
    protected int size;

    // number of elements
    public int size(){
        return size;

    }

    // if it is empty or not
    public boolean isEmpty(){
        return size == 0;
    }

    // clear all elements
    public void clear(){
        root = null;
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

    public boolean isCompleteTree(Node<E> head) {

        if (head == null) return false;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (isLeaf && isLeafNode(node)) return false;

            // 1. If node.left != null && node.right != null
            //    add node.left and node.right to the queue in sequence
            if (hasTwoChildren(node)) {
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

    private boolean hasTwoChildren(Node node){
        return node.left != null && node.right != null;
    }

    private boolean isLeafNode(Node node){
        return node.left == null && node.right == null;
    }

    protected static class Node<E> {
        protected E element;
        protected Node<E> left;
        protected Node<E> right;
        protected Node<E> parent;

        public Node(E val, Node parent) {
            this.element = val;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return " element=" + element + ", parent=" + parent;
        }
    }
}
