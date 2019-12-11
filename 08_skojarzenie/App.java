import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

  public static int color[];// = new int[n];
  // public static ArrayList<Vertex> queue = new ArrayList<Vertex>();

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

  public static Boolean checkBipartiteness(Graph graph) {
    int n = graph.getVertices().size();
    // int color[] = new int[n];
    color = new int[n];
    ArrayList<Vertex> queue = new ArrayList<Vertex>();
    // queue = new ArrayList<Vertex>();

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
              h.showTab(color);
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
    h.showTab(color);
    return true;
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

    if (checkBipartiteness(graph)) {
      h.breakLine();
      System.out.println("   GRAF JEST DWUDZIELNY!");
    } else {
      h.breakLine();
      System.out.println("   GRAF NIE JEST DWUDZIELNY!");
      h.breakLine();
      h.exitOnPurpose("Graf nie jest grafem dwudzielnym!");
    }
    h.breakLine();

    // robocze szukanie skojarzenia (!!!)

    int n = graph.getVertices().size();
    int matrix[][] = prepareEmptyMatrix(n+2);

    // ...
    int P[] = new int[n+2];
    int CFP[] = new int[n+2];
    int cp = 0;
    // ...

    int fmax = 0;

    for (int i = 0; i < n; i++) {
      if (color[i] != 0) {
        for (Vertex u : vm.getVertexByNumber(graph, (i+1)).getNeighbours()) {
          matrix[i][u.getNumber()-1] = 1;
        }
        continue;
      }
      matrix[i][n+1] = 1;
    }
    fmax = 0;

    int matrixF[][] = prepareEmptyMatrix(n+2);

    for (int i = 0; i < n+2; i++)
      P[i] = -1;
    P[n] = -2;
    CFP[n] = Integer.MAX_VALUE;

    ArrayList<Vertex> queue = new ArrayList<Vertex>();
    queue.add(new Vertex(n+1));

    while (!queue.isEmpty()) {
      Vertex v = queue.get(0);
      queue.remove(0);

      for (int u = 0; u < n+2; u++) {
        cp = matrix[v.getNumber()-1][u] - matrixF[v.getNumber()-1][u];

        if ((cp == 0) || (P[u] != -1)) continue;

        P[u] = v.getNumber()-1;
        CFP[u] = Math.min(CFP[v.getNumber()-1], cp);
      }
    }

    h.displayMatrix(matrix);
    h.breakLine();
  }
}
