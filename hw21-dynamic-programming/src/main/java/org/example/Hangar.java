package org.example;

import java.util.Random;
import java.util.Stack;

public class Hangar {

    private final int width;
    private final int height;
    private final int[][] map;
    private final Random rand = new Random();
    private int[] L;
    private int[] R;
    private int[] heights;

    public Hangar(int width, int height, int F) {
        this.width = width;
        this.height = height;
        map = new int[height][width];
        generateMap(F);
    }

    private void generateMap(int F) {
        int x, y;
        for (int i = 0; i < F; i++) {
            do {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            } while (map[y][x] == 1);
            map[y][x] = 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                s.append(map[i][j] == 0 ? ". " : "1 ");
            s.append("\n");
        }
        return s.toString();
    }

    public int getMaxArea() {
        int maxArea = 0;
        heights = new int[width];
        L = new int[width];
        R = new int[width];
        for (int y = 0; y < height; y++) {
            calculateHeights(y);
            calculateL();
            calculateR();

        }
        for (int x = 0; x < width; x++) {
            int h = heights[x];
            int w = R[x] - L[x] + 1;
            int area = h * w;
            if (area > maxArea)
                maxArea = area;
        }

        return maxArea;
    }

    private void calculateR() {
        Stack<Integer> stack = new Stack<>();
        for (int x = 0; x < width; x++) {
            while (!stack.isEmpty()) {
                if (heights[stack.peek()] > heights[x])
                    R[stack.pop()] = x - 1;
                else
                    break;
            }
            stack.push(x);
        }
        while (!stack.isEmpty()) {
            R[stack.pop()] = width - 1;
        }
    }

    private void calculateL() {
        Stack<Integer> stack = new Stack<>();
        for (int x = width - 1; x >= 0; x--) {
            while (!stack.isEmpty()) {
                if (heights[stack.peek()] > heights[x])
                    L[stack.pop()] = x + 1;
                else
                    break;
            }
            stack.push(x);
        }
        while (!stack.isEmpty()) {
            L[stack.pop()] = 0;
        }
    }

    private void calculateHeights(int y) {
        for (int x = 0; x < width; x++) {
            if (map[y][x] == 1)
                heights[x] = 0;
            else
                heights[x] += 1;
        }
    }

}
