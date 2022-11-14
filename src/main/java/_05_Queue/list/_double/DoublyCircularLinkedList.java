package _05_Queue.list._double;

import _05_Queue.list.AbstractList;

/**
 * 双向循环链表：尾结点指向头结点，并且，头结点指向尾结点
 * 如果唯一结点：该结点分别指向自己
 */

public class DoublyCircularLinkedList<E> extends AbstractList<E> {

    private Node<E> first;
    private Node<E> last;

    private Node<E> current; // this is for the josephus circle problem

    private static class Node<E> {
        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.value = element;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    // An Iterator
    private Node<E> node(int index) {
        rangeCheck(index);

        if (index < (size >> 1)) {// 证明index在链表的前半部分，从first开始找
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;

        } else { // 证明index在链表的后半部分，从last开始找
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }


    }

    @Override
    public void add(int index, E element) {

        rangeCheckForAdd(index);

        if (index == size) { //tail
            Node<E> oldLast = last;
            last = new Node<>(element, oldLast, first);

            if (size == 0) { // 空链表的情况下的添加
                first = last; // z自指向的操作
                last.next = last;
                last.prev = last;
            } else { // 正常尾部添加
                oldLast.next = last;
                first.prev = last;
            }
        } else if (index == 0) { //head
            Node<E> oldFirst = first;
            Node<E> newFirst = new Node<>(element, null, oldFirst);
            first = newFirst;
            oldFirst.prev = oldFirst;
        } else { //current
            Node<E> preNode = node(index - 1);
            Node<E> nextNode = preNode.next;
            Node<E> newNode = new Node<>(element, preNode, nextNode);
            preNode.next = newNode;
            nextNode.prev = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        return node(index).value;
    }

    @Override
    public E set(int index, E element) {
        Node<E> oldNode = node(index);
        oldNode.value = element;
        return oldNode.value;
    }

    private E remove(Node<E> current){
        int index = indexOf(current.value);
        return remove(index);
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> removedNode = node(index);
        // 链表中只有一个节点需要删除的时候
        if (size == 1){
            first = null;
            last = null;
        }else { // current
            Node<E> prevNode = removedNode.prev;
            Node<E> nextNode = removedNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            // head
            if (index == 0)  first = nextNode;
            // tail
            if (index == size - 1)  last = prevNode;
        }
        size--;
        return removedNode.value;
    }

    @Override
    public int indexOf(E element) {

        if(element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.value == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.value)) return i;
                node = node.next;
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append(",{");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sb.append(node.value);
            node = node.next;
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // the below methods are for the josephus circle problem
    public void reset(){
        current = first;
    }

    public E next(){
        if (current == null) return null;
        current = current.next;
        return current.value;
    }

    // This remove is totally different with the linked list remove method
    // This doesn't have input index
    // It's for: delete the node that current is pointing to, then let current point to the next node
    // 删除current指向的节点。current 有可能为null
    public E remove(){
        if (current == null) return null;
        Node<E> next = current.next;
        E val = remove(current);
        if (size == 0)  current = null;
        else  current = next;

        return val;
    }



}
