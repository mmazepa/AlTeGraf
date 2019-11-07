import java.util.ArrayList;
import java.util.Collections;

public class EdgeManager {

  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

  public static void setEdge(Graph graph, int v1, int v2) {
    if (v1 != v2) {
      Vertex vertex1 = vm.getVertexByNumber(graph, v1);
      Vertex vertex2 = vm.getVertexByNumber(graph, v2);

      vertex1.getNeighbours().add(vertex2);
      vertex2.getNeighbours().add(vertex1);

      h.sortNeigbours(vertex1);
      h.sortNeigbours(vertex2);
    }
  }

  public static Boolean areConnected(Graph graph, int v1, int v2) {
    Vertex vertex1 = vm.getVertexByNumber(graph, v1);
    Vertex vertex2 = vm.getVertexByNumber(graph, v2);

    if (vertex1.getNeighbours().contains(vertex2) && vertex2.getNeighbours().contains(vertex1))
      return true;
    return false;
  }
}
