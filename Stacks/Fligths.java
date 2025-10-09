package Stacks;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;

public class Fligths {
    public static void findPath(String src, String dst, ArrayList<String[]> data) {
        ArrayList<String> visited = new ArrayList<>();
        Stack<String>     stack   = new Stack<>();

        stack.push(src);
        // Search for all possible paths from the starting point
        while (!stack.empty()) {
            String curr = stack.peek();
            if (curr.equals(dst)) {
                while (!stack.empty()) {
                    System.out.print(stack.pop() + " ");
                }
                return;
            }
            visited.add(curr);

            for (String[] pair : data) {
                // Every pair is ["X", "Y"], which is from and to
                if (pair[0].equals(curr) && !visited.contains(pair[1])) {
                    stack.push(pair[1]);
                    break;
                }
            }

            if (stack.peek().equals(curr)) {
                stack.pop();
            }
        }

        if (stack.empty()) {
            System.out.println("Path doesn't exist");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Stacks/flights.txt"));

        ArrayList<String[]> data = new ArrayList<>();

        // The following loop only takes care of READING THE FILE
        while (sc.hasNextLine()) {
            // A one-liner: data.add(sc.nextLine().split(" "));
            String line = sc.nextLine();
            String[] splitted = line.split(" ");  // ["Y", "Z"]
            data.add(splitted);
        }

        sc.close();
        sc = new Scanner(System.in);

        System.out.println("Where do you wanna start and where do you wanna go?");

        String line = sc.nextLine();
        String[] splitted = line.split(" ");
        sc.close();

        String from = splitted[0];
        String to   = splitted[1];

        findPath(from, to, data);

    }
}
