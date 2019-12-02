public class Edge {

  private String label = new String();

  private Vertex vertex1 = new Vertex();
  private Vertex vertex2 = new Vertex();
  private int weight = 0;

  private int earlyStart = 0;
  private int lateStart = 0;
  private int earlyFinish = 0;
  private int lateFinish = 0;

  public Edge() { }

  public Edge(String label, Vertex vertex1, Vertex vertex2, int weight) {
    this.label = label;
    this.vertex1 = vertex1;
    this.vertex2 = vertex2;
    this.weight = weight;
  }

  public String getLabel() {
    return label;
  }
  public void setLabel(String label) {
    this.label = label;
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

  public int getWeight() {
    return weight;
  }
  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getEarlyStart() {
    return earlyStart;
  }
  public void setEarlyStart(int earlyStart) {
    this.earlyStart = earlyStart;
  }

  public int getLateStart() {
    return lateStart;
  }
  public void setLateStart(int lateStart) {
    this.lateStart = lateStart;
  }

  public int getEarlyFinish() {
    return earlyFinish;
  }
  public void setEarlyFinish(int earlyFinish) {
    this.earlyFinish = earlyFinish;
  }

  public int getLateFinish() {
    return lateFinish;
  }
  public void setLateFinish(int lateFinish) {
    this.lateFinish = lateFinish;
  }

  public void show() {
    // System.out.print("   " + this.label + " : ");
    System.out.print("   (" + vertex1.getNumber() + "->" + vertex2.getNumber() + ")");
    System.out.print(" : " + this.label + "|" + this.weight);
  }
}
