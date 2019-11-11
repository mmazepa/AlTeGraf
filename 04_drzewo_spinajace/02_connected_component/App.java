import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static int findSet(int[] vertexSet, int vertexNum) {
    return vertexSet[vertexNum-1];
  }

  public static void unionSets(int[] vertexSet, int n, int v1_num, int v2_num) {
    int v1_parent = findSet(vertexSet, v1_num);
    int v2_parent = findSet(vertexSet, v2_num);

    if (v1_parent != v2_parent) {
      for (int i = 0; i < n; i++) {
        if (vertexSet[i] == v2_parent) {
          vertexSet[i] = v1_parent;
        }
      }
    }
  }

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0) {
      h.frameIt("Plik: " + args[0], true);
      graph = fm.prepareGraph(args[0]);
    } else {
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym!");
    }
    h.breakLine();
    h.frameIt("Graf wejściowy", false);
    h.breakLine();

    em.displayEdges(graph.getEdges());
    h.breakLine();
  }
}
