package com.adjacency.list;

import com.adjacency.list.Graph2.Type;

public class Graph2Test {
    private static void analyzeGraph1() {
        Graph2 graph = new Graph2(6, Type.UNDIRECTED);

        // Adjacency List Representation
        // ----------------------------
        // [0] -> 1(1) -> 4(1) -> NULL
        // [1] -> 0(1) -> 4(1) -> 3(1) -> 2(1) -> NULL
        // [2] -> 1(1) -> 3(1) -> NULL
        // [3] -> 1(1) -> 2(1) -> 4(1) -> NULL
        // [4] -> 0(1) -> 1(1) -> 3(1) -> 5(1) -> NULL
        // [5] -> 4(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        System.out.println("Graph 1");
        System.out.println("--------");
        graph.display();
        System.out.println();
        graph.breadthFirstTraversal();
        System.out.println();
        graph.depthFirstTraversal();
    }

    private static void analyzeGraph2() {
        Graph2 graph = new Graph2(10, Type.DIRECTED);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1(1) -> 5(1) -> NULL
        // [1] -> 7(1) -> NULL
        // [2] -> NULL
        // [3] -> 2(1) -> 4(1) -> 8(1) -> NULL
        // [4] -> 8(1) -> NULL
        // [5] -> NULL
        // [6] -> 0(1) -> 1(1) -> 2(1) -> NULL
        // [7] -> NULL
        // [8] -> 2(1) -> NULL
        // [9] -> 4(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(1, 7);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(3, 8);
        graph.addEdge(4, 8);
        graph.addEdge(6, 0);
        graph.addEdge(6, 1);
        graph.addEdge(6, 2);
        graph.addEdge(8, 2);
        graph.addEdge(9, 4);

        System.out.println();
        System.out.println("Graph 2");
        System.out.println("--------");
        graph.display();
        System.out.println();
        graph.topologicalSort();
    }

    private static void analyzeGraph3() {
        Graph2 graph = new Graph2(6, Type.DIRECTED);
        // Second Graph
        // Adjacency List Representation
        // ----------------------------
        // [0] -> NULL
        // [1] -> NULL
        // [2] -> 3(1) -> NULL
        // [3] -> 1(1) -> NULL
        // [4] -> 0(1) -> 1(1) -> NULL
        // [5] -> 0(1) -> 2(1) -> NULL

        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(5, 0);
        graph.addEdge(5, 2);

        System.out.println();
        System.out.println("Graph 3");
        System.out.println("--------");
        graph.display();
        System.out.println();
        graph.topologicalSort();
    }

    private static void analyzeGraph4() {
        Graph2 graph = new Graph2(7, Type.UNDIRECTED);

        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> 2(1) -> NULL
        // [1] -> 0(1) -> 3(1) -> NULL
        // [2] -> 0(1) -> 3(1) -> NULL
        // [3] -> 1(1) -> 2(1) -> NULL
        // [4] -> 5(1) -> 6(1) -> NULL
        // [5] -> 4(1) -> 6(1) -> NULL
        // [6] -> 4(1) -> 5(1) -> NULL

        // Has 2 Islands in this graph

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);

        graph.addEdge(1, 3);

        graph.addEdge(2, 3);

        graph.addEdge(4, 5);
        graph.addEdge(4, 6);

        graph.addEdge(5, 6);

        graph.display();

        graph.breadthFirstTraversal();

