import java.util.ArrayList;
import java.util.Collections;

public class EdgeManager {

  public static Helpers h = new Helpers();

  public static void addEdge(Graph graph, Vertex v1, Vertex v2, int weight) {
    if (v1.getNumber() != v2.getNumber())
      graph.getEdges().add(new Edge(v1, v2, weight));
  }

  public static void displayEdges(ArrayList<Edge> edges) {
    for (Edge edge : edges) {
      edge.show();
      h.breakLine();
    }
  }

  public static void sortByWeights(ArrayList<Edge> edges) {
    for (int i = 0; i < edges.size(); i++) {
      for (int j = i+1; j < edges.size(); j++) {
          if (edges.get(i).getWeight() > edges.get(j).getWeight())
            Collections.swap(edges, i, j);
      }
    }
  }

  public static int sumWeights(ArrayList<Edge> edges) {
    int sum = 0;
    for (int i = 0; i < edges.size(); i++) {
      sum = sum + edges.get(i).getWeight();
    }
    return sum;
  }
}
