package com.rough.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//{ Driver Code Starts
import java.util.ArrayList;
import java.util.Stack;

class DriverClass {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            String st[] = read.readLine().trim().split("\\s+");
            int edg = Integer.parseInt(st[0]);
            int nov = Integer.parseInt(st[1]);

            for (int i = 0; i < nov + 1; i++)
                list.add(i, new ArrayList<Integer>());

            String s[] = read.readLine().trim().split("\\s+");
            int p = 0;
            for (int i = 1; i <= edg; i++) {
                int u = Integer.parseInt(s[p++]);
                int v = Integer.parseInt(s[p++]);
                list.get(u).add(v);
            }

            int[] res = new TopologicalSort().topoSort(list, nov);

            if (check(list, nov, res) == true)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }

    static boolean check(ArrayList<ArrayList<Integer>> list, int V, int[] res) {
        int[] map = new int[V];
        for (int i = 0; i < V; i++) {
            map[res[i]] = i;
        }
        for (int i = 0; i < V; i++) {
            for (int v : list.get(i)) {
                if (map[i] > map[v])
                    return false;
            }
        }
        return true;
    }
}
//} Driver Code Ends

/* Complete the function below */

/*
 * ArrayList<ArrayList<>Integer>list: to represent graph containing 'N' vertices
 * and edges between them N: represent number of vertices
 */
class TopologicalSort {
    static int[] topoSort(ArrayList<ArrayList<Integer>> list, int N) {
        boolean isVisited[] = new boolean[N];
        Stack<Integer> outputStack = new Stack<Integer>();
        Stack<Integer> stack = new Stack<Integer>();

        for (int index = 0; index < N; ++index) {
            if (!isVisited[index]) {
                isVisited[index] = true;
                stack.push(index);

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();

                    while (hasNonVisitedNeighbour(vertex, list, isVisited)) {
                        stack.push(vertex);
                        vertex = nextNonVisitedNeighbour(vertex, list, isVisited);
                        isVisited[vertex] = true;
                    }
                    outputStack.push(vertex);
                }
            }
        }

        int index = 0;
        int[] outputArray = new int[outputStack.size()];
        while (!outputStack.isEmpty()) {
            outputArray[index] = outputStack.pop();
            ++index;
        }
        return outputArray;
    }

    static boolean hasNonVisitedNeighbour(int vertex, ArrayList<ArrayList<Integer>> list, boolean[] isVisited) {
        ArrayList<Integer> innerList = list.get(vertex);
        for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
            if (!isVisited[innerList.get(innerIndex)]) {
                return true;
            }
        }
        return false;
    }

    static int nextNonVisitedNeighbour(int vertex, ArrayList<ArrayList<Integer>> list, boolean[] isVisited) {
        ArrayList<Integer> innerList = list.get(vertex);
        for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
            if (!isVisited[innerList.get(innerIndex)]) {
                return innerList.get(innerIndex);
            }
        }
        return -1;
    }
}
