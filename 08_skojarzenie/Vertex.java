import java.util.ArrayList;

public class Vertex {
  private int number;
  private ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

  public Vertex() { }

  public Vertex(int number) {
    this.number = number;
  }

  public int getNumber() {
    return number;
  }
  public void setNumber(int number) {
    this.number = number;
  }

  public ArrayList<Vertex> getNeighbours() {
    return neighbours;
  }
  public void setNeighbours(ArrayList<Vertex> neighbours) {
    this.neighbours = neighbours;
  }

  public void show() {
    System.out.print("   v" + this.number + " :");
    this.neighbours.forEach(n -> System.out.print(" v" + n.getNumber()));
  }
}
