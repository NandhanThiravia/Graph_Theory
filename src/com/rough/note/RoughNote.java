package com.rough.note;

import java.util.ArrayList;

public class RoughNote {
    public static void strcmp(String[] args) {
        String start = "toon";
        String target = "tOon";

        if (start == target) {
            System.out.println("same");
        } else {
            System.out.println("different");
        }
    }

    private static void change(int matrix[][]) {
        matrix[0][0] = 30;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        matrix.add(new ArrayList<Integer>());
        matrix.add(new ArrayList<Integer>());
        matrix.add(new ArrayList<Integer>());
        matrix.add(new ArrayList<Integer>());
        matrix.add(new ArrayList<Integer>());
        System.out.println("Length: " + matrix.size());

        ArrayList<Integer> list = matrix.get(0);
        list.add(20);
        System.out.println(list.get(0));
        System.out.println(matrix.get(0).get(0));
    }

    private void testArray() {
        boolean[] visited = new boolean[5];
        visited[0] = true;

        for (int index = 0; index < 5; ++index) {
            System.out.print(visited[index] + " ");
        }
        System.out.println();
        changeArray(visited);
        for (int index = 0; index < 5; ++index) {
            System.out.print(visited[index] + " ");
        }
    }

    private void changeArray(boolean[] visited) {
        visited[1] = true;
        visited[2] = true;
        for (int index = 0; index < 5; ++index) {
            System.out.print(visited[index] + " ");
        }
        System.out.println();
    }
}
