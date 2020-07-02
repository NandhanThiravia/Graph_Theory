package com.other.problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RottenOranges {
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private static final int TOTAL_DIRECTIONS = 4;

    private static final int X = 0;
    private static final int Y = 1;

    private static final int FRESH_ORANGE = 1;
    private static final int ROTTEN_ORANGE = 2;

    class Item {
        int x;
        int y;
        int time;

        public Item(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Coordinate: [" + x + ", " + y + "] & Time: " + time;
        }
    }

    private int findTimeToRot(int rows, int cols, int[][] matrix) {
        int time = 0;

        boolean hasRottenOranges = false;
        Queue<Item> queue = new LinkedList<Item>();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                if (matrix[rowIndex][colIndex] == ROTTEN_ORANGE) {
                    queue.add(new Item(rowIndex, colIndex, 0));
                    hasRottenOranges = true;
                }
            }
        }

        if (!hasRottenOranges) {
            return -1;
        }

        int direction = 0;
        int x = 0, y = 0, localTime = 0;
        int next_x = 0, next_y = 0;
        Item item = null;
        while (!queue.isEmpty()) {
            item = queue.poll();
            x = item.x;
            y = item.y;
            localTime = item.time;
            direction = 0;

            if (time < localTime) {
                time = localTime;
            }

            while (direction < TOTAL_DIRECTIONS) {
                next_x = x + DIRECTIONS[direction][X];
                next_y = y + DIRECTIONS[direction][Y];

                if ((next_x >= 0 && next_x < rows) && (next_y >= 0 && next_y < cols)) {
                    if (matrix[next_x][next_y] == FRESH_ORANGE) {
                        matrix[next_x][next_y] = ROTTEN_ORANGE;
                        queue.add(new Item(next_x, next_y, localTime + 1));
                    }
                }
                direction += 1;
            }
        }

        boolean hasFreshOranges = false;
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                if (matrix[rowIndex][colIndex] == FRESH_ORANGE) {
                    hasFreshOranges = true;
                    break;
                }
            }
        }

        if (hasFreshOranges) {
            return -1;
        }

        return time;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();

            int matrix[][] = new int[rows][cols];

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++)
                    matrix[i][j] = sc.nextInt();
            }

            System.out.println(new RottenOranges().findTimeToRot(rows, cols, matrix));
        }
    }
}
