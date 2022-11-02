package _02_dynamicArray;

public class ArrayList<E> {

    private int size;
    private E[] elements;

    public ArrayList(){

    }

    public ArrayList(int capacity){

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

    //Contains a certain element
    public boolean contains(E element) {
        return false;
    }

    // Add elements to the end
    public void add(E element) {
        add(size, element);

    }

    // Add elements to the index position
    public void add(int index, E element) {
        // check the input index is valid or not
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: " + index + " , but size is: " + size);
        }

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
        return elements[index];
    }

    // Set the element at the index position
    public E set(int index, E element) {
        return null;
    }


    // Delete elements to the index position
    public E remove(int index) {
        return null;
    }

    // Return the index of the element
    public int indexOf(E element) {
        return 0;
    }


}
