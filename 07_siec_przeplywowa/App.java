import java.util.ArrayList;
import java.util.Stack;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static Boolean DFS(Vertex start, Vertex end, Stack<Vertex> stack, Boolean[] visited, Graph graph) {
    visited[start.getNumber()-1] = true;
    stack.push(start);

    if (start.getNumber() == end.getNumber())
      return true;

    for (Vertex neighbour : vm.getVertexNeighbours(graph, start)) {
      if (!visited[neighbour.getNumber()-1]) {
        if (DFS(neighbour, end, stack, visited, graph))
          return true;
      }
    }

    stack.pop();
    return false;
  }

  public static void findPath(Vertex start, Vertex end, Graph graph) {
    int n = graph.getVertices().size();

    Stack<Vertex> stack = new Stack<Vertex>();
    Boolean[] visited = new Boolean[n];

    for (int i = 0; i < n; i++)
      visited[i] = false;

    ArrayList<Vertex> path = new ArrayList<Vertex>();

    if (!DFS(start, end, stack, visited, graph)) {
      System.out.println("   BRAK DROGI!");
      h.breakLine();
      h.exitOnPurpose("Nie istnieje droga między startem a ujściem!");
    } else {
      System.out.println("   ISTNIEJE!!");
      while (!stack.empty()) {
        Vertex v = stack.pop();
        path.add(v);
      }
      h.displayPath("Przykładowa droga", path);
    }
  }

  public static int[][] prepareEmptyMatrix(int n) {
    int[][] matrix = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        matrix[i][j] = 0;
        matrix[j][i] = 0;
      }
    }
    return matrix;
  }

  public static int[][] prepareMatrix(Graph graph) {
    int n = graph.getVertices().size();
    int[][] matrix = new int[n][n];

    matrix = prepareEmptyMatrix(graph.getVertices().size());

    for (Edge edge : graph.getEdges()) {
      int v1_index = edge.getVertex1().getNumber()-1;
      int v2_index = edge.getVertex2().getNumber()-1;
      matrix[v1_index][v2_index] = edge.getWeight();
    }

    return matrix;
  }

  public static Boolean BFS(int[][] matrix, int s, int t, int parent[]) {
    int n = matrix.length;
    boolean visited[] = new boolean[n];
    for (int i = 0; i < n; ++i)
      visited[i] = false;

    ArrayList<Integer> queue = new ArrayList<Integer>();
    queue.add(s);
    visited[s] = true;
    parent[s] = -1;

    while (queue.size() != 0) {
      int u = queue.get(0);
      queue.remove(0);
      for (int v = 0; v < n; v++) {
        if (visited[v] == false && matrix[u][v] > 0) {
          queue.add(v);
          parent[v] = u;
          visited[v] = true;
        }
      }
    }
    return (visited[t] == true);
  }

  public static int getMaxFlow(int graph[][], int s, int t) {
    int u, v;
    int n = graph.length;
    int rGraph[][] = new int[n][n];

    for (u = 0; u < n; u++)
      for (v = 0; v < n; v++)
        rGraph[u][v] = graph[u][v];

    int parent[] = new int[n];
    int max_flow = 0;

    while (BFS(rGraph, s, t, parent)) {
      int path_flow = Integer.MAX_VALUE;
      for (v=t; v != s; v = parent[v]) {
        u = parent[v];
        path_flow = Math.min(path_flow, rGraph[u][v]);
      }

      for (v = t; v != s; v = parent[v]) {
        u = parent[v];
        rGraph[u][v] -= path_flow;
        rGraph[v][u] += path_flow;
      }
      max_flow += path_flow;
    }
    return max_flow;
  }

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0)
      graph = fm.prepareGraph(args[0]);
    else
      h.exitOnPurpose("Nie podano pliku z siecią wejściową!");

    h.frameIt("Plik: " + args[0], true);
    h.breakLine();

    h.frameIt("Sieć Wejściowa", false);
    h.breakLine();
    System.out.println("   Wierzchołków: " + String.format("%2d", graph.getVertices().size()));
    System.out.println("   Krawędzi:     " + String.format("%2d", graph.getEdges().size()));
    h.breakLine();
    em.displayEdges(graph.getEdges());
    h.breakLine();

    int n = graph.getVertices().size();

    Vertex start = graph.getVertices().get(0);
    Vertex end = graph.getVertices().get(n-1);

    System.out.print("   Start:  ");
    start.show();
    h.breakLine();
    System.out.print("   Ujście: ");
    end.show();
    h.breakLine();
    System.out.println("   Czy istnieje droga między startem a ujściem?");
    h.breakLine();

    findPath(start, end, graph);
    h.breakLine();

    int[][] matrix = new int[n][n];
    matrix = prepareMatrix(graph);
    h.frameIt("Macierz przepustowości", false);
    h.breakLine();
    h.displayMatrix(matrix);
    h.breakLine();

    int maxFlow = getMaxFlow(matrix, start.getNumber()-1, end.getNumber()-1);
    h.frameIt("Maksymalny przepływ: " + maxFlow, true);
    h.breakLine();
  }
}
