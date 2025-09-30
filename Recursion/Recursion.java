package Recursion;

public class Recursion {
    public static double factorial(double num) {
        // return num == 1 ? 1 : num * factorial(num - 1);
        if (num == 1) 
            return 1;
        return num * factorial(num - 1);
    }

    public static void hanoi(int n, String src, String dst, String spare) {
        if (n == 1) {
            System.out.println("Move from " + src + " to " + dst);
        }
        else {
            hanoi(n - 1, src, spare, dst);
            hanoi(1, src, dst, spare);
            hanoi(n - 1, spare, dst, src);
        }
    }

    public static String backward(String input) {
        // return input.equals("") ? "" : backward(input.substring(1)) + input.charAt(0);
        if (input.equals("")) {
            return "";
        }
        return backward(input.substring(1)) + input.charAt(0);
    }

    public static int rabbits(int n) {
        if (n <= 2) return 1;
        return rabbits(n - 1) + rabbits(n - 2);
    }

    public static int rabbitsFor(int n) {
        if (n <= 2) return 1;

        int prev1 = 1;
        int prev2 = 1;
        int count = 0;

        for (int i = 3; i <= n; i++) {
            count = prev1 + prev2;
            prev2 = prev1;
            prev1 = count;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(backward("A Santa lived as a devil at NASA"));
        System.out.println(rabbitsFor(120));

        hanoi(4, "A", "B", "C");
    }
}
