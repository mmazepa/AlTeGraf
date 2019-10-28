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
      int val1 = matrix.get(from).get(to);
      int val2 = matrix.get(to).get(from);

      if (from != to) {
        matrix.get(from).set(to, val1+1);
        matrix.get(to).set(from, val2+1);
      } else {
        matrix.get(from).set(from, val1+2);
      }
    }
    h.clearScreen();
  }

  public static void removeEdge(ArrayList<ArrayList<Integer>> matrix, int from, int to) {
    if (isInsideMatrix(matrix, from, to)) {
      from = from - 1;
      to = to - 1;
      int val1 = matrix.get(from).get(to);
      int val2 = matrix.get(to).get(from);

      if (val1 != 0 && val2 != 0) {
        if (from != to) {
          matrix.get(from).set(to, val1-1);
          matrix.get(to).set(from, val2-1);
        } else {
          matrix.get(from).set(to, val1-2);
        }
      }
    }
    h.clearScreen();
  }
}
