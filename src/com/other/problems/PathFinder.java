package com.other.problems;

import java.util.Scanner;
import java.util.Stack;

// Input:
// -------
// 2
// 4
// 3 0 0 0 0 3 3 0 0 1 0 3 0 2 3 3 
// 3
// 0 3 2 3 0 0 1 0 0

// Output:
// --------
// 1
// 0
public class PathFinder {
    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    static class StackItem {
        Coordinate coordinate;
        int direction;

        public StackItem(Coordinate coordinate, int direction) {
            this.coordinate = coordinate;
            this.direction = direction;
        }
    }

    // Below are the 8 directions
    // private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1
    // }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    // private static int TOTAL_DIRECTION = 8;

    // Below are the 4 directions
    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private static int TOTAL_DIRECTION = 4;
    private static int X = 0;
    private static int Y = 1;

    // A value of cell 1 means Source.
    private static final int SOURCE = 1;

    // A value of cell 2 means Destination.
    private static final int DESTINATION = 2;

    // A value of cell 3 means Blank cell.
    private static final int BLANK = 3;

    // A value of cell 0 means Blank Wall.
    private static final int WALL = 0;

    static int hasPath(int[][] matrix, int N, int M) {
        int hasPath = 0;
        Coordinate coordinate = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == SOURCE) {
                    coordinate = new Coordinate(i, j);
                }
            }
        }

        if (null == coordinate) {
            return hasPath;
        }
        // System.out.println("Source found at " + coordinate);

        Stack<StackItem> stack = new Stack<StackItem>();
        int direction = 0;
        StackItem stackItem = new StackItem(coordinate, direction);
        stack.push(stackItem);

        while (!stack.isEmpty()) {
            stackItem = stack.pop();
            int next_x = 0;
            int next_y = 0;
            int x = stackItem.coordinate.x;
            int y = stackItem.coordinate.y;
            direction = stackItem.direction;
            matrix[x][y] = WALL;
            // System.out.println("Rolling back to [" + x + ", " + y + "]");

            while (direction < TOTAL_DIRECTION) {
                next_x = x + DIRECTIONS[direction][X];
                next_y = y + DIRECTIONS[direction][Y];

                // Check if the new index is within the matrix
                if ((next_x >= 0 && next_x < N) && (next_y >= 0 && next_y < M)) {
                    if (matrix[next_x][next_y] == DESTINATION) {
                        hasPath = 1;
                        // System.out.println("Found Destination at [" + next_x + ", " + next_y + "]");
                        break;
                    } else if (matrix[next_x][next_y] == BLANK) {
                        // Save the instance
                        stackItem = new StackItem(new Coordinate(x, y), direction + 1);
                        stack.push(stackItem);

                        // Move to the new location
                        x = next_x;
                        y = next_y;
                        matrix[x][y] = WALL;
                        direction = 0;
                        // System.out.println("Moving to [" + x + ", " + y + "]");
                        continue;
                    }
                }
                direction += 1;
            }

            if (hasPath == 1) {
                stack.clear();
                stack = null;
                break;
            }
        }

        return hasPath;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int N = sc.nextInt();

            int matrix[][] = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int val = sc.nextInt();
                    matrix[i][j] = val;
                }
            }

            System.out.println(hasPath(matrix, N, N));
        }
        sc.close();
    }

}
