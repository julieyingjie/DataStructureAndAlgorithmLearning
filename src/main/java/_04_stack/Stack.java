package _04_stack;

import _04_stack.list._single.ArrayList;


public class Stack<E> {

    // 以变量的方式来引入，不用extends,这样后期变化不会太大

    private ArrayList<E> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }


}
