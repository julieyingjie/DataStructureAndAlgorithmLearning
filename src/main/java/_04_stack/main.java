package _04_stack;

public class main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 10; i < 20; i++) {
            stack.push(i);
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }
}
