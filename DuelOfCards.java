import java.util.*;

public class DuelOfCards {
    public static int bestScore(int[] nums) {
        int gapCount = 0;
        int score = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                gapCount++;
            }
            else {
                if (gapCount != 0) {
                    score++;
                    gapCount--;
                }
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[2*n + 1];
        int[] bob = new int[2*n + 1];

        for (int i = 0; i < n; i++) {
            nums[sc.nextInt()] = 1;
        }

        for (int i = 1; i < 2*n + 1; i++) {
            if (nums[i] == 0) {
                bob[i] = 1;
            }
        }

        int best = bestScore(nums);
        int worst = n - bestScore(bob);
        
        System.out.println(worst + " " + best);
    }
}
