package com.rough.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//{ Driver Code Starts
//Initial Template for Java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graph {
    int size;

    Graph(int V) {
        this.size = V;
    }

    Graph() {
    }

    static void addEdge(ArrayList<ArrayList<Integer>> list, int u, int v) {
        list.get(u).add(v);
        // list.get(v).add(u);
    }
}

class DriverClass {
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

            // int nov = sc.nextInt();
            // int edg = sc.nextInt();

            new Graph(nov);
            for (int i = 0; i < nov + 1; i++)
                list.add(i, new ArrayList<Integer>());

            str = read.readLine().trim().split(" ");
            int k = 0;
            for (int i = 1; i <= edg; i++) {
                int u = Integer.parseInt(str[k++]);
                int v = Integer.parseInt(str[k++]);
                new Graph().addEdge(list, u, v);
            }
            str = read.readLine().trim().split(" ");
            int s = Integer.parseInt(str[0]);
            int d = Integer.parseInt(str[1]);
            System.out.println(new Path().countPaths(list, s, d));
        }
    }
}
//} Driver Code Ends

//User function Template for Java

/*
 * g : Adjacency list of the graph s : source node d : destination node
 */

class Path {
    static int countPaths(ArrayList<ArrayList<Integer>> g, int s, int d) {
        int source = s;
        int destination = d;

        int countOfPath = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);

        if (source == destination) {
            countOfPath = 1;
        }

        while (!queue.isEmpty()) {
            int vertex = queue.poll();

            ArrayList<Integer> neighbourList = g.get(vertex);
            for (int neighbourIndex = 0; neighbourIndex < neighbourList.size(); ++neighbourIndex) {
                int neighbour = neighbourList.get(neighbourIndex);
                if (neighbour != destination) {
                    queue.add(neighbour);
                } else {
                    countOfPath += 1;
                }
            }
        }

        // System.out.println("Number of Path from " + source + " to " + destination + "
        // are " + countOfPath);
        return countOfPath;
    }
}
