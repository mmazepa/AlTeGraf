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
    int vSize = graph.getVertices().size();
    int eSize = graph.getEdges().size();
    int vertexSet[] = new int[vSize];
    ArrayList<Edge> tree = new ArrayList<Edge>();

    for (int i = 0; i < vSize; i++)
      vertexSet[i] = graph.getVertices().get(i).getNumber();

    for (int i = 0; i < eSize; i++) {
      Edge edge = graph.getEdges().get(i);
      int v1 = edge.getVertex1().getNumber();
      int v2 = edge.getVertex2().getNumber();

      if (findSet(vertexSet, v1) != findSet(vertexSet, v2)) {
        tree.add(edge);
        unionSets(vertexSet, vSize, v1, v2);
      }
    }

    return tree;
  }

  public static Boolean isConnected(Graph graph) {
    int vs = graph.getVertices().size();
    int es = graph.getEdges().size();
    int vertexSet[] = new int[vs];

    for (int i = 0; i < vs; i++)
      vertexSet[i] = graph.getVertices().get(i).getNumber();

    for (int i = 0; i < es; i++) {
      int v1 = graph.getEdges().get(i).getVertex1().getNumber();
      int v2 = graph.getEdges().get(i).getVertex2().getNumber();
      unionSets(vertexSet, vs, v1, v2);
    }

    int prev = findSet(vertexSet, 1);
    for (int i = 1; i < vs; i++) {
      int tmp = findSet(vertexSet, (i+1));
      if (tmp != prev) return false;
      prev = tmp;
    }

    return true;
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

    if (!isConnected(graph)) {
      h.breakLine();
      h.exitOnPurpose("Graf nie jest spójny!");
    }

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
