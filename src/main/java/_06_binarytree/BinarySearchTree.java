package _06_binarytree;

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

    // add elements
    public void add(E element){
        //对添加元素进行判定
        if (element == null) throw new IllegalArgumentException("element can not be null");

        // 添加第一个节点
        if (root == null){
            root = new Node<>(element, null);
            size++;
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
        Node<E> newNode = new Node<>(element,parent);
        if (cmp > 0) parent.right = newNode;
        else parent.left = newNode;
        size++;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) return comparator.compare(e1, e2);
        return ((Comparable<E>) e1).compareTo(e2);
    }

    // remove elements
    public void remove(E element){

    }

    // does it contain an element or not
    public boolean contains(E element){

        return false;
    }

}
