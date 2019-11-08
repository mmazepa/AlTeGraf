public class EdgeManager {

  public static VertexManager vm = new VertexManager();

  public static void setEdge(Graph graph, Vertex v1, Vertex v2) {
    v1.getNeighbours().add(v2);
    v2.getNeighbours().add(v1);
  }

  public static void addEdge(Graph graph, Vertex v1, Vertex v2, int weight) {
    if (v1.getNumber() != v2.getNumber()) {
      graph.getEdges().add(new Edge(v1, v2, weight));
      setEdge(graph, v1, v2);
    }
  }
}
