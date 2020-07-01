package com.other.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Grouping {
    // Sample Input
    // ------------
    // 2
    // 4 7
    // OOOOXXO OXOXOOX XXXXOXO OXXXOOO
    // 10 3
    // XXO OOX OXO OOO XOX XOX OXO XXO XXX OOO
    //
    // Output:
    // ---------
    // 4
    // 6
    public static void main(String[] args) throws IOException {
        // Scanner sc = new Scanner(System.in);
        // int t = sc.nextInt();

        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int row = Integer.parseInt(str[0]);
            int col = Integer.parseInt(str[1]);
            char[][] matrix = new char[row][col];

            str = read.readLine().trim().split(" ");
            for (int rowIndex = 0; rowIndex < row; ++rowIndex) {
                for (int colIndex = 0; colIndex < col; ++colIndex) {
                    matrix[rowIndex][colIndex] = str[rowIndex].charAt(colIndex);
                }
            }

            System.out.println(countOfGroups(matrix, row, col));
        }
    }

    // Below are the 4 directions
    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    private static final int TOTAL_DIRECTIONS = 4;
    private static final int X = 0;
    private static final int Y = 1;

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

    private static int countOfGroups(char[][] matrix, int row, int col) {
        char countOfGroups = 0;

        int next_x = 0;
        int next_y = 0;
        int x = 0;
        int y = 0;
        int direction = 0;
        Coordinate coordinate = null;
        StackItem stackItem = null;
        Stack<StackItem> stack = new Stack<StackItem>();

        for (int rowIndex = 0; rowIndex < row; ++rowIndex) {
            for (int colIndex = 0; colIndex < col; ++colIndex) {
                if (matrix[rowIndex][colIndex] == 'X') {
                    stack.clear();
                    x = rowIndex;
                    y = colIndex;
                    direction = 0;
                    coordinate = new Coordinate(x, y);
                    stackItem = new StackItem(coordinate, direction);
                    stack.push(stackItem);

                    while (!stack.isEmpty()) {
                        stackItem = stack.pop();
                        x = stackItem.coordinate.x;
                        y = stackItem.coordinate.y;
                        direction = stackItem.direction;
                        matrix[x][y] = countOfGroups;

                        while (direction < TOTAL_DIRECTIONS) {
                            next_x = x + DIRECTIONS[direction][X];
                            next_y = y + DIRECTIONS[direction][Y];

                            // Check if the new index is within the matrix
                            if ((next_x >= 0 && next_x < row) && (next_y >= 0 && next_y < col)) {
                                if (matrix[next_x][next_y] == 'X') {
                                    coordinate = new Coordinate(x, y);
                                    stackItem = new StackItem(coordinate, direction + 1);
                                    stack.push(stackItem);

                                    direction = 0;
                                    x = next_x;
                                    y = next_y;
                                    matrix[x][y] = countOfGroups;
                                    continue;
                                }
                            }
                            direction += 1;
                        }
                    }
                    countOfGroups += 1;
                }
            }
        }
        return countOfGroups;
    }
}
