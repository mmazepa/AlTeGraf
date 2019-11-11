public class Edge {

  private Vertex vertex1 = new Vertex();
  private Vertex vertex2 = new Vertex();

  public Edge() { }

  public Edge(Vertex vertex1, Vertex vertex2) {
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
  }

  public Vertex getVertex1() {
    return vertex1;
  }
  public void setVertex1(Vertex vertex1) {
    this.vertex1 = vertex1;
  }

  public Vertex getVertex2() {
    return vertex2;
  }
  public void setVertex2(Vertex vertex2) {
    this.vertex2 = vertex2;
  }

  public void show() {
    System.out.print("   (");
    vertex1.show();
    System.out.print(", ");
    vertex2.show();
    System.out.print(")");
  }
}
