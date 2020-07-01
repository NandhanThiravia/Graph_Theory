package com.other.problems;

//Initial Template for Java
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Islands {
    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class StackFrame {
        Coordinate coordinate;
        int direction;

        public StackFrame(Coordinate coordinate, int direction) {
            this.coordinate = coordinate;
            this.direction = direction;
        }
    }

    // Function to find the number of island in the given list
    // N, M: size of list row and column respectively
    // private static int[][] DIRECTIONS = new int[8][2];

    // Entry Description in the matrix
    // 1-> Unvisited Cell
    // 2-> Visited Cell. Unvisited Cell once visited is changed to 2

    // Below are the 8 directions
    private static int[][] DIRECTIONS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
    private static int TOTAL_DIRECTION = 8;
    private static int X = 0;
    private static int Y = 1;

    private static Coordinate getValidCoordinate(int[][] matrix, int N, int M) {
        Coordinate coordinate = null;
        boolean hasFound = false;
        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < M; ++y) {
                if (matrix[x][y] == 1) {
                    coordinate = new Coordinate(x, y);
                    hasFound = true;
                    break;
                }
            }
            if (hasFound) {
                break;
            }
        }
        return coordinate;
    }

    static int findIslands(ArrayList<ArrayList<Integer>> list, int N, int M) {
        int matrix[][] = new int[N][M];
        for (int index = 0; index < N; ++index) {
            ArrayList<Integer> innerList = list.get(index);
            for (int innerIndex = 0; innerIndex < M; ++innerIndex) {
                matrix[index][innerIndex] = innerList.get(innerIndex);
            }
        }
        return findIslands(matrix, N, M);
    }

    static int findIslands(int[][] matrix, int N, int M) {
        int islands = 0;
        Coordinate coordinate = null;
        Stack<StackFrame> stack = new Stack<StackFrame>();

        while (true) {
            coordinate = getValidCoordinate(matrix, N, M);
            if (coordinate == null) {
                break;
            }
            islands += 1;

            matrix[coordinate.x][coordinate.y] = 2;
            StackFrame item = new StackFrame(coordinate, 0);
            stack.push(item);

            while (!stack.isEmpty()) {
                item = stack.pop();

                int next_x = 0;
                int next_y = 0;
                int x = item.coordinate.x;
                int y = item.coordinate.y;
                int direction = item.direction;

                while (direction < TOTAL_DIRECTION) {
                    next_x = x + DIRECTIONS[direction][X];
                    next_y = y + DIRECTIONS[direction][Y];

                    // Check if the new index is within the matrix
                    if ((next_x >= 0 && next_x < N) && (next_y >= 0 && next_y < M)) {
                        // Check if the matrix is visited or not
                        if (matrix[next_x][next_y] == 1) {
                            // Save Old location
                            stack.push(new StackFrame(new Coordinate(x, y), direction + 1));

                            // Move to the new location
                            x = next_x;
                            y = next_y;
                            matrix[x][y] = 2;
                            direction = 0;
                            continue;
                        }
                    }
                    direction += 1;
                }
            }
        }
        return islands;
    }

    // Sample Input:
    // --------------
    // 2
    // 3 3
    // 1 1 0 0 0 1 1 0 1
    // 4 4
    // 1 1 0 0 0 0 1 0 0 0 0 1 0 1 0 0
    //
    // Output:
    // --------
    // 2
    // 2

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            ArrayList<ArrayList<Integer>> list = new ArrayList<>(N);

            // creating arraylist of arraylist
            for (int i = 0; i < N; i++) {
                ArrayList<Integer> temp = new ArrayList<>(M);
                list.add(i, temp);
            }

            // adding elements to the arraylist of arraylist
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int val = sc.nextInt();
                    list.get(i).add(j, val);
                }
            }

            System.out.println(findIslands(list, N, M));
        }
        sc.close();
    }
}