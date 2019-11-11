import java.util.ArrayList;
import java.util.Stack;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  // public static void printStack(Stack<Vertex> stack) {
  //   System.out.print("   STOS:");
  //   stack.forEach(n -> System.out.print(" " + n.getNumber()));
  //   h.breakLine();
  // }

  public static void DFSstack(Vertex v, Boolean[] visited, Stack<Vertex> stack, Graph graph) {
    visited[v.getNumber()-1] = true;
    for (Vertex u : v.getNeighbours()) {
      if (!visited[u.getNumber()-1])
        DFSstack(u, visited, stack, graph);
    }
    stack.push(v);
    // printStack(stack); // <------ (!!!)
  }

  public static void DFSprint(Vertex v, Boolean[] visited, Graph graph) {
    visited[v.getNumber()-1] = true;
    System.out.print(String.format(" %2d", v.getNumber()));
    for (Vertex u : v.getNeighbours()) {
      if (!visited[u.getNumber()-1])
        DFSprint(u, visited, graph);
    }
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

  public static void theKosarajuAlgorithm(Graph graph) {
    int n = graph.getVertices().size();
    Boolean visited[] = new Boolean[n];

    for (int i = 0; i < visited.length; i++)
      visited[i] = false;

    Stack<Vertex> stack = new Stack<Vertex>();
    for (int v = 0; v < n; v++) {
      if (!visited[v])
        DFSstack(vm.getVertexByNumber(graph, (v+1)), visited, stack, graph);
    }

    graph = transposeGraph(graph);
    h.displayGraph(graph, "Transpozycja digrafu");

    for (int i = 0; i < visited.length; i++)
      visited[i] = false;

    h.frameIt("Silnie spójne składowe", false);
    h.breakLine();

    int cn = 0;

    while (!stack.empty()) {
      // printStack(stack); // <------ (!!!)
      Vertex v = stack.pop();
      if (!visited[v.getNumber()-1]) {
        cn = cn + 1;
        System.out.print("   * " + String.format("%2d", cn) + ": ");
        DFSprint(v, visited, graph);
        h.breakLine();
      }
    }
    // printStack(stack); // <------ (!!!)
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
