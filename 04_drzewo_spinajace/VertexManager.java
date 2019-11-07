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

  public static void sortNeigbours(Vertex vertex) {
    for (int i = 0; i < vertex.getNeighbours().size(); i++) {
      for (int j = 0; j < vertex.getNeighbours().size(); j++) {
        if (vertex.getNeighbours().get(i).getNumber() < vertex.getNeighbours().get(j).getNumber())
          Collections.swap(vertex.getNeighbours(), i, j);
      }
    }
  }
}
