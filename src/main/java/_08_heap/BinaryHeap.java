package _08_heap;

import _08_heap.printer.BinaryTreeInfo;

import java.util.Comparator;

// 默认实现maximum Heap
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator, E[] elements) {
        super(comparator);
//        this.elements = elements;//浅拷贝 直接将引用传进来，比较危险
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];

            for (int i = 0; i < size; i++) {
                this.elements[i] = elements[i];//深拷贝 将值一个一个放进来
            }

        }

        // 进行建堆操作
         heapify();
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

    private void heapify(){ // 建堆
        // 1. Top-down sift up  一般情况不用这种方法，因为这种方法的efficiency特别差。
//        for (int i = 0; i < size; i++) {
//            siftUp(i);
//        }

        // 2. Bottom-up sift down 从第一个非叶节点开始
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }
    private void siftDown(int index) {

        // 因为当换到叶节点上时，所有的叶节点都不需要再交换了。
        // 所有叶节点的个数是总节点 n / 2， 所以只需要操作前面一半的节点
        int half = size >> 1;
        E element = elements[index];

        // siftDown 的时候必须保证index是非叶节点
        while(index < half){
            // index has 2 conditions:
            // 1. left, right both exists
            // 2. left only
            // 默认有左孩子

            // 拿到左孩子的下标
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex]; // 取得值

            // 也有可能有右孩子
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

        // 拿到最后一个节点
        int lastIndex = --size;

        // 用最后一个节点上的值，去覆盖到root上去，也就是目前root的值是最后一个节点的值
        elements[0] = elements[lastIndex];

        // 删掉最后一个节点；
        elements[lastIndex] = null;

        // 检查是否仍然符合大顶堆，否，则进行修改操作
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