        graph.depthFirstTraversal();
    }

    private static void analyzeGraph5() {
        Graph2 graph = new Graph2(6, Type.UNDIRECTED);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1 -> 2 -> 4 -> NULL
        // [1] -> 0 -> 3 -> NULL
        // [2] -> 0 -> 4 -> NULL
        // [3] -> 1 -> 2 -> 5 -> NULL
        // [4] -> 0 -> 2 -> 5 -> NULL
        // [5] -> 3 -> 4 -> NULL

        graph.addEdge(0, 4);
        graph.addEdge(0, 2);
        graph.addEdge(0, 1);

        graph.addEdge(1, 3);

        graph.addEdge(2, 4);

        graph.addEdge(3, 5);
        graph.addEdge(3, 2);

        graph.addEdge(4, 5);

        graph.display();
        graph.shortestDistance(0);
        graph.shortestDistance(1);

        graph.isCyclic();
    }

    private static void analyzeGraph6() {
        Graph2 graph = new Graph2(3, Type.UNDIRECTED);

        // Adjacency List Representation
        // -------------------------------
        // [0] -> 1(1) -> 2(1) -> NULL
        // [1] -> 0(1) -> 2(1) -> NULL
        // [2] -> 1(1) -> 0(1) -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        graph.display();
        graph.isCyclic();
    }

    private static void analyzeGraph7() {
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(3, 4);
        graph.addEdge(5, 1);
        graph.display();

        CycleNode node = null;
        if (null != (node = graph.isCycleDetectedDirected())) {
            System.out.println("Cycle Detected between " + node.currentNode + " and " + node.parentNode);
        } else {
            System.out.println("NO Cycle Detected");
        }
    }

    private static void analyzeGraph8() {
        Graph graph = new Graph(8);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1 -> NULL
        // [1] -> 2 -> NULL
        // [2] -> 3 -> NULL
        // [3] -> 4 -> NULL
        // [4] -> 5 -> NULL
        // [5] -> 6 -> NULL
        // [6] -> 7 -> NULL
        // [7] -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 0);
        graph.display();

        CycleNode node = null;
        if (null != (node = graph.isCycleDetectedDirected())) {
            System.out.println("Cycle Detected between " + node.currentNode + " and " + node.parentNode);
        } else {
            System.out.println("NO Cycle Detected");
        }
    }

    private static void analyzeGraph9() {
        Graph graph = new Graph(6);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1 -> NULL
        // [1] -> NULL
        // [2] -> 1 -> 3 -> NULL
        // [3] -> 4 -> NULL
        // [4] -> 5 -> NULL
        // [5] -> 2 -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        graph.display();

        CycleNode node = null;
        if (null != (node = graph.isCycleDetectedDirected())) {
            System.out.println("Cycle Detected between " + node.currentNode + " and " + node.parentNode);
        } else {
            System.out.println("NO Cycle Detected");
        }
    }

    private static void analyzeGraph10() {
        Graph graph = new Graph(6);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 1 -> NULL
        // [2] -> 1 -> 3 -> NULL
        // [3] -> 4 -> NULL
        // [4] -> 5 -> NULL
        // [5] -> 1 -> NULL

        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);
        graph.display();

        CycleNode node = null;
        if (null != (node = graph.isCycleDetectedDirected())) {
            System.out.println("Cycle Detected between " + node.currentNode + " and " + node.parentNode);
        } else {
            System.out.println("NO Cycle Detected");
        }
    }

    private static void analyzeGraph11() {
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);

        graph.display();

        CycleNode node = null;
        if (null != (node = graph.isCycleDetectedDirected())) {
            System.out.println("Directed: Cycle Detected between " + node.currentNode + " and " + node.parentNode);
        } else {
            System.out.println("Directed: NO Cycle Detected");
        }

        if (graph.isCycleDetectedUndirected()) {
            System.out.println("Undirected: Cycle Detected");
        } else {
            System.out.println("Undirected: NO Cycle Detected");
        }
    }

    private static void analyzeGraph12() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 3 -> 2 -> NULL
        // [1] -> 4 -> 3 -> NULL
        // [2] -> NULL
        // [3] -> NULL
        // [4] -> NULL

        Graph graph = new Graph(5);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);

        graph.display();
        graph.topologicalSort();
    }

    private static void analyzeGraph13() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 2 -> 1 -> NULL
        // [1] -> 3 -> NULL
        // [2] -> 3 -> NULL
        // [3] -> 5 -> 4 -> NULL
        // [4] -> NULL
        // [5] -> NULL

        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        graph.display();
        graph.topologicalSort();
        graph.unitShortestDistance(0);
    }

    private static void analyzeGraph14() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 4(1) -> 1(2) -> NULL
        // [1] -> 2(3) -> NULL
        // [2] -> 3(6) -> NULL
        // [3] -> NULL
        // [4] -> 5(4) -> 2(2) -> NULL
        // [5] -> 3(1) -> NULL

        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 6);
        graph.addEdge(4, 2, 2);
        graph.addEdge(4, 5, 4);
        graph.addEdge(5, 3, 1);

        graph.display();
//        graph.topologicalSort();
//        graph.nonUnitShortestDistance(0);
        graph.topoShortestDistance(0);
    }

    private static void analyzeGraph15() {
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 4(1) -> 1(2) -> NULL
        // [1] -> 2(3) -> NULL
        // [2] -> 3(6) -> NULL
        // [3] -> NULL
        // [4] -> 5(4) -> 2(2) -> NULL
        // [5] -> 3(1) -> NULL

        Graph graph = new Graph(7);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 5, 1);
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 15);
        graph.addEdge(3, 4, 10);
        graph.addEdge(5, 6, 1);
        graph.addEdge(6, 1, 1);

        graph.display();
        graph.topologicalSort();
        graph.nonUnitShortestDistance(0);
    }

    public static void main(String[] args) {
        // analyzeGraph1();
        // analyzeGraph2();
        // analyzeGraph3();
        // analyzeGraph4();
        // analyzeGraph5();
        analyzeGraph6();
        // analyzeGraph7();
        // analyzeGraph8();
        // analyzeGraph9();
        // analyzeGraph10();
        // analyzeGraph11();
        // analyzeGraph12();
        // analyzeGraph13();
        // analyzeGraph14();
        // analyzeGraph15();
    }
}
