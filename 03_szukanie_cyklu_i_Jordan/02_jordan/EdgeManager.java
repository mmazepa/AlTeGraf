import java.util.ArrayList;

public class EdgeManager {

  public static Helpers h = new Helpers();

  public static Boolean isInsideMatrix(ArrayList<ArrayList<Integer>> matrix, int from, int to) {
    if (from > 0 && to > 0 && from <= matrix.size() && to <= matrix.size())
      return true;
    return false;
  }

  public static void setEdge(ArrayList<ArrayList<Integer>> matrix, int from, int to, int value) {
    matrix.get(from).set(to, value);
    matrix.get(to).set(from, value);
  }

  public static void addEdge(ArrayList<ArrayList<Integer>> matrix, int from, int to) {
    if (isInsideMatrix(matrix, from, to)) {
      from--;
      to--;

      if (from != to)
        setEdge(matrix, from, to, 1);
    }
  }

  public static Boolean areConnected(ArrayList<ArrayList<Integer>> matrix, int v1, int v2) {
    if (matrix.get(v1).get(v2) == 1 && matrix.get(v2).get(v1) == 1) return true;
    return false;
  }
}
