package com.rough.note;

// { Driver Code Starts
// Initial Template for Java
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class DriverClass {
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

            System.out.println(new Islands().findIslands(list, N, M));
        }
    }
}
// } Driver Code Ends

// User function Template for Java

class Islands {

    // Function to find the number of island in the given list
    // N, M: size of list row and column respectively
    static int findIslands(ArrayList<ArrayList<Integer>> list, int N, int M) {
        boolean isVisited[] = new boolean[N];
        Stack<Integer> stack = new Stack<Integer>();

        int islands = 0;
        for (int index = 0; index < N; ++index) {
            if (!isVisited[index]) {
                isVisited[index] = true;
                stack.add(index);
                ++islands;

                while (!stack.isEmpty()) {
                    int vertex = stack.pop();

                    ArrayList<Integer> innerList = list.get(vertex);
                    for (int innerIndex = 0; innerIndex < innerList.size(); ++innerIndex) {
                        if (!isVisited[innerList.get(innerIndex)]) {
                            isVisited[innerList.get(innerIndex)] = true;
                            stack.add(innerList.get(innerIndex));
                        }
                    }
                }
            }
        }
        return islands;
    }
}
