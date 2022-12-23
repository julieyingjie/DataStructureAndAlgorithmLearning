package _07_avlTree;

import java.util.Comparator;

/**
 * Note: BST 当中，不允许有null值
 * @param <E>
 */

public class BinarySearchTree<E> extends BinaryTree<E> {

    protected Comparator<E> comparator;
    public BinarySearchTree() {  };

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void afterAdd(Node<E> node){ }

    public Node<E> createNode(E element, Node<E> parent){
        return new Node<>(element, parent);
    }

    // add elements
    public void add(E element){
        //对添加元素进行判定
        if (element == null) throw new IllegalArgumentException("element can not be null");

        // 添加第一个节点
        if (root == null){
            // TODO: 2022-12-03
            root = new Node<>(element, null);
            size++;
            afterAdd(root);
            return;
        }

        // 2。。2 添加的不是第一个节点，那就一定是叶节点
        Node<E> node = root;
        Node<E> parent = root;
        int cmp = 0;
        while (node != null){
            cmp = compare(element,node.element);
            parent = node;
            if (cmp > 0){
                node = node.right;
            }else if (cmp < 0){
                node = node.left;
            }else {
                node.element = element;
                return;
            }
        }

        // 3. 创建一个新节点，并且插入到指定的位置
        // TODO: 2022-12-03
        Node<E> newNode = new Node<>(element,parent);
        if (cmp > 0) parent.right = newNode;
        else parent.left = newNode;
        size++;
        afterAdd(newNode);
    }

    private int compare(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }


    /**
     *
     * 1. 删除叶子结点： 定位到删除节点。直接将parent的相应left,right置空
     * 2. 删除度为1的节点：直接将被删除节点的父母，指向删除节点的孩子。父子相认。Replace 操作
     * 3. 删除度为2的节点：（1）predecessor,inOrderTravel 中当前节点的前一个节点 (2) successor, 当前节点的后一个节点
     * @param element
     */
    // remove elements
    public void remove(E element){
        remove(node(element));
    }

    private void remove(Node<E> node){
        size--;
        // 1. 删除度为2的节点
        if (node.hasTwoChildren()){
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        // 2. 删除度为1的节点
        // Step1: 找到
        Node<E> replacement = node.left != null ? node.left: node.right;
        // Step2: 双亲相认

        if (replacement != null){
            replacement.parent = node.parent;
            if (node.parent == null) root = replacement;
            if (node == node.parent.left) node.parent.left = replacement;
            else  node.parent.right = replacement;
        }

        // 3. 删除叶子结点
        // 当node 为root节点
        else if (node.parent == null) root = null;

        else{
            if (node == node.parent.left) node.parent.left = null;
            else  node.parent.right = null;
        }
    }

    // does it contain an element or not
    public boolean contains(E element){

        return node(element) != null;
    }


    // 迭代器
    private Node<E> node(E element) {
        Node<E> node = root;
        int cmp = 0;
        while (node != null){
            cmp = compare(element, node.element);
            if (cmp > 0){
                node = node.right;
            }
            else if (cmp < 0) {
                node = node.left;
            }
            else {
                node.element = element;
                return node;
            }
        }
        return null;
    }

}
