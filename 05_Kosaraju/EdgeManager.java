import java.util.ArrayList;
import java.util.Collections;

public class EdgeManager {

  public static VertexManager vm = new VertexManager();

  public static void setEdge(Graph graph, int v1, int v2) {
    if (v1 != v2) {
      Vertex vertex1 = vm.getVertexByNumber(graph, v1);
      Vertex vertex2 = vm.getVertexByNumber(graph, v2);

      vertex1.getNeighbours().add(vertex2);
      vm.sortNeigbours(vertex1);
    }
  }

  public static void removeEdge(Graph graph, int v1, int v2) {
    if (v1 != v2) {
      Vertex vertex1 = vm.getVertexByNumber(graph, v1);
      Vertex vertex2 = vm.getVertexByNumber(graph, v2);

      vertex1.getNeighbours().remove(vertex2);
    }
  }

  public static Boolean areConnected(Graph graph, int v1, int v2) {
    Vertex vertex1 = vm.getVertexByNumber(graph, v1);
    Vertex vertex2 = vm.getVertexByNumber(graph, v2);

    if (vertex1.getNeighbours().contains(vertex2))
      return true;
    return false;
  }
}
