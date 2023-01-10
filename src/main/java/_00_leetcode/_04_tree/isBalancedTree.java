package _00_leetcode._04_tree;

/**
 * 不能只看跟节点的左子树和右子树的高度差。应该是每个节点的左右子树高度差 <= 1
 */

public class isBalancedTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(9, null, null);
        root.left = new TreeNode(6, null, null);
        root.right = new TreeNode(15, null, null);
        root.left.left = new TreeNode(4, null, null);
        root.left.right = new TreeNode(8, null, null);
        root.right.left = new TreeNode(14, null, null);
        root.right.right = new TreeNode(16, null, null);
        root.right.left.left = new TreeNode(12, null, null);
//      root.right.left.left.right = new TreeNode(13, null, null);

        System.out.println(isBalanced2(root));

    }


//   java bean / domain / pojo  一个真实存在的对象 （eg. Student, Course） --> table
//   dto: data transfer obj  数据的传输对象，用来传递数据

    public static class ReturnData {
        public boolean isB;
        public int height;

        public ReturnData(boolean isB, int height) {
            this.isB = isB;
            this.height = height;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        //recursion
        //divide
        return isB(root).isB;
    }

    public static ReturnData isB(TreeNode root) {
        // Base case
        // base case principle: 递归的最后一层，最后一次调用这个方法的场景。
        // 来到叶节点时，方法就停
        // 叶节点，一定是个平衡树
        if (root == null) return new ReturnData(true, 0);

//          利用分置的思路divide
//          recursion的技术
        ReturnData leftData = isB(root.left);
        // if 左侧树不平衡， 只要有一次树不平衡，就不需要往下走了。直接返回false。
        if (!leftData.isB) return new ReturnData(false, 0); // 这个地方的height是多少都可以，不重要。因为反正已经返回了false了。

        ReturnData rightData = isB(root.right);
        // if 右侧书不平衡， similar to left not balanced tree
        if (!rightData.isB) return new ReturnData(false, 0);

        // 左右都平，但是高度差大于1
        if (Math.abs(leftData.height - rightData.height) > 1) return new ReturnData(false, 0);

        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);
    }

    public static boolean isBalanced2(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(height(root.left) - height(root.right)) > 1) return false;

        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    private static int height(TreeNode root) {
        if (root == null) return 0;
        return (Math.max(height(root.left), height(root.right)) + 1);
    }

}
