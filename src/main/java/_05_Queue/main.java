package _05_Queue;


public class main {
    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        for (int i = 10; i < 20; i++) {
            queue.enqueue(i);
        }

        while (!queue.isEmpty()){
            System.out.println(queue.dequeue());
        }


    }
}
