import java.util.ArrayList;
import java.util.Collections;

public class VertexManager {

  public static void addVertex(Graph graph, Vertex vertex) {
    graph.getVertices().add(vertex);
  }

  public Vertex getVertexByNumber(Graph graph, int number) {
    for (Vertex vertex : graph.getVertices())
      if (vertex.getNumber() == number)
        return vertex;
    return null;
  }
}
