package _03_list;

public class LinkedList<E> extends AbstractList<E>{

    private Node<E> first;
    private Node<E> last;

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

        if (index < (size >> 1) ){// 证明index在链表的前半部分，从first开始找
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;

        }else { // 证明index在链表的后半部分，从last开始找
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }


    }

    @Override
    public void add(int index, E element) {

        //tail
        if (index == size){
            Node<E> oldLast = last;
            Node<E> newLast = new Node<>(element, oldLast, null);
            last = newLast;
            if (size == 0){ // 空链表的情况下的添加
                first = newLast;
            }else { // 正常尾部添加
                oldLast.next = newLast;
            }
        }
        if (index == 0){ //head
            Node<E> oldFirst = first;
            Node<E> newFirst = new Node<>(element,null, oldFirst);
            first = newFirst;
            oldFirst.prev = oldFirst;
        } else { //current
            Node<E> preNode = node(index - 1);
            Node<E> nextNode = preNode.next;
            Node<E> newNode = new Node<>(element, preNode, nextNode);
            preNode.next = newNode;
            nextNode.prev = newNode;
        }




    }

    @Override
    public E get(int index) { return node(index).value; }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }


}
