package com.adjacency.list;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(6);
        // Vertex 0
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        
        // Vertex 1
        graph.addEdge(1, 0);
        graph.addEdge(1, 4);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        
        // Vertex 2
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        
        // Vetex 3
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);
        graph.addEdge(3, 2);
        
        // Vertex 4
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(4, 3);
        
        // Vertex 5
        graph.addEdge(4, 5);
        graph.addEdge(5, 4);
        graph.display();
        
        graph.breadthFirstTraversal();
        graph.depthFirstTraversal();
    }
}
