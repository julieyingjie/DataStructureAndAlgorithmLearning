package _05_Queue;

import _05_Queue.list._double.LinkedList;

public class Queue <E>{

    private LinkedList<E> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }

    public void enqueue(E element){
        list.add(element);
    }

    public E dequeue(){
        return list.remove(0);
    }

    // get the head element of the queue
    public E front(){
        return list.get(0);
    }
}
