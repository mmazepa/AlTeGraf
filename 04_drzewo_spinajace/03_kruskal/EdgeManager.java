public class EdgeManager {

  public static void addEdge(Graph graph, Vertex v1, Vertex v2, int weight) {
    if (v1.getNumber() != v2.getNumber()) {
      graph.getEdges().add(new Edge(v1, v2, weight));
    }
  }
}
