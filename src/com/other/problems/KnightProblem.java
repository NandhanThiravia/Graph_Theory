package com.other.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}

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

class QueueItem {
    Coordinate coordinate;
    int step;

    public QueueItem(Coordinate coordinate, int step) {
        this.coordinate = coordinate;
        this.step = step;
    }
}

class KnightProblem {
    // KnightPos : knight position coordinates
    // targetPos : target position coordinated
    // N : square matrix size
    private static final int X = 0;
    private static final int Y = 1;
    private static final int TOTAL_DIRECTIONS = 8;
    private static final int[][] DIRECTIONS = { { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 } };

    private static final int SOURCE = 1;
    private static final int DESTINATION = 2;
    private static final int UNVISITED = 0;
    private static final int VISITED = 3;

    public int minStepToReachTarget(int knightpos[], int targetpos[], int N) {
        // If Knight's and Target's position are same then return 0
        if (knightpos[0] == targetpos[0] && knightpos[1] == targetpos[1]) {
            return 0;
        }

        // Fetch Knight's and Target's Coordinates
        Coordinate knightCoordinate = new Coordinate(knightpos[0] - 1, knightpos[1] - 1);
        Coordinate targetCoordinate = new Coordinate(targetpos[0] - 1, targetpos[1] - 1);

        // Initialize Chess Board Matrix
        int[][] chess = new int[N][N];
        chess[knightCoordinate.x][knightCoordinate.y] = SOURCE;
        chess[targetCoordinate.x][targetCoordinate.y] = DESTINATION;
        int shortestSteps = Integer.MAX_VALUE;

        // Using Queue to apply BFS Algorithm
        Queue<QueueItem> queue = new LinkedList<QueueItem>();

        QueueItem item = new QueueItem(knightCoordinate, 0);
        queue.add(item);
        chess[knightCoordinate.x][knightCoordinate.y] = VISITED;

        Coordinate coordinate = null;
        int step = 0;
        int direction = 0;
        int x = 0, y = 0;
        int next_x = 0, next_y = 0;
        boolean isPathFound = false;
        while (!queue.isEmpty()) {
            item = queue.poll();
            step = item.step;
            coordinate = item.coordinate;

            direction = 0;
            x = coordinate.x;
            y = coordinate.y;
            while (direction < TOTAL_DIRECTIONS) {
                next_x = x + DIRECTIONS[direction][X];
                next_y = y + DIRECTIONS[direction][Y];

                if ((next_x >= 0 && next_x < N) && (next_y >= 0 && next_y < N)) {
                    if (chess[next_x][next_y] == UNVISITED) {
                        chess[next_x][next_y] = VISITED;
                        item = new QueueItem(new Coordinate(next_x, next_y), step + 1);
                        queue.add(item);
                    } else if (chess[next_x][next_y] == DESTINATION) {
                        shortestSteps = step + 1;
                        isPathFound = true;
                        break;
                    }
                }
                ++direction;
            }
            if (isPathFound) {
                break;
            }
        }
        queue.clear();
        return shortestSteps;
    }

    public static void main(String[] args) {

        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            int knightpos[] = new int[2];
            knightpos[0] = sc.nextInt();
            knightpos[1] = sc.nextInt();
            int targetpos[] = new int[2];
            targetpos[0] = sc.nextInt();
            targetpos[1] = sc.nextInt();

            KnightProblem T = new KnightProblem();
            long start = System.currentTimeMillis();
            System.out.println(T.minStepToReachTarget(knightpos, targetpos, n));
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start) + " ms");
            t--;
        }
    }
}
