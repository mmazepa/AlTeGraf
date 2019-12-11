import java.util.ArrayList;

public class VertexManager {

  public static void addVertex(Graph graph, Vertex vertex) {
    graph.getVertices().add(vertex);
  }

  public static Vertex getVertexByNumber(Graph graph, int v_num) {
    for (Vertex vertex : graph.getVertices())
      if (vertex.getNumber() == v_num)
        return vertex;
    return null;
  }

  public static void connectNeighbours(Graph graph, int v1, int v2) {
    Vertex vertex1 = getVertexByNumber(graph, v1);;
    Vertex vertex2 = getVertexByNumber(graph, v2);;

    if (vertex1.getNumber() != vertex2.getNumber()) {
      vertex1.getNeighbours().add(vertex2);
      vertex2.getNeighbours().add(vertex1);
    }
  }
}
