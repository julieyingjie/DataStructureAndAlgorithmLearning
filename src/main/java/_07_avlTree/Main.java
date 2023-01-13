package _07_avlTree;


import _07_avlTree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        test5();
    }

    public static void test5(){
        AVLTree<Integer> tree = new AVLTree<>();

        for (int i = 0; i < 18; i++) {
            tree.add(i);
        }
        BinaryTrees.println(tree);

        System.out.println("========================================================================================");
        tree.remove(17);
        BinaryTrees.println(tree);

        System.out.println("========================================================================================");
        tree.remove(16);
        BinaryTrees.println(tree);
    }
}
