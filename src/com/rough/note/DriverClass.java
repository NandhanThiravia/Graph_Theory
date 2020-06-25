package com.rough.note;

// { Driver Code Starts
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class DriverClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            int nov = sc.nextInt();
            int edg = sc.nextInt();

            for (int i = 0; i < nov; i++)
                list.add(i, new ArrayList<Integer>());

            for (int i = 1; i <= edg; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                list.get(u).add(v);
                list.get(v).add(u);
            }
            int x = sc.nextInt();
            if (x >= nov)
                System.out.println("-1");
            else
                System.out.println(new Level_of_Nodes().levels(list, 0, x));
        }
    }
}// } Driver Code Ends

/*
 * ArrayList<ArrayList<Integer>> list: to represent graph containing vertices
 * and edges between them x: starting vertex of graph in: represent vertex whose
 * level we have to find
 */
class Level_of_Nodes {

    static int levels(ArrayList<ArrayList<Integer>> list, int x, int in) {
        int levelOfDestination = -1;
        int destinationVertex = in;

        int startVertex = x;
        Queue<Integer> levelQueue = new LinkedList<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean isVisited[] = new boolean[list.size()];

        queue.add(startVertex);
        levelQueue.add(startVertex);
        isVisited[startVertex] = true;

        // System.out.println("Destination Vertex: " + destinationVertex);
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            int level = levelQueue.poll();

            // System.out.println("Vertex: " + vertex + " Level: " + level);
            if (vertex == destinationVertex) {
                levelOfDestination = level;
                break;
            }

            ArrayList<Integer> neighbourList = list.get(vertex);
            for (int index = 0; index < neighbourList.size(); ++index) {
                int neighbour = neighbourList.get(index);
                if (!isVisited[neighbour]) {
                    isVisited[neighbour] = true;
                    queue.add(neighbour);
                    levelQueue.add(level + 1);
                }
            }
        }
        return levelOfDestination;
    }
}
