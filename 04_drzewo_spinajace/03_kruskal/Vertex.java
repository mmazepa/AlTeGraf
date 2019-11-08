public class Vertex {
  private int number;

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

  public void show() {
    System.out.print(this.number);
  }
}
