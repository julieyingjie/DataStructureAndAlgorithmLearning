package _00_leetcode._04_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 * 思路：遍历方式

 */

public class InvertBinaryTree {


    // This is the way using preOrderTraversal
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

    // this is the way using postOrderTraversal
    public TreeNode invertTree2(TreeNode root) {

        if (root == null) return null;

        invertTree2(root.left);
        invertTree2(root.right);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }


    // This is the way using levelTraversal
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

    // This is the way using inorder Traversal
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return null;

        invertTree4(root.left);

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree4(root.left);

        return root;

    }
}
