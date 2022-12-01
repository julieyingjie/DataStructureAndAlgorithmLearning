package _00_leetcode._04_tree;

import _06_binarytree.TreeTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 958. Check Completeness of a Binary Tree
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 */
public class CheckCompletenessofaBinaryTree {

    /**
     * 完全二叉树的定义：
     * 1.从上到下，从左到右来充满
     * 2.叶子节点只能在最后两层
     * 思路：判断完全二叉树,利用层序遍历的做法
     *  树当中的每个节点，只能有4种情况
     *
     * @param root
     * @return boolean
     */
    public boolean isCompleteTree(TreeNode root) {

        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
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

    private boolean hasTwoChildren(TreeNode node){
        return node.left != null && node.right != null;
    }

    private boolean isLeafNode(TreeNode node){
        return node.left == null && node.right == null;
    }
}
