package com.other.problems;

import java.util.ArrayList;
import java.util.Scanner;

class Node {
    int x, y, dist;

    Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.dist = d;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

public class ShortestPathArray {
    private static final int X = 0;
    private static final int Y = 1;
//    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
//    private static final int TOTAL_DIRECTIONS = 8;

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private static final int TOTAL_DIRECTIONS = 4;

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

        @Override
        public boolean equals(Object obj) {
            boolean isEqual = false;
            if (obj instanceof Coordinate) {
                Coordinate coordinate = (Coordinate) obj;
                if (coordinate.x == this.x && coordinate.y == this.y) {
                    isEqual = true;
                }
            }
            return isEqual;
        }
    }

    private Coordinate minElement(int[][] distance, boolean[][] isFinalized) {
        Coordinate minCoordinate = new Coordinate(-1, -1);
        int minValue = Integer.MAX_VALUE;

        for (int rowIndex = 0; rowIndex < distance.length; ++rowIndex) {
            for (int colIndex = 0; colIndex < distance[0].length; ++colIndex) {
                if (!isFinalized[rowIndex][colIndex]) {
                    if (minValue > distance[rowIndex][colIndex]) {
                        minValue = distance[rowIndex][colIndex];
                        minCoordinate.x = rowIndex;
                        minCoordinate.y = colIndex;
                    }
                }
            }
        }
        return minCoordinate;
    }

    private int shortestPath(int grid[][], int size) {
        if (size == 1) {
            return grid[0][0];
        }
        int rows = size;
        int cols = size;

        Node source = new Node(0, 0, grid[0][0]);

        ArrayList<Node> minCostPathList = new ArrayList<Node>();
        minCostPathList.add(source);

        ArrayList<Coordinate> coordinatesList = new ArrayList<Coordinate>();
        // As per Djikstra's Algorithm, the least value in the Array are finalized will not change its value in the next iteration
        boolean[][] isFinalized = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];

        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                distance[rowIndex][colIndex] = Integer.MAX_VALUE;
                coordinatesList.add(new Coordinate(rowIndex, colIndex));
            }
        }

        distance[source.x][source.y] = source.dist;

        while (!coordinatesList.isEmpty()) {
            Coordinate coordinate = minElement(distance, isFinalized);
            isFinalized[coordinate.x][coordinate.y] = true;
            coordinatesList.remove(coordinate);

            int x = coordinate.x, y = coordinate.y;
            int next_x = 0, next_y = 0;
            int direction = 0;
            while (direction < TOTAL_DIRECTIONS) {
                next_x = x + DIRECTIONS[direction][X];
                next_y = y + DIRECTIONS[direction][Y];

                if ((next_x >= 0 && next_x < rows) && (next_y >= 0 && next_y < cols) && !isFinalized[next_x][next_y]) {
                    if (distance[next_x][next_y] > distance[x][y] + grid[next_x][next_y]) {
                        distance[next_x][next_y] = distance[x][y] + grid[next_x][next_y];
                    }
                }
                direction += 1;
            }

        }

        return distance[rows - 1][cols - 1];
    }

    private static int shortest(int grid[][], int size) {
        return new ShortestPathArray().shortestPath(grid, size);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();

            int grid[][] = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            long start = System.currentTimeMillis();
            System.out.println(shortest(grid, n));
            long end = System.currentTimeMillis();
            System.out.println("Time taken is " + (end - start) + " ms");
        }
    }
}
