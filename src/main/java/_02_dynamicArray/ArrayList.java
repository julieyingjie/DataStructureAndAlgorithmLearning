package _02_dynamicArray;

public class ArrayList<E> {

    private int size;
    private E[] elements;
    /**
     * Default initial capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     *
     */
    private static final int ELEMENT_NOT_FOUND = -1;


    public ArrayList(){
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity){ // capacity 修饰的是容器的大小。但容器不一定会装满
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY: capacity;
        this.elements = (E[]) new Object[capacity];
    }

    //Number of elements
    public int size() {
        return size;
    }

    //Is it empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Clear all the elements
    public void clear() {
        size = 0;
    }

    // Add elements to the end
    public void add(E element) {
        add(size, element);
    }

    // Add elements to the index position
    public void add(int index, E element) {
        // check the input index is valid or not
        rangeCheckForAdd(index);
        // ensure the capacity
        ensureCapacity(size + 1);

        //head, current position
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        // all and tail
        elements[index] = element;

        // change on the size
        size++;
    }

    // Returns the element corresponding to the index position
    public E get(int index) {
        // Check the input index is valid or not
        rangeCheck(index);
        return elements[index];
    }

    //Contains a certain element
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }


    // Set the element at the index position
    public E set(int index, E element) {
        rangeCheckForAdd(index);

        E oldElement = elements[index];
        elements[index] = element;

        return oldElement;
    }


    // Delete elements to the index position
    public E remove(int index) {
        // check index
        rangeCheck(index);

        // remove, head, current
        E oldElement = elements[index];
        for (int i = index + 1; i < size ; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;

        return oldElement;
    }

    // Return the index of the element
    public int indexOf(E element) { // element 有可能 NULL
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null){ return i;}
            }
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])){ return i;}
        }
        return ELEMENT_NOT_FOUND;
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

    private void rangeCheckForAdd(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: " + index + " , but size is: " + size);
        }
    }

    private void rangeCheck(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index: " + index + " , but size is: " + size);
        }
    }


}
