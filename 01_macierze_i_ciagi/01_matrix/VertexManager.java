import java.util.ArrayList;
import java.util.Collections;

public class VertexManager {

  public static Helpers h = new Helpers();

  public static void addVertex(ArrayList<ArrayList<Integer>> matrix) {
    for (ArrayList<Integer> row : matrix) {
      row.add(0);
    }
    ArrayList<Integer> newRow = new ArrayList<Integer>();
    for (int i = 0; i < matrix.size()+1; i++) newRow.add(0);
    matrix.add(newRow);
    h.clearScreen();
  }

  public static void removeVertex(ArrayList<ArrayList<Integer>> matrix, int vertex) {
    vertex = vertex - 1;
    for (ArrayList<Integer> row : matrix) row.remove(vertex);
    matrix.remove(vertex);
    h.clearScreen();
  }

  public static int getVertexDegree(ArrayList<ArrayList<Integer>> matrix, int vertex) {
    int degree = 0;
    degree = h.sumArray(matrix.get(vertex));
    return degree;
  }

  public static int getMaxDegree(ArrayList<ArrayList<Integer>> matrix) {
    int max = 0;
    for (int i = 0; i < matrix.size(); i++) {
      if (h.sumArray(matrix.get(i)) > max) max = h.sumArray(matrix.get(i));
    }
    return max;
  }

  public static int getMinDegree(ArrayList<ArrayList<Integer>> matrix) {
    int min = 0;
    if (matrix.size() > 0) min = h.sumArray(matrix.get(0));
    if (matrix.size() > 1) {
      for (int i = 1; i < matrix.size(); i++) {
        if (h.sumArray(matrix.get(i)) < min) min = h.sumArray(matrix.get(i));
      }
    }
    return min;
  }

  public static void getMinAndMaxDegree(ArrayList<ArrayList<Integer>> matrix) {
    int min = getMinDegree(matrix);
    int max = getMaxDegree(matrix);

    System.out.println("   Min stopień grafu: " + min);
    System.out.println("   Max stopień grafu: " + max);
    System.out.print("\n");
  }

  public static void getEvenOrOdd(ArrayList<ArrayList<Integer>> matrix) {
    int even = 0;
    int odd = 0;

    for (ArrayList<Integer> row : matrix) {
      int degree = h.sumArray(row);
      if (degree%2 == 0) even++;
      else odd++;
    }

    System.out.println("   Wierzchołków parzystych:    " + even);
    System.out.println("   Wierzchołków nieparzystych: " + odd);
    System.out.print("\n");
  }

  public static void getDegreeSeries(ArrayList<ArrayList<Integer>> matrix) {
    ArrayList<Integer> series = new ArrayList<Integer>();
    for (ArrayList<Integer> row : matrix) {
      series.add(h.sumArray(row));
    }

    Collections.sort(series, Collections.reverseOrder());
    System.out.println("   Ciąg stopni wierzchołków:");
    System.out.println("      " + series);
    System.out.print("\n");
  }
}
