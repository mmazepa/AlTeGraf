import org.jgrapht.*;
import org.jgrapht.graph.*;

public class GraphManager {

  public static void addVertices(DefaultUndirectedGraph<String, DefaultEdge> graph, int numOfVertices) {
    for (int i = 0; i < numOfVertices; i++) {
      String vertex = "v" + (i+1);
      graph.addVertex(vertex);
    }
  }

  public static void addEdges(DefaultUndirectedGraph<String, DefaultEdge> graph, String from, String ...to) {
    for (String vertex : to) {
      graph.addEdge(from, vertex);
    }
  }

  public static void neighboursList(DefaultUndirectedGraph<String, DefaultEdge> graph) {
    int n = graph.vertexSet().size();
    for (int i = 0; i < n; i++) {
      String sourceVertex = "v" + (i+1);
      System.out.print("   " + String.format("%3s", sourceVertex) + ":");
      Object edges[] = graph.edgesOf(sourceVertex).toArray();
      for (int j = 0; j < graph.degreeOf(sourceVertex); j++) {
        String edge = edges[j].toString();
        edge = edge.replace("(", "");
        edge = edge.replace(")", "");

        String[] vertices = edge.split(" : ");
        String targetVertex = new String();

        if (sourceVertex.equals(vertices[0]))
          targetVertex = vertices[1];
        else if (sourceVertex.equals(vertices[1]))
          targetVertex = vertices[0];

        System.out.print(String.format("%3s", targetVertex));
      }
      System.out.print("\n");
    }
  }
}
