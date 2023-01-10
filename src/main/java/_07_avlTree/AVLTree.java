package _07_avlTree;

import java.util.Comparator;

public class AVLTree <E> extends BinarySearchTree<E>{
    public AVLTree() {
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public Node<E> createNode(E element, Node<E> parent){
        return new AVLNode<>(element, parent);
    }


    // 为了每次添加完节点就去检查是否平衡
    @Override
   public void afterAdd(Node<E> node){
        while((node = node.parent) != null){// node = root  node = node.parent, 节点向上走
            if (isBalanced(node)){
                calculateHeight(node);
            }else {
                rebalance(node);
                break;
            }
        }
    }

    // 最重要的环节
    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();

        Node<E> node = ((AVLNode<E>)parent).tallerChild();


        if (parent.isLeftChild()){ // L
            if (node.isLeftChild()){   // LL
                rightRotate(grand);
            }else{ // LR
                leftRotate(parent);
                rightRotate(grand);
            }
        }else {// R
            if (node.isLeftChild()){   // RL
                rightRotate(parent);
                leftRotate(grand);
            }else{ // RR
                leftRotate(grand);
            }
        }
    }

    private void leftRotate(Node<E> parent) {
    }

    private void rightRotate(Node<E> grand) {
    }

    private void calculateHeight(Node<E> node) {

        ((AVLNode<E>)node).calculateHeight();
    }

    private boolean isBalanced(Node<E> node) {

        return Math.abs(((AVLNode<E>)node).balanceFactor())<= 1;
    }

    private static class AVLNode<E> extends Node<E>{

        int height = 1; // 用于得出balance factor

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public void calculateHeight(){
            int leftHeight = this.left == null ? 0: ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public int balanceFactor(){
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            return leftHeight - rightHeight;
        }

        public Node<E> tallerChild(){
            int leftHeight = this.left == null ? 0 : ((AVLNode<E>)this.left).height;
            int rightHeight = this.right == null ? 0 : ((AVLNode<E>)this.right).height;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return this == this.parent.left ? left : right;
        }

    }
}
