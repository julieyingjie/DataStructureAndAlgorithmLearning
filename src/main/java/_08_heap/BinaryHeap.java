package _08_heap;

import _08_heap.printer.BinaryTreeInfo;

import java.util.Comparator;

// 默认实现maximumHeap
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator, E[] elements) {
        super(comparator);
//        this.elements = elements;//浅拷贝
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];

            for (int i = 0; i < size; i++) {
                this.elements[i] = elements[i];//深拷贝

            }

        }

        // heapify();


    }


    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);

        this.elements = (E[]) new Object[DEFAULT_CAPACITY];

    }


    public BinaryHeap(E[] elements) {
        this.elements = elements;
    }

    public BinaryHeap() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }


    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    private void heapify(){
        // 1. Top-down sift up
        for (int i = 0; i < size; i++) {
            siftUp(i);
        }

        // 2. Bottom-up sift down
        for (int i = (size >> 1) - 1; i > 0; i--) {

        }
    }
    private void siftDown(int index) {
        int half = size >> 1;
        E element = elements[index];

        // siftDown 的时候必须保证index是非叶节点
        while(index < half){
            // index has 2 conditions:
            // 1. left, right both exists
            // 2. left only
            // 默认于左孩子
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightChildIndex = childIndex + 1;

            // find the max value between left child and right child
            if (rightChildIndex < size && compare(elements[rightChildIndex], child) > 0){
                child = elements[childIndex = rightChildIndex];
            }

            if (compare(element, child) >= 0) break;
            elements[index] = child;
            index = childIndex;
        }

        elements[index] = element;

    }

    private void siftUp(int index) {
        E e = elements[index];
        while (index > 0){
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(e, parent) <= 0) break;

            // node.val > parent.val
            elements[index] = parent;

//            E temp = elements[index];
//            elements[index] = elements[parentIndex] ;
//            elements[parentIndex] = temp;

            index = parentIndex;
        }
        elements[index] = e;
    }

    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        //扩容, 每次1。5倍。
        int newCapacity = oldCapacity + oldCapacity >> 1; // 这种写法，可以保证精度, 专门用于小数转整数时的丢失
        // 值的迁移
        E[] newElement = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElement[i]  = elements[i];
        }
        elements = newElement;
    }

    private void elementNotNullCheck(E element){
        if (element == null) throw new IllegalArgumentException("Element can not be null");
    }

    private void emptyCheck() {
        if (size == 0) throw new RuntimeException("Heap is empty");
    }

    @Override
    public E remove() {
        emptyCheck();
        E root = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);

        return root;
    }
    
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0){
            elements[0] = element;
            size++;
        }else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int) node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int) node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int) node];
    }
}
