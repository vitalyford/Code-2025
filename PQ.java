import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

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

class TaskCompareByName implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.name.compareTo(o2.name);
    }
    
}

class TaskCompareByPriority implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.priority - o2.priority;
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

        // mapping name of the comparator to the comparator itself
        Map<String, Comparator<Task>> comparators = new HashMap<>(); 
        comparators.put("name", new TaskCompareByName());
        comparators.put("priority", new TaskCompareByPriority());

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the comparator you wanna call: ");
        String userComparator = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        PriorityQueue<Task> pq = new PriorityQueue<>(comparators.get(userComparator));

        for (int i = 0; i < 10; i++) {
            int namePriority = rand.nextInt(tasks.length);
            int taskPriority = rand.nextInt(100);
            Task t = new Task(tasks[namePriority], taskPriority);
            System.out.println(t);
            taskList.add(t);
            pq.add(t);
        }
        Collections.sort(taskList);

        System.out.println();
        System.out.println(pq);
        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.println(pq.poll());
        }

    }
}
