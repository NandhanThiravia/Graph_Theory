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

        boolean[] isVisited = new boolean[vertexCount];
        int motherVertex = -1;

        for (int vertexIndex = 0; vertexIndex < vertexCount; ++vertexIndex) {
            if (!isVisited[vertexIndex]) {
                stack.push(vertexIndex);
                isVisited[vertexIndex] = true;
                motherVertex = vertexIndex;

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();

                    ArrayList<Integer> neighbourList = list.get(vertex);
                    for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                        if (!isVisited[neighbourList.get(neighbourIndex)]) {
                            isVisited[neighbourList.get(neighbourIndex)] = true;
                            stack.add(neighbourList.get(neighbourIndex));
                        }
                    }
                }
            }

        }

        // Reset the variables
        stack.clear();
        for (int vertexIndex = 0; vertexIndex < vertexCount; ++vertexIndex) {
            isVisited[vertexIndex] = false;
        }

        // Check if MotherVertex variable really contains the MotherVertex
        isVisited[motherVertex] = true;
        stack.push(motherVertex);
        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            ArrayList<Integer> neighbourList = list.get(vertex);
            for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                if (!isVisited[neighbourList.get(neighbourIndex)]) {
                    isVisited[neighbourList.get(neighbourIndex)] = true;
                    stack.add(neighbourList.get(neighbourIndex));
                }
            }
        }
        
        // If there exists any Non Visited Node then this is not a Mother Vertex and hence return -1
        for (int vertexIndex = 0; vertexIndex < vertexCount; ++vertexIndex) {
            if (!isVisited[vertexIndex]) {
                return -1;
            }
                
        }

        return motherVertex;
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
