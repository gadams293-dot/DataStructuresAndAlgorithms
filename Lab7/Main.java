class Main {
  public static void main(String[] args) {

    // 1. Define the vertices (buildings)
    java.util.List<String> vertices = java.util.Arrays.asList(
      "Liberal Arts",                // 0
      "Student Services",            // 1
      "Health Careers & Sciences",   // 2
      "Health Technologies Center",  // 3
      "Recreation Center",           // 4
      "Technology Learning Center",  // 5
      "Business & Technology",       // 6
      "Theatre"                      // 7
    );

    // 2. Define edges based on the campus map
    java.util.List<Edge> edges = new java.util.ArrayList<>();

    // Liberal Arts connections
    edges.add(new Edge(0, 1));
    edges.add(new Edge(0, 2));

    // Student Services connections
    edges.add(new Edge(1, 0));
    edges.add(new Edge(1, 2));
    edges.add(new Edge(1, 7));

    // Health Careers & Sciences
    edges.add(new Edge(2, 0));
    edges.add(new Edge(2, 1));
    edges.add(new Edge(2, 3));

    // Health Technologies Center
    edges.add(new Edge(3, 2));
    edges.add(new Edge(3, 4));

    // Recreation Center
    edges.add(new Edge(4, 3));
    edges.add(new Edge(4, 5));

    // Technology Learning Center
    edges.add(new Edge(5, 4));
    edges.add(new Edge(5, 6));

    // Business & Technology
    edges.add(new Edge(6, 5));
    edges.add(new Edge(6, 7));

    // Theatre
    edges.add(new Edge(7, 1));
    edges.add(new Edge(7, 6));

    // 3. Create the graph
    UnweightedGraph<String> graph = new UnweightedGraph<>(vertices, edges);

    // 4. Perform DFS starting from Business & Technology (vertex 6)
    UnweightedGraph<String>.SearchTree dfs = graph.dfs(6);

    // 5. Print search order
    System.out.println("DFS Search Order:");
    for (int v : dfs.getSearchOrder()) {
      System.out.println("Visited: " + graph.getVertex(v));
    }

    // 6. Print parent-child relationships
    System.out.println("\nParent-Child Relationships:");
    for (int i = 0; i < graph.getSize(); i++) {
      int parent = dfs.getParent(i);
      if (parent != -1) {
        System.out.println(graph.getVertex(parent) + " → " + graph.getVertex(i));
      }
    }

    // 7. Print paths
    System.out.println();
    System.out.println("Path to Health Technologies Center:");
    dfs.printPath(3);
    System.out.println("\n");

    System.out.println("Path to Student Services:");
    dfs.printPath(1);
    System.out.println("\n");

    System.out.println("Path to Recreation Center:");
    dfs.printPath(4);
    System.out.println("\n");

    // 8. Print the entire DFS tree
    System.out.println("DFS Tree:");
    dfs.printTree();
  }
}