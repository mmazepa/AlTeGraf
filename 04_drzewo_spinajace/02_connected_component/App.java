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

  public static void lookForConnectedComponents(Graph graph) {
    int n = graph.getVertices().size();
    int vertexSet[] = new int[n];

    for (int i = 0; i < n; i++)
      vertexSet[i] = graph.getVertices().get(i).getNumber();

    for (Edge edge : graph.getEdges()) {
      int v1 = edge.getVertex1().getNumber();
      int v2 = edge.getVertex2().getNumber();
      unionSets(vertexSet, n, v1, v2);
    }

    checkConnectivity(vertexSet);
  }

  public static void checkConnectivity(int[] vertexSet) {
    int componentsAmount = 0;
    int components[] = new int[vertexSet.length];

    for (int i = 0; i < vertexSet.length; i++)
      components[vertexSet[i]-1]++;

    for (int i = 0; i < components.length; i++)
      if (components[i] > 0) componentsAmount++;

    if (componentsAmount == 1)
      h.frameIt("Podany graf JEST spójny!", true);
    else
      h.frameIt("Podany graf NIE JEST spójny, składowych spójności: " + componentsAmount, true);
    h.breakLine();

    int counter = 1;

    for (int i = 0; i < vertexSet.length; i++) {
      if ((i+1) == findSet(vertexSet, i+1)) {
        System.out.print("   [Składowa nr " + (counter++) + "] : ");
        for (int j = 0; j < vertexSet.length; j++) {
          if ((i+1) == findSet(vertexSet, j+1)) {
            System.out.print(String.format(" %2d", (j+1)));
          }
        }
        h.breakLine();
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
    h.frameIt("Graf wejściowy", false);
    h.breakLine();

    System.out.println("   Liczba wierzchołków: " + graph.getVertices().size());
    h.breakLine();

    em.displayEdges(graph.getEdges());
    h.breakLine();

    lookForConnectedComponents(graph);
    h.breakLine();
  }
}
