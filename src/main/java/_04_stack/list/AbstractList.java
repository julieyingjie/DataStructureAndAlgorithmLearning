package _04_stack.list;

//Abstract 的用处：不能实现一个实例
public abstract class AbstractList<E> implements List<E> {
    protected int size; // 有效元素的个数

    //Number of elements
    public int size() {
        return size;
    }

    //Is it empty
    public boolean isEmpty() {
        return size == 0;
    }


    // Add elements to the end
    public void add(E element) {
        add(size, element);
    }

    //Contains a certain element
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    protected void rangeCheck(int index){
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("index: " + index + " , but size is: " + size);
        }
    }

    protected void rangeCheckForAdd(int index){
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: " + index + " , but size is: " + size);
        }
    }







}
