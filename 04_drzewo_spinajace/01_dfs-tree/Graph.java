import java.util.ArrayList;

public class Graph {
  private ArrayList<Vertex> vertices = new ArrayList<Vertex>();

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

  public void show() {
    vertices.forEach(v -> v.show());
  }
}
