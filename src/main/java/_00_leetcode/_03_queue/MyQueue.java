package _00_leetcode._03_queue;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * https://leetcode.com/problems/implement-queue-using-stacks/
 * 2 个 stack 来实现
 */

public class MyQueue {

    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (inStack.isEmpty() && outStack.isEmpty()) throw new RuntimeException("Queue is empty");

            // outStack 为空的时候
            // 将inStack中所有元素都装入outStack, 然后pop outStack
        else if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
            //            return outStack.pop(); (和下面情况合并)
        }// outStack 不为空
        // 直接pop outStack
        return outStack.pop();
    }

    public int peek() {
        if (inStack.isEmpty() && outStack.isEmpty()) throw new RuntimeException("Queue is empty");

            // outStack 为空的时候
            // 将inStack中所有元素都装入outStack, 然后pop outStack
        else if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) outStack.push(inStack.pop());
            //            return outStack.pop(); (和下面情况合并)
        }// outStack 不为空
        // 直接pop outStack
        return outStack.peek(); // 这个地方是和pop唯一的区别之处
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
