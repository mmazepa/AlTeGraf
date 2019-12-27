import java.util.ArrayList;
import java.util.Stack;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

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

  public static void fillMatrix(Graph graph, int[][] matrix) {
    for (Vertex v1 : graph.getVertices()) {
      for (Vertex v2 : v1.getNeighbours()) {
        if (matrix[v1.getNumber()-1][v2.getNumber()-1] == 0) {
          matrix[v1.getNumber()-1][v2.getNumber()-1] = 1;
          matrix[v2.getNumber()-1][v1.getNumber()-1] = 1;
        }
      }
    }
  }

  public static ArrayList<Integer> getNeighbours(int v, int[][] matrix) {
    ArrayList<Integer> neighbours = new ArrayList<Integer>();
    for (int i = 0; i < matrix.length; i++) {
      if ((matrix[v][i] > 0) && (matrix[i][v] > 0))
        neighbours.add(i);
    }
    return neighbours;
  }

  public static void setEdgeValue(int v1, int v2, int[][] matrix, int value) {
    matrix[v1][v2] = value;
    matrix[v2][v1] = value;
  }

  public static void removeEdge(int v1, int v2, int[][] matrix) {
    setEdgeValue(v1, v2, matrix, 0);
  }

  public static void markBridge(int v1, int v2, int[][] matrix) {
    setEdgeValue(v1, v2, matrix, 2);
  }

  public static int DFSb(int v, int vf, int[][] matrix, int[] D, int cv) {
    int low = 0;
    int tmp = 0;

    D[v] = cv;
    low = cv;

    cv = cv + 1;

    for (int u : getNeighbours(v, matrix)) {
      if (u != vf) {
        if (D[u] < low) low = D[u];
        if (D[u] == 0)
          tmp = DFSb(u, v, matrix, D, cv);
        if (tmp < low) low = tmp;
      }
    }

    if ((vf > -1) && (low == D[v]))
      markBridge(vf, v, matrix);

    return low;
  }

  public static Stack<Integer> theFleuryAlgorithm(int v, int n, int[][] matrix) {
    int D[] = new int[n];
    Stack<Integer> stack = new Stack<Integer>();
    int cv;

    while (true) {
      stack.push(v);
      if (getNeighbours(v, matrix).size() == 0) return stack;
      int u = getNeighbours(v, matrix).get(0);

      for (int i = 0; i < D.length; i++) D[i] = 0;

      cv = 1;
      DFSb(v, -1, matrix, D, cv);
      while ((matrix[v][u] == 2) && (getNeighbours(v, matrix).size() > 1))
        u = getNeighbours(v, matrix).get(1);

      removeEdge(v, u, matrix);

      System.out.println("   EDGE = (v" + (v+1) + "->v" + (u+1) + ")");
      h.displayMatrix(matrix);
      h.breakLine();

      v = u;
    }
  }

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0)
      graph = fm.prepareGraph(args[0]);
    else
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym!");

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
    int[][] matrix = prepareEmptyMatrix(n);

    fillMatrix(graph, matrix);

    h.frameIt("Macierz Sąsiedztwa", false);
    h.breakLine();

    h.displayMatrix(matrix);
    h.breakLine();

    Stack<Integer> stack = theFleuryAlgorithm(0, n, matrix);

    String stackStr = "CYKL EULERA |";
    stackStr += (" v" + (stack.elementAt(0)+1));
    for (int i = 1; i < stack.size(); i++)
      stackStr += (" v" + (stack.elementAt(i)+1));

    h.frameIt(stackStr, true);
    h.breakLine();

    h.frameIt("Macierz po Fleury'm (powinna być pusta)", false);
    h.breakLine();

    h.displayMatrix(matrix);
    h.breakLine();
  }
}
