package _06_binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree <E>{

   private Node<E> root;
   private int size;

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





    private static class Node<E> {
        private E val;
        private Node left;
        private Node right;
        // public Node parent;

        public Node(E val, Node left, Node right) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +  "val=" + val + '}';
        }
    }
}
