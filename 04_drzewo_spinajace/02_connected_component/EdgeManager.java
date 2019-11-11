import java.util.ArrayList;
import java.util.Collections;

public class EdgeManager {

  public static Helpers h = new Helpers();

  public static void addEdge(Graph graph, int v1, int v2) {
    Vertex vertex1 = new Vertex(v1);
    Vertex vertex2 = new Vertex(v2);

    if (vertex1.getNumber() != vertex2.getNumber())
      graph.getEdges().add(new Edge(vertex1, vertex2));
  }

  public static void displayEdges(ArrayList<Edge> edges) {
    int counter = 1;
    System.out.print("   ");
    for (Edge edge : edges) {
      edge.show();
      if (counter%5 == 0) {
        h.breakLine();
        System.out.print("   ");
      }
      counter++;
    }
    if (counter%5 != 1) h.breakLine();
  }
}
