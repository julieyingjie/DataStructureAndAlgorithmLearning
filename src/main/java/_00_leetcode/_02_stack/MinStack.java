package _00_leetcode._02_stack;

import java.util.Map;
import java.util.Stack;

/**
 * 155. Min Stack
 * https://leetcode.com/problems/min-stack/
 * 考察如何对流动数据进行记录，操作
 * 不要遍历
 * idea1: 两个stack 容器 data 和 min
 * idea2: 利用node 的思想。node 当中，有val, next 指针, min 指针
 */

public class MinStack {

        // idea1: 两个stack 容器 data 和 min
//        private Stack<Integer> dataStack;
//        private Stack<Integer> minStack;
//
//        public MinStack() {
//                dataStack = new Stack<>();
//                minStack = new Stack<>();
//        }
//
//        public void push(int val) {
//                dataStack.push(val);
//
//                if (minStack.isEmpty())  minStack.push(val);
//                else if (val <= minStack.peek()){ // 注意这里，有个=号
//                        minStack.push(val);
//                }
//        }
//
//        public void pop() {
//                int pop = 0;
//                if (!dataStack.isEmpty()) {
//                        pop = dataStack.pop();
//                }
//
//                if (pop == minStack.peek()) minStack.pop();
//
//        }
//
//        public int top() {
//            return dataStack.peek();
//        }
//
//        public int getMin() {
//                if (minStack.isEmpty()) throw new RuntimeException("Stack is empty");
//            return minStack.peek();
//        }


        // idea 2: 利用node 的思想。node 当中，有val, next 指针, min 指针

        // 1. 创造这么一个node 作为inner class
        private static class Node{
                int value;
                Node next;
                int min;

                public Node(int value, Node next, int min) {
                        this.value = value;
                        this.next = next;
                        this.min = min;
                }
        }

        Node head;

        public MinStack() {
                // dummy node
                head = new Node(0,null,Integer.MAX_VALUE);
        }

        public void push(int val) {
               head = new Node(val, head, Math.min(val, head.min));
        }

        public void pop() {
                head = head.next;
        }

        public int top() {

                return head.value;
        }

        public int getMin() {

                return head.min;
        }


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
}
