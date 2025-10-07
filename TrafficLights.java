import java.util.*;

/*
3
2 4
3 1
6 1

 */

public class TrafficLights {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] red = new int[n];
        int[] green = new int[n];
        int maxRed = 0;
        for (int i = 0; i < n; i++) {
            red[i] = sc.nextInt();
            if (red[i] > maxRed) {
                maxRed = red[i];
            }
            green[i] = sc.nextInt();
        }

        // simulation begins at 0 + maxRed + 1 seconds
        int simSecond = 0;
        for (;;) {
            if (isAllGreen(red, green, simSecond)) {
                break;
            }
            simSecond++;
        }
        System.out.println(simSecond);
    }

    public static boolean isAllGreen(int[] red, int[] green, int simSecond) {
        for (int light = 0; light < red.length; light++) {
            if (simSecond % (red[light] + green[light]) < red[light]) {
                return false;
            }
        }

        return true;
    }
}
