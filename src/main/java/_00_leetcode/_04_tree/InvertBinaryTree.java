package _00_leetcode._04_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 * 思路：遍历方式

 */

public class InvertBinaryTree {
    public TreeNode invertTree1(TreeNode root) {

        //base case;
        if (root == null) return null;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree1(root.left);
        invertTree1(root.right);

        return root;
    }

    public TreeNode invertTree3(TreeNode root){
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            TreeNode temp = poll.left;
            poll.left = poll.right;
            poll.right = temp;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        return root;
    }
}
