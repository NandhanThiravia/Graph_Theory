package com.other.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/*
 * ArrayList<ArrayList<Integer>> g: arraylist of arraylist which represents
 * graph n: represents number of vertex in graph
 */
class MotherVertex {
    int findMother(ArrayList<ArrayList<Integer>> list, int vertexCount) {
        Stack<Integer> stack = new Stack<Integer>();

        for (int vertexIndex = 0; vertexIndex < vertexCount; ++vertexIndex) {
            boolean[] isVisited = new boolean[vertexCount];
            int visitedCount = 0;
            
            stack.push(vertexIndex);
            isVisited[vertexIndex] = true;
            ++visitedCount;

            while (!stack.isEmpty()) {
                int vertex = stack.pop();

                ArrayList<Integer> neighbourList = list.get(vertex);
                for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                    if (!isVisited[neighbourList.get(neighbourIndex)]) {
                        isVisited[neighbourList.get(neighbourIndex)] = true;
                        stack.add(neighbourList.get(neighbourIndex));
                        ++visitedCount;
                    }
                }
            }

            if (visitedCount == vertexCount) {
                return vertexIndex;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        // Scanner sc = new Scanner(System.in);
        // int t = sc.nextInt();
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String str[] = read.readLine().trim().split(" ");

            int nov = Integer.parseInt(str[0]);
            int edg = Integer.parseInt(str[1]);

            for (int i = 0; i < nov + 1; i++)
                list.add(i, new ArrayList<Integer>());

            int k = 0;
            str = read.readLine().trim().split(" ");
            for (int i = 1; i <= edg; i++) {
                int u = Integer.parseInt(str[k++]);
                int v = Integer.parseInt(str[k++]);
                list.get(u).add(v);

            }
            long start = System.currentTimeMillis();
            System.out.println(new MotherVertex().findMother(list, nov));
            long end = System.currentTimeMillis();
            System.out.println("Time taken is " + (end - start) + " ms");
        }
    }
}
