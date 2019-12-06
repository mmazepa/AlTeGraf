import java.util.ArrayList;

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

  public ArrayList<Vertex> getVertexNeighbours(Graph g, Vertex v) {
    ArrayList<Edge> edges = g.getEdges();
    ArrayList<Vertex> neighbours = new ArrayList<Vertex>();
    for (Edge edge : edges) {
      if (edge.getVertex1().getNumber() == v.getNumber())
        neighbours.add(edge.getVertex2());
    }
    return neighbours;
  }
}
