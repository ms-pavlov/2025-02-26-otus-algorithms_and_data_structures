package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class ChristmasTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();

        int[][] tree = new int[n][];

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().trim().split("\\s+");
            tree[i] = new int[line.length];
            for (int j = 0; j < line.length; j++) {
                tree[i][j] = Integer.parseInt(line[j]);
            }
        }

        System.out.println(getMaxSum(n, tree));
    }

    private static int getMaxSum(int n, int[][] tree) {
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = Arrays.copyOf(tree[i], tree[i].length);
        }

        for(int i = n - 2; i >= 0; i --) {
            for(int j = 0; j < dp[i].length; j ++) {
                dp[i][j] = dp[i][j] + Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }

        return dp[0][0];
    }
}