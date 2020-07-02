package com.other.problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Algorithm to find the Largest Unit Area in a Matrix containing 1s and 0s.
public class Area {
    class Coordinate {
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

    // Below are the 8 directions
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    private static final int TOTAL_DIRECTIONS = 8;
    private static final int X = 0;
    private static final int Y = 1;

    private static final int SPACE = 0;
    private static final int WALL = 1;

    int findMaxArea(int rows, int cols, int matrix[][]) {
        int maxArea = 0;

        int direction = 0;
        int x = 0, y = 0;
        int next_x = 0, next_y = 0;
        int localArea = 0;
        Coordinate coordinate = null;
        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                if (matrix[rowIndex][colIndex] == WALL) {
                    // Mark the Coordinate as Visited by changing the matrix index value to 0, i.e. SPACE
                    matrix[rowIndex][colIndex] = SPACE;
                    localArea = 1;

                    coordinate = new Coordinate(rowIndex, colIndex);
                    queue.add(coordinate);

                    while (!queue.isEmpty()) {
                        coordinate = queue.poll();
                        x = coordinate.x;
                        y = coordinate.y;
                        direction = 0;

                        while (direction < TOTAL_DIRECTIONS) {
                            next_x = x + DIRECTIONS[direction][X];
                            next_y = y + DIRECTIONS[direction][Y];

                            if ((next_x >= 0 && next_x < rows) && (next_y >= 0 && next_y < cols)) {
                                if (matrix[next_x][next_y] == WALL) {
                                    matrix[next_x][next_y] = SPACE;
                                    localArea += 1;

                                    coordinate = new Coordinate(next_x, next_y);
                                    queue.add(coordinate);
                                }
                            }
                            direction += 1;
                        }
                    }
                    if (maxArea < localArea) {
                        maxArea = localArea;
                    }
                }
            }
        }

        return maxArea;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int g[][] = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++)
                    g[i][j] = sc.nextInt();
            }

            System.out.println(new Area().findMaxArea(n, m, g));
        }
    }
}