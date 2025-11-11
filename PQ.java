import java.util.PriorityQueue;
import java.util.Random;

class Task implements Comparable<Task> {
    String name;
    int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String toString() {
        return priority + " " + name;
    }

    @Override
    public int compareTo(Task o) {
        return priority - o.priority;
    }
}

public class PQ {
    public static void main(String[] args) {
        Random rand = new Random(6);
        String[] tasks = new String[]{
            "wake up",
            "clean floors",
            "do dishes",
            "do laundry",
            "turn on music"
        };

        PriorityQueue<Task> pq = new PriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            int namePriority = rand.nextInt(tasks.length);
            int taskPriority = rand.nextInt(100);
            Task t = new Task(tasks[namePriority], taskPriority);
            System.out.println(t);
            pq.add(t);
        }

        System.out.println();
        System.out.println(pq);
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.println(pq.poll());
        }

    }
}
