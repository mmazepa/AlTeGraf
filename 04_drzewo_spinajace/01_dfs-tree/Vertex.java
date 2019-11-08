import java.util.ArrayList;

public class Vertex {
  private int number;
  private ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

  public Vertex() { }

  public Vertex(int number, ArrayList<Vertex> neighbours) {
    this.number = number;
    this.neighbours = neighbours;
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
    String num = String.format("%2d", number);
    System.out.print("   " + num + " :");
    neighbours.forEach(n -> System.out.print(String.format(" %2d", n.getNumber())));
    Helpers.breakLine();
  }
}
