import java.util.ArrayList;

public class VertexManager {

  public static Helpers h = new Helpers();

  public static void addVertex(ArrayList<ArrayList<Integer>> matrix) {
    for (ArrayList<Integer> row : matrix)
      row.add(0);

    ArrayList<Integer> newRow = new ArrayList<Integer>();
    for (int i = 0; i < matrix.size()+1; i++) newRow.add(0);
    matrix.add(newRow);
  }

  public static int getVertexDegree(ArrayList<ArrayList<Integer>> matrix, int vertex) {
    int degree = 0;
    degree = h.sumArray(matrix.get(vertex));
    return degree;
  }
}
