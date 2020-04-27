package com.adjacency.list;

public class GraphTest {
    private static void analyzeGraph1() {
        Graph graph1 = new Graph(6);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 4 -> 1 -> NULL
        // [1] -> 2 -> 3 -> 4 -> 0 -> NULL
        // [2] -> 3 -> 1 -> NULL
        // [3] -> 2 -> 4 -> 1 -> NULL
        // [4] -> 5 -> 3 -> 1 -> 0 -> NULL
        // [5] -> 4 -> NULL

        graph1.addEdge(0, 1);
        graph1.addEdge(0, 4);
        graph1.addEdge(1, 0);
        graph1.addEdge(1, 4);
        graph1.addEdge(1, 3);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 1);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 1);
        graph1.addEdge(3, 4);
        graph1.addEdge(3, 2);
        graph1.addEdge(4, 0);
        graph1.addEdge(4, 1);
        graph1.addEdge(4, 3);
        graph1.addEdge(4, 5);
        graph1.addEdge(5, 4);

        System.out.println("Graph 1");
        System.out.println("--------");
        graph1.display();
        System.out.println();
        graph1.breadthFirstTraversal();
        System.out.println();
        graph1.depthFirstTraversal();
    }

    private static void analyzeGraph2() {
        Graph graph2 = new Graph(10);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 5 -> 1 -> NULL
        // [1] -> 7 -> NULL
        // [2] -> NULL
        // [3] -> 8 -> 4 -> 2 -> NULL
        // [4] -> 8 -> NULL
        // [5] -> NULL
        // [6] -> 2 -> 1 -> 0 -> NULL
        // [7] -> NULL
        // [8] -> 2 -> NULL
        // [9] -> 4 -> NULL

        graph2.addEdge(0, 1);
        graph2.addEdge(0, 5);
        graph2.addEdge(1, 7);
        graph2.addEdge(3, 2);
        graph2.addEdge(3, 4);
        graph2.addEdge(3, 8);
        graph2.addEdge(4, 8);
        graph2.addEdge(6, 0);
        graph2.addEdge(6, 1);
        graph2.addEdge(6, 2);
        graph2.addEdge(8, 2);
        graph2.addEdge(9, 4);

        System.out.println();
        System.out.println("Graph 2");
        System.out.println("--------");
        graph2.display();
        System.out.println();
        graph2.topologicalSort();
    }

    private static void analyzeGraph3() {
        Graph graph3 = new Graph(6);
        // Second Graph
        // Adjacency List Presentation
        // ----------------------------
        // [0] -> NULL
        // [1] -> NULL
        // [2] -> 3 -> NULL
        // [3] -> 1 -> NULL
        // [4] -> 1 -> 0 -> NULL
        // [5] -> 2 -> 0 -> NULL

        graph3.addEdge(2, 3);
        graph3.addEdge(3, 1);
        graph3.addEdge(4, 0);
        graph3.addEdge(4, 1);
        graph3.addEdge(5, 0);
        graph3.addEdge(5, 2);

        System.out.println();
        System.out.println("Graph 3");
        System.out.println("--------");
        graph3.display();
        System.out.println();
        graph3.topologicalSort();
    }

    private static void analyzeGraph4() {
        Graph graph4 = new Graph(7);

        // Adjacency List Presentation
        // ----------------------------
        // [0] -> 2 -> 1 -> NULL
        // [1] -> 0 -> 3 -> NULL
        // [2] -> 3 -> 0 -> NULL
        // [3] -> 2 -> 1 -> NULL
        // [4] -> 6 -> 5 -> NULL
        // [5] -> 6 -> 4 -> NULL
        // [6] -> 5 -> 4 -> NUL

        // Has 2 Islands in this graph

        graph4.addEdge(0, 1);
        graph4.addEdge(0, 2);
        graph4.addEdge(1, 3);
        graph4.addEdge(1, 0);
        graph4.addEdge(2, 0);
        graph4.addEdge(2, 3);
        graph4.addEdge(3, 1);
        graph4.addEdge(3, 2);
        graph4.addEdge(4, 5);
        graph4.addEdge(4, 6);
        graph4.addEdge(5, 4);
        graph4.addEdge(5, 6);
        graph4.addEdge(6, 4);
        graph4.addEdge(6, 5);

        graph4.display();

        graph4.breadthFirstTraversal();

        graph4.depthFirstTraversal();

        graph4.isCycleDetected();
    }

    public static void analyzeGraph5() {
        Graph graph = new Graph(6);

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
        graph.addEdge(1, 0);

        graph.addEdge(2, 4);
        graph.addEdge(2, 0);

        graph.addEdge(3, 5);
        graph.addEdge(3, 2);
        graph.addEdge(3, 1);

        graph.addEdge(4, 5);
        graph.addEdge(4, 2);
        graph.addEdge(4, 0);

        graph.addEdge(5, 4);
        graph.addEdge(5, 3);

        graph.display();
        graph.shortestDistance(0);
        graph.shortestDistance(1);

        System.out.println();
        graph.isCycleDetected();
    }

    public static void analyze
    8.Graph6() {
        Graph graph = new Graph(6);

        graph.addEdge(0, 1);
        graph.addEdge(1, 0);

        if (graph.isCycleDetected()) {
            System.out.println("Cycle Detected");
        } else {
            System.out.println("NO Cycle Detected");
        }
    }

    public static void main(String[] args) {
        // analyzeGraph1();
        // analyzeGraph2();
        // analyzeGraph3();
        // analyzeGraph4();
        // analyzeGraph5();
        analyzeGraph6();
    }
}
