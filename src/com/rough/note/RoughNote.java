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

    public static void main(String[] args) {
        int rate = 48000;
        System.out.println(Integer.toHexString((rate >> 0) & 0xFF));
        System.out.println(Integer.toHexString((rate >> 8) & 0xFF));
        System.out.println(Integer.toHexString((rate >> 16) & 0xFF));
        System.out.println(Integer.toHexString((rate >> 24) & 0xFF));
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
