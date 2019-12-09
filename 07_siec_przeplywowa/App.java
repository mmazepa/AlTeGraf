import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

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
    int[][] matrix = prepareEmptyMatrix(n);

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
        if (!visited[v] && matrix[u][v] > 0) {
          queue.add(v);
          parent[v] = u;
          visited[v] = true;
        }
      }
    }
    return (visited[t] == true);
  }

  public static int getMaxFlow(Graph g, int graph[][], int s, int t) {
    int u, v;
    int n = graph.length;
    int rGraph[][] = new int[n][n];

    for (u = 0; u < n; u++)
      for (v = 0; v < n; v++)
        rGraph[u][v] = graph[u][v];

    int parent[] = new int[n];
    int maxFlow = 0;

    int counter = 1;

    while (BFS(rGraph, s, t, parent)) {
      int pathFlow = Integer.MAX_VALUE;
      ArrayList<Vertex> path = new ArrayList<Vertex>();

      h.horizontalLine(5+(n*4));
      h.breakLine();
      path.add(new Vertex(t+1));
      for (v = t; v != s; v = parent[v]) {
        u = parent[v];
        path.add(new Vertex(u+1));
        pathFlow = Math.min(pathFlow, rGraph[u][v]);
      }
      h.displayPath("Ścieżka powiększająca", path);

      for (v = t; v != s; v = parent[v]) {
        u = parent[v];
        rGraph[u][v] -= pathFlow;
        rGraph[v][u] += pathFlow;
      }
      maxFlow += pathFlow;

      h.frameIt("Sieć residualna nr " + (counter++), false);
      System.out.println("   Aktualny przepływ: " + maxFlow + "\n");
      h.displayMatrix(rGraph);
      h.breakLine();
    }

    h.frameIt("Minimalny przekrój", false);
    getMinimalSection(g, rGraph, (s+1), (t+1));
    h.breakLine();

    return maxFlow;
  }

  public static ArrayList<ArrayList<Integer>> divideVertices(int[][] rGraph, int start, int end) {
    int n = rGraph.length;
    ArrayList<Integer> s = new ArrayList<Integer>();
    ArrayList<Integer> t = new ArrayList<Integer>();
    int limit = 0;

    s.add(start);
    while (limit != s.size()) {
      limit = s.size();
      for (int i = 0; i < limit; i++) {
        for (int j = 0; j < n; j++) {
          if (rGraph[i][j] > 0 && !s.contains(j+1))
            s.add(j+1);
        }
      }
    }

    if (s.contains(end))
      s.remove(new Integer(end));

    for (int i = 0; i < n; i++)
      t.add((i+1));

    for (int i = 0; i < s.size(); i++)
      t.remove(new Integer(s.get(i)));

    h.displaySet("X", 3, s);
    h.displaySet("V/X", 1, t);

    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    result.add(s);
    result.add(t);

    return result;
  }

  public static void getMinimalSection(Graph graph, int[][] rGraph, int start, int end) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    result = divideVertices(rGraph, start, end);

    ArrayList<Integer> s = result.get(0);
    ArrayList<Integer> t = result.get(1);

    ArrayList<Edge> minimalSection = new ArrayList<Edge>();

    for (Edge edge : graph.getEdges()) {
      for (int s_num : s) {
        for (int t_num : t) {
          if (edge.getVertex1().getNumber() == s_num && edge.getVertex2().getNumber() == t_num) {
            minimalSection.add(edge);
          }
        }
      }
    }

    h.breakLine();
    System.out.println("   Zbiór łuków:\n");
    em.displayEdges(minimalSection);
    h.breakLine();

    int capacity = h.sumWeights(minimalSection);
    System.out.println("   Przepustowość przekroju: " + capacity);
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
    h.breakLine(2);
    h.frameIt("Czy istnieje droga między startem a ujściem?", false);
    h.breakLine();

    int[][] matrix = new int[n][n];
    matrix = prepareMatrix(graph);

    if (BFS(matrix, start.getNumber()-1, end.getNumber()-1, new int[n])) {
      System.out.println("   ISTNIEJE!\n");
    } else {
      System.out.println("   BRAK DROGI!\n");
      h.exitOnPurpose("Brak drogi między startem a ujściem.");
    }

    h.frameIt("Macierz przepustowości", false);
    System.out.println("   Aktualny przepływ: " + 0 + "\n");
    h.displayMatrix(matrix);
    h.breakLine();

    int maxFlow = getMaxFlow(graph, matrix, start.getNumber()-1, end.getNumber()-1);
    h.frameIt("Maksymalny przepływ: " + maxFlow, true);
    h.breakLine();
  }
}
