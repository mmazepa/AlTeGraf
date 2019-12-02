import java.util.ArrayList;

public class EdgeManager {

  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

  public static void addEdge(Graph graph, int v1, int v2, int weight) {
    Vertex vertex1 = new Vertex(v1);
    Vertex vertex2 = new Vertex(v2);

    if (vertex1.getNumber() != vertex2.getNumber())
      graph.getEdges().add(new Edge(vertex1, vertex2, weight));
  }

  public static void displayEdges(ArrayList<Edge> edges) {
    for (Edge edge : edges) {
      edge.show();
      h.breakLine();
    }
  }

  public static int getWeightTwoVertices(Graph g, int v1, int v2) {
    for (Edge e : g.getEdges()) {
      int edge_v1 = e.getVertex1().getNumber();
      int edge_v2 = e.getVertex2().getNumber();

      if ((edge_v1 == v1 && edge_v2 == v2))
        return e.getWeight();
    }
    return Integer.MAX_VALUE;
  }
}
