import java.util.*;

public class UnweightedGraph<V> implements Graph<V> {
  protected List<V> vertices = new ArrayList<>(); // Store vertices
  protected List<List<Edge>> neighbors 
    = new ArrayList<>(); // Adjacency lists

  /** Construct an empty graph */
  public UnweightedGraph() {
  }

  /** Construct a graph from vertices and edges stored in arrays */
  public UnweightedGraph(V[] vertices, int[][] edges) {
    for (int i = 0; i < vertices.length; i++)
      addVertex(vertices[i]);

    createAdjacencyLists(edges, vertices.length);
  }

  /** Construct a graph from vertices and edges stored in List */
  public UnweightedGraph(List<V> vertices, List<Edge> edges) {
    for (int i = 0; i < vertices.size(); i++)
      addVertex(vertices.get(i));

    createAdjacencyLists(edges, vertices.size());
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) 
      addVertex((V)(Integer.valueOf(i))); // vertices is {0, 1, ...}

    createAdjacencyLists(edges, numberOfVertices);
  }

  /** Construct a graph from integer vertices 0, 1, and edge array */
  public UnweightedGraph(int[][] edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) 
      addVertex((V)(Integer.valueOf(i))); // vertices is {0, 1, ...}

    createAdjacencyLists(edges, numberOfVertices);
  }

  /** Create adjacency lists for each vertex */
  private void createAdjacencyLists(
      int[][] edges, int numberOfVertices) {
    for (int i = 0; i < edges.length; i++) {
      addEdge(edges[i][0], edges[i][1]);
    }
  }

  /** Create adjacency lists for each vertex */
  private void createAdjacencyLists(
      List<Edge> edges, int numberOfVertices) {
    for (Edge edge: edges) {
      addEdge(edge.u, edge.v);
    }
  }

  @Override /** Return the number of vertices in the graph */
  public int getSize() {
    return vertices.size();
  }

  @Override /** Return the vertices in the graph */
  public List<V> getVertices() {
    return vertices;
  }

  @Override /** Return the object for the specified vertex */
  public V getVertex(int index) {
    return vertices.get(index);
  }

  @Override /** Return the index for the specified vertex object */
  public int getIndex(V v) {
    return vertices.indexOf(v);
  }

  @Override /** Return the neighbors of the specified vertex */
  public List<Integer> getNeighbors(int index) {
    List<Integer> result = new ArrayList<>();
    for (Edge e: neighbors.get(index))
      result.add(e.v);

    return result;
  }

  @Override /** Return the degree for a specified vertex */
  public int getDegree(int v) {
    return neighbors.get(v).size();
  }

  @Override /** Print the edges */
  public void printEdges() {
    for (int u = 0; u < neighbors.size(); u++) {
      System.out.print(getVertex(u) + " (" + u + "): ");
      for (Edge e: neighbors.get(u)) {
        System.out.print("(" + getVertex(e.u) + ", " +
          getVertex(e.v) + ") ");
      }
      System.out.println();
    }
  }

  @Override /** Clear the graph */
  public void clear() {
    vertices.clear();
    neighbors.clear();
  }

  @Override /** Add a vertex to the graph */  
  public boolean addVertex(V vertex) {
    if (!vertices.contains(vertex)) {
      vertices.add(vertex);
      neighbors.add(new ArrayList<Edge>());
      return true;
    }
    else {
      return false;
    }
  }

  @Override /** Add an edge to the graph */  
  public boolean addEdge(Edge e) {
    if (e.u < 0 || e.u > getSize() - 1)
      throw new IllegalArgumentException("No such index: " + e.u);

    if (e.v < 0 || e.v > getSize() - 1)
      throw new IllegalArgumentException("No such index: " + e.v);

    if (!neighbors.get(e.u).contains(e)) {
      neighbors.get(e.u).add(e);
      return true;
    }
    else {
      return false;
    }
  }

  @Override /** Add an edge to the graph */  
  public boolean addEdge(int u, int v) {
    return addEdge(new Edge(u, v));
  }

  @Override /** Obtain a DFS tree starting from vertex u */
  public SearchTree dfs(int v) {
    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1;

    boolean[] isVisited = new boolean[vertices.size()];

    dfs(v, parent, searchOrder, isVisited);

    return new SearchTree(v, parent, searchOrder);
  }

  private void dfs(int v, int[] parent, List<Integer> searchOrder,
      boolean[] isVisited) {

    searchOrder.add(v);
    isVisited[v] = true;

    for (Edge e : neighbors.get(v)) {
      int w = e.v;
      if (!isVisited[w]) {
        parent[w] = v;
        dfs(w, parent, searchOrder, isVisited);
      }
    }
  }

  @Override /** Starting bfs search from vertex v */
  public SearchTree bfs(int v) {
    List<Integer> searchOrder = new ArrayList<>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1;

    java.util.LinkedList<Integer> queue =
      new java.util.LinkedList<>();
    boolean[] isVisited = new boolean[vertices.size()];
    queue.offer(v);
    isVisited[v] = true;

    while (!queue.isEmpty()) {
      int u = queue.poll();
      searchOrder.add(u);
      for (Edge e: neighbors.get(u)) {
        int w = e.v;
        if (!isVisited[w]) {
          queue.offer(w);
          parent[w] = u;
          isVisited[w] = true;
        }
      }
    }

    return new SearchTree(v, parent, searchOrder);
  }

  public class SearchTree {
    private int root;
    private int[] parent;
    private List<Integer> searchOrder;

    public SearchTree(int root, int[] parent, 
        List<Integer> searchOrder) {
      this.root = root;
      this.parent = parent;
      this.searchOrder = searchOrder;
    }

    public int getRoot() {
      return root;
    }

    public int getParent(int v) {
      return parent[v];
    }

    public List<Integer> getSearchOrder() {
      return searchOrder;
    }

    public int getNumberOfVerticesFound() {
      return searchOrder.size();
    }

    public List<V> getPath(int index) {
      ArrayList<V> path = new ArrayList<>();

      do {
        path.add(vertices.get(index));
        index = parent[index];
      }
      while (index != -1);

      return path;
    }

    public void printPath(int index) {
      List<V> path = getPath(index);
      System.out.print("A path from " + vertices.get(root) + " to " +
        vertices.get(index) + ": ");
      for (int i = path.size() - 1; i >= 0; i--)
        System.out.print(path.get(i) + " ");
    }

    public void printTree() {
      System.out.println("Root is: " + vertices.get(root));
      System.out.print("Edges: ");
      for (int i = 0; i < parent.length; i++) {
        if (parent[i] != -1) {
          System.out.print("(" + vertices.get(parent[i]) + ", " +
            vertices.get(i) + ") ");
        }
      }
      System.out.println();
    }
  }

  @Override
  public boolean remove(V v) {
    return true;
  }

  @Override
  public boolean remove(int u, int v) {
    return true;
  }
}
