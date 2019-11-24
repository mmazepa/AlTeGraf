import java.util.ArrayList;
import java.util.Stack;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static void DFSstack(Vertex v, Boolean[] visited, Stack<Vertex> stack, Graph graph, Boolean fillingStack) {
    v = vm.getVertexByNumber(graph, v.getNumber());
    visited[v.getNumber()-1] = true;

    for (Vertex u : v.getNeighbours()) {
      if (!visited[u.getNumber()-1])
        DFSstack(u, visited, stack, graph, fillingStack);
    }

    if (fillingStack)
      stack.push(v);
    else
      System.out.print(String.format(" %2d", v.getNumber()));
  }

  public static Graph transposeGraph(Graph graph) {
    Graph transpose = new Graph();
    for (Vertex v : graph.getVertices())
      transpose.getVertices().add(new Vertex(v.getNumber(), new ArrayList<Vertex>()));

    for (Vertex v1 : graph.getVertices()) {
      for (Vertex v2 : graph.getVertices()) {
        if (em.areConnected(graph, v1.getNumber(), v2.getNumber())) {
          em.setEdge(transpose, v2.getNumber(), v1.getNumber());
        }
      }
    }
    return transpose;
  }

  public static void resetVisited(Boolean[] visited) {
    for (int i = 0; i < visited.length; i++)
      visited[i] = false;
  }

  public static void theKosarajuAlgorithm(Graph graph) {
    Stack<Vertex> stack = new Stack<Vertex>();
    int n = graph.getVertices().size();
    Boolean visited[] = new Boolean[n];

    resetVisited(visited);

    DFSstack(vm.getVertexByNumber(graph, 1), visited, stack, graph, true);
    h.breakLine();

    graph = transposeGraph(graph);
    h.displayGraph(graph, "Transpozycja digrafu");

    h.frameIt("Silnie spójne składowe", false);
    h.breakLine();

    resetVisited(visited);
    int cn = 0;

    while (!stack.empty()) {
      Vertex vertex = stack.pop();

      if (!visited[vertex.getNumber()-1]) {
        cn = cn + 1;
        System.out.print("   *" + String.format("%2d", cn) + ":");
        DFSstack(vertex, visited, null, graph, false);
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
      h.exitOnPurpose("Nie podano pliku z digrafem wejściowym!");

    h.frameIt("Plik: " + args[0], true);
    h.breakLine();
    h.displayGraph(graph, "Digraf Wejściowy");

    theKosarajuAlgorithm(graph);
    h.breakLine();
  }
}
