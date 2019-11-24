import java.util.ArrayList;

public class Graph {
  private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
  private ArrayList<Edge> edges = new ArrayList<Edge>();

  public Graph() { }

  public Graph(ArrayList<Vertex> vertices) {
    this.vertices = vertices;
  }

  public ArrayList<Vertex> getVertices() {
    return vertices;
  }
  public void setVertices(ArrayList<Vertex> vertices) {
    this.vertices = vertices;
  }

  public ArrayList<Edge> getEdges() {
    return edges;
  }
  public void setEdges(ArrayList<Edge> edges) {
    this.edges = edges;
  }
}
