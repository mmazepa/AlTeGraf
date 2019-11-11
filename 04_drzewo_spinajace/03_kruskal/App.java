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

  public static ArrayList<Edge> theKruskalAlgorithm(Graph graph) {
    int n = graph.getVertices().size();
    int vertexSet[] = new int[n];
    ArrayList<Edge> tree = new ArrayList<Edge>();

    for (int i = 0; i < n; i++)
      vertexSet[i] = graph.getVertices().get(i).getNumber();

    for (int i = 0; i < n; i++) {
      Edge edge = graph.getEdges().get(i);
      int v1 = edge.getVertex1().getNumber();
      int v2 = edge.getVertex2().getNumber();

      if (findSet(vertexSet, v1) != findSet(vertexSet, v2)) {
        tree.add(edge);
        unionSets(vertexSet, n, v1, v2);
      }
    }

    return tree;
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
    h.frameIt("Graf wejściowy posortowany względem wag", false);
    h.breakLine();

    em.sortByWeights(graph.getEdges());
    em.displayEdges(graph.getEdges());

    h.breakLine();
    h.frameIt("Minimalne drzewo spinające", false);
    h.breakLine();

    ArrayList<Edge> tree = new ArrayList<Edge>();
    tree = theKruskalAlgorithm(graph);

    em.displayEdges(tree);
    h.breakLine();

    h.frameIt("Waga minimalnego drzewa spinającego: " + em.sumWeights(tree), true);
    h.breakLine();
  }
}
