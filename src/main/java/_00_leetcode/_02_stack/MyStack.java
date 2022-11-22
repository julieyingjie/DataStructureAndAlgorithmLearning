package _00_leetcode._02_stack;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * https://leetcode.com/problems/implement-stack-using-queues/
 * 两个queue 来实现
 */

public class MyStack {
    private Queue<Integer> data;
    private Queue<Integer> help;

    public MyStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(int x) {
        data.offer(x); // add 也行，但是offer是queue独有的。
    }

    public int pop() {
        while (data.size()> 1) help.offer(data.poll());
        Integer poll = data.poll();

        //改变指向的交换
        swap();

        return poll;
    }

    public int top() {
        while (data.size()> 1) help.offer(data.poll());
        Integer poll = data.poll();
        help.offer(poll);

        //改变指向的交换
        swap();

        return poll;
    }

    public boolean empty() {

        return data.isEmpty() && help.isEmpty();
    }

    public void swap(){ // 不需要传参是因为操作的是成员变量
        Queue<Integer> temp = help;
        help = data;
        data = temp;
    }
}
