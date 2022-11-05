package _03_list._single;

import _03_list.AbstractList;

/**
 * 单向循环链表
 * 尾结点指向头结点
 */

public class SingleCircularLinkedList<E> extends AbstractList<E>{

        private Node<E> first; // initially null;

        @Override
        public void clear() {
            first = null;
            size = 0;
        }

        @Override
        public E get(int index) {
            return node(index).value;
        }

        /**
         * 迭代器，去找到某个index上的node
         *
         * @param index
         */
        private Node<E> node(int index) {
            rangeCheck(index);
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }


        @Override
        public void add(int index, E element) {
            rangeCheckForAdd(index);

            //head
            if (index == 0) {
                Node<E> newFirst = new Node<E>(element, first);
                Node<E> last = (size == 0) ? newFirst: node(size - 1);

//                Node<E> last = node(size - 1); 错误！！！ 空链表的情况下，插入第一个节点时，这个代码会出错。
                last.next = newFirst;
                first = newFirst;
            } else {
                //current, tail
                Node<E> preNode = node(index - 1);
                preNode.next = new Node<>(element, preNode.next);
            }

            size++;

        }

        @Override
        public E set(int index, E element) {
            Node<E> oldNode = node(index);
            E oldValue = oldNode.value;
            oldNode.value = element;

            return oldValue;
        }

        @Override
        public E remove(int index) {
            rangeCheck(index);
            Node<E> removedNode = first;
            //head
            if (index == 0) {
                if (size == 1) first = null;
                else {
                    Node<E> last = node(size - 1); // 一定注意这个地方，要先拿到last的值。要不然node()会有问题。
                    first = first.next;
                    last.next = first;
                }
            } else {
                //current, tail
                Node<E> preNode = node(index - 1);
                removedNode = preNode.next;
                preNode.next = removedNode.next;
            }

            size--;

            return removedNode.value;
        }

        @Override
        public int indexOf(E element) {
            if (element == null) {
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

        private static class Node<E> {
            E value;
            Node<E> next;

            public Node(E value,Node<E> next) {
                this.value = value;
                this.next = next;
            }
        }
    }


