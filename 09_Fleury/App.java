import java.util.ArrayList;

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

    h.displayMatrix(matrix);
    h.breakLine();
  }
}
