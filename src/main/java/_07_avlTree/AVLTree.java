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
                rebalance2(node);
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

    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();

        Node<E> node = ((AVLNode<E>)parent).tallerChild();

        if (parent.isLeftChild()){ // L
            if (node.isLeftChild()){   // LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            }else{ // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        }else {// R
            if (node.isLeftChild()){   // RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            }else{ // RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    private void rotate(Node<E> r, // 用来制定原来子树的root， 用r的parent 作为parent
                        Node<E> a, Node<E> b,Node<E> c,Node<E> d,Node<E> e,Node<E> f,Node<E> g) {

        // 让d成为这颗子树的root
        d.parent = r.parent;
        if (r.isLeftChild()) r.parent.left = d;
        else if (r.isRightChild()) r.parent.right = d;
        else root = d;

        // a -- b -- c
        b.left = a;
        if(a != null) a.parent = b;

        b.right = c;
        if(c != null) c.parent = b;

        calculateHeight(b);

        // e -- f -- g
        f.left = e;
        if(e != null) e.parent = f;

        f.right = g;
        if(g != null) g.parent = f;

        calculateHeight(f);

        // b -- f -- d
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
        calculateHeight(d);
    }
    private void rightRotate(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;

        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }
    private void leftRotate(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left; // T2

        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);

    }

    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        // 更新parent 属性
        parent.parent = grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        }else if (grand.isRightChild()){
            grand.parent.right = parent;
        }else { // grand is root
            root = parent;
        }

        // 更新child的parent
        if (child != null){
            child.parent = grand;
        }

        // 更新grand 的parent
        grand.parent = parent;

        calculateHeight(grand);
        calculateHeight(parent);

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
