import java.util.ArrayList;

public class EdgeManager {

  public static Helpers h = new Helpers();

  public static Boolean isInsideMatrix(ArrayList<ArrayList<Integer>> matrix, int from, int to) {
    if (from > 0 && to > 0 && from <= matrix.size() && to <= matrix.size())
      return true;
    return false;
  }

  public static void addEdge(ArrayList<ArrayList<Integer>> matrix, int from, int to) {
    if (isInsideMatrix(matrix, from, to)) {
      from = from - 1;
      to = to - 1;

      if (from != to) {
        matrix.get(from).set(to, 1);
        matrix.get(to).set(from, 1);
      }
    }
    h.clearScreen();
  }

  public static void removeEdge(ArrayList<ArrayList<Integer>> matrix, int from, int to) {
    if (isInsideMatrix(matrix, from, to)) {
      from = from - 1;
      to = to - 1;

      if (from != to) {
        matrix.get(from).set(to, 0);
        matrix.get(to).set(from, 0);
      }
    }
    h.clearScreen();
  }
}
