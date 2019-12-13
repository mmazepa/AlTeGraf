import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

  public static void resetTable(int tab[]) {
    int n = tab.length;
    for (int i = 0; i < n; i++)
      tab[i] = 0;
  }

  public static int[][] prepareEmptyMatrix(int n) {
    int matrix[][] = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = i; j < n; j++) {
        matrix[i][j] = 0;
        matrix[j][i] = 0;
      }
    }
    return matrix;
  }

  public static Boolean checkBipartiteness(Graph graph, int color[]) {
    int n = graph.getVertices().size();
    ArrayList<Vertex> queue = new ArrayList<Vertex>();

    resetTable(color);

    for (int i = 0; i < n; i++) {
      if (color[i] == 0) {
        color[i] = 1;
        queue.add(vm.getVertexByNumber(graph, (i+1)));

        while (!queue.isEmpty()) {
          Vertex v = queue.get(0);
          queue.remove(0);
          for (Vertex u : v.getNeighbours()) {
            if (color[v.getNumber()-1] == color[u.getNumber()-1]) {
              return false;
            }
            if (color[u.getNumber()-1] == 0) {
              color[u.getNumber()-1] = -color[v.getNumber()-1];
              queue.add(u);
            }
          }
        }
      }
    }
    return true;
  }

  public static void prepareSets(int color[], ArrayList<Integer> first, ArrayList<Integer> second) {
    for (int i = 0; i < color.length; i++) {
      if (color[i] == 1) first.add((i+1));
      if (color[i] == -1) second.add((i+1));
    }

    h.displaySet("A", first);
    h.displaySet("B", second);
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

  public static int getMaxFlow(Graph g, int graph[][], int flowMatrix[][], int s, int t) {
    int u, v;
    int n = graph.length;
    int rGraph[][] = prepareEmptyMatrix(n);

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
        flowMatrix[u][v] += pathFlow;
      }
      maxFlow += pathFlow;

      h.frameIt("Sieć residualna nr " + (counter++), false);
      System.out.println("   Aktualny przepływ: " + maxFlow + "\n");
      h.displayMatrix(rGraph);
      h.breakLine();
    }

    return maxFlow;
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

    h.frameIt("Graf Wejściowy", false);
    h.breakLine();
    for (Vertex vertex : graph.getVertices()) {
      vertex.show();
      h.breakLine();
    }
    h.breakLine();

    int n = graph.getVertices().size();
    int color[] = new int[n];

    h.frameIt("Podział na dwa zbiory", false);
    h.breakLine();

    Boolean bipartiteness = checkBipartiteness(graph, color);

    h.showTab(color);
    h.breakLine();

    if (bipartiteness) {
      System.out.println("   GRAF JEST DWUDZIELNY!");
    } else {
      System.out.println("   GRAF NIE JEST DWUDZIELNY!");
      h.breakLine();
      h.exitOnPurpose("Graf nie jest grafem dwudzielnym!");
    }
    h.breakLine();

    ArrayList<Integer> first = new ArrayList<Integer>();
    ArrayList<Integer> second = new ArrayList<Integer>();

    prepareSets(color, first, second);
    h.breakLine();

    h.frameIt("Konwersja na sieć przepływową", false);
    h.breakLine();

    int matrix[][] = prepareEmptyMatrix(n+2);

    for (Vertex v : graph.getVertices()) {
      for (Vertex u : v.getNeighbours()) {
        if (first.contains(v.getNumber()) && second.contains(u.getNumber()))
          matrix[v.getNumber()-1][u.getNumber()-1] = 1;
      }
    }

    for (int v_num : first)
      matrix[n][v_num-1] = 1;

    for (int v_num : second)
      matrix[v_num-1][n+1] = 1;

    h.displayMatrix(matrix);
    h.breakLine();

    int flowMatrix[][] = prepareEmptyMatrix(n+2);

    int maxFlow = getMaxFlow(graph, matrix, flowMatrix, n, n+1);
    h.frameIt("Maksymalny przepływ = " + maxFlow, true);
    System.out.println("   a więc skojarzenie maksymalne");
    System.out.println("   powinno składać się z " + maxFlow + " krawędzi.");
    h.breakLine();

    h.frameIt("Skojarzenie maksymalne", false);
    h.breakLine();

    int counter = 1;

    for (int v_num : first) {
      for (int i = 0; i < n; i++) {
        if (flowMatrix[v_num-1][i] == matrix[v_num-1][i] && flowMatrix[v_num-1][i] == 1)
          System.out.println("   [" + (counter++) + "] : (v" + v_num + " -> v" + (i+1) + ")");
      }
    }
    h.breakLine();
  }
}
