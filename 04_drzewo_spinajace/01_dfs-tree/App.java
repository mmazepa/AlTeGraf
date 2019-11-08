import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static void DFSTree(Vertex v, Graph graph, Graph tree, Boolean[] visited) {
    visited[v.getNumber()-1] = true;
    for (Vertex u : v.getNeighbours()) {
      if (!visited[u.getNumber()-1]) {
        em.setEdge(tree, v.getNumber(), u.getNumber());
        DFSTree(u, graph, tree, visited);
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
    h.displayGraph(graph, "Graf Wejściowy");

    int n = graph.getVertices().size();
    Graph tree = new Graph();
    Boolean[] visited = new Boolean[n];

    for (int i = 0; i < n; i++) {
      Vertex vertex = new Vertex();
      vertex.setNumber(i+1);
      vm.addVertex(tree, vertex);
      visited[i] = false;
    }

    DFSTree(graph.getVertices().get(0), graph, tree, visited);
    h.displayGraph(tree, "Drzewo Spinające DFS");
  }
}
