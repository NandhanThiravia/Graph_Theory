package com.other.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Input:
// -------
// 2
// 2 2 
// 1 0 0 1
// 1 2
// 1 1

// Output:
// --------
// 0 1 1 0
// 0 0

class Distance {
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
    // private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, {
    // 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    // private static final int TOTAL_DIRECTIONS = 8;

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private static final int TOTAL_DIRECTIONS = 4;

    private static final int X = 0;
    private static final int Y = 1;

    private static final int SPACE = 0;
    private static final int WALL = 1;

    public ArrayList<ArrayList<Integer>> nearest(ArrayList<ArrayList<Integer>> matrix, int rows, int cols) {
        ArrayList<ArrayList<Integer>> distanceMatrix = new ArrayList<ArrayList<Integer>>();

        Queue<Coordinate> queue = new LinkedList<Coordinate>();
        Coordinate coordinate = null;

        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            distanceMatrix.add(rowIndex, new ArrayList<Integer>());

            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                if (matrix.get(rowIndex).get(colIndex) == SPACE) {
                    distanceMatrix.get(rowIndex).add(colIndex, Integer.MAX_VALUE);
                } else if (matrix.get(rowIndex).get(colIndex) == WALL) {
                    distanceMatrix.get(rowIndex).add(colIndex, 0);

                    // Add it to Stack for processing
                    coordinate = new Coordinate(rowIndex, colIndex);
                    queue.add(coordinate);
                }
            }
        }

        int x = 0, y = 0;
        int next_x = 0, next_y = 0;
        int distance = 0;
        int direction = 0;

        while (!queue.isEmpty()) {
            coordinate = queue.poll();

            x = coordinate.x;
            y = coordinate.y;
            direction = 0;

            while (direction < TOTAL_DIRECTIONS) {
                next_x = x + DIRECTIONS[direction][X];
                next_y = y + DIRECTIONS[direction][Y];

                if ((next_x >= 0 && next_x < rows) && (next_y >= 0 && next_y < cols)) {
                    if (matrix.get(next_x).get(next_y) == SPACE) {
                        distance = distanceMatrix.get(x).get(y) + 1;
                        if (distanceMatrix.get(next_x).get(next_y) > distance) {
                            distanceMatrix.get(next_x).set(next_y, distance);

                            queue.add(new Coordinate(next_x, next_y));
                        }
                    }
                }
                direction = direction + 1;
            }
        }

        return distanceMatrix;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);

            ArrayList<ArrayList<Integer>> list = new ArrayList<>(row);

            for (int i = 0; i < row; i++) {
                ArrayList<Integer> temp = new ArrayList<>(col);
                list.add(i, temp);
            }
            int k = 0;
            str = read.readLine().trim().split(" ");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int val = Integer.parseInt(str[k++]);
                    list.get(i).add(j, val);
                }
            }
            long start = System.currentTimeMillis();
            print(new Distance().nearest(list, row, col), row, col);
            long end = System.currentTimeMillis();
            System.out.println("Time taken " + (end - start) + " ms");
        }
    }

    static void print(ArrayList<ArrayList<Integer>> sol, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(sol.get(i).get(j) + " ");
            }
        }

        System.out.println();
    }
}
