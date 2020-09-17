package com.other.problems;

import java.util.Comparator;
import java.util.PriorityQueue;
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

    private int shortestPath(int grid[][], int size) {
        if (size == 1) {
            return grid[0][0];
        }
        int rows = size;
        int cols = size;

        Node source = new Node(0, 0, grid[0][0]);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node x, Node y) {
                return x.dist - y.dist;
            }
        });

        // As per Djikstra's Algorithm, the least value in the Array are finalized will not change its value in the next iteration
        boolean[][] isVisited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];

        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            for (int colIndex = 0; colIndex < cols; ++colIndex) {
                distance[rowIndex][colIndex] = Integer.MAX_VALUE;
            }
        }

        distance[source.x][source.y] = source.dist;
        priorityQueue.add(source);

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            isVisited[node.x][node.y] = true;

            int x = node.x, y = node.y;
            int next_x = 0, next_y = 0;
            int direction = 0;
            while (direction < TOTAL_DIRECTIONS) {
                next_x = x + DIRECTIONS[direction][X];
                next_y = y + DIRECTIONS[direction][Y];

                if ((next_x >= 0 && next_x < rows) && (next_y >= 0 && next_y < cols) && !isVisited[next_x][next_y]) {
                    if (distance[next_x][next_y] > distance[x][y] + grid[next_x][next_y]) {
                        distance[next_x][next_y] = distance[x][y] + grid[next_x][next_y];
                        priorityQueue.add(new Node(next_x, next_y, distance[next_x][next_y]));
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
