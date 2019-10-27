import java.util.ArrayList;
import java.util.Collections;

public class GraphSeries {

  public static ArrayList<Integer> prepareSeries(int... values) {
    ArrayList<Integer> series = new ArrayList<Integer>();
    for (int value : values)
      series.add(value);
    return series;
  }

  public static int sumArray(ArrayList<Integer> array) {
    int sum = 0;
    for (Integer num : array) sum = sum + num;
    return sum;
  }

  public static Boolean isGraphic(ArrayList<Integer> series) {
    ArrayList<Integer> tmpSeries = new ArrayList<Integer>(series);
    int sum = 0;

    for (int i = 0; i < tmpSeries.size(); i++) {
      sum = sumArray(tmpSeries);

      if (sum % 2 != 0) {
        System.out.println("   Suma elementów ciągu nie jest parzysta!");
        System.out.println("   ───────────────────────────────");
        return false;
      }

      if (sum == 0) {
        System.out.println("   " + tmpSeries);
        System.out.println("   ───────────────────────────────");
        return true;
      }

      Collections.sort(tmpSeries, Collections.reverseOrder());
      System.out.println("   " + tmpSeries);

      for (int j = 1; j < tmpSeries.get(0)+1; j++) {
        if (tmpSeries.get(j) > 0) {
          tmpSeries.set(j, tmpSeries.get(j)-1);
        } else {
          System.out.println("   ───────────────────────────────");
          return false;
        }
      }
      tmpSeries.set(0, 0);
    }
    return false;
  }

  public static int getVertexDegree(ArrayList<ArrayList<Integer>> matrix, int vertex) {
    int degree = 0;
    degree = sumArray(matrix.get(vertex));
    return degree;
  }

  public static void displayMatrix(ArrayList<ArrayList<Integer>> matrix) {
    System.out.print("      │");
    for (int i = 0; i < matrix.size(); i++) {
      String output = String.format("%2s", (i+1));
      System.out.print(output + " ");
    }
    System.out.print("\n");

    System.out.print("   ───┼");
    for (int i = 0; i < matrix.size(); i++) {
      System.out.print("───");
    }
    System.out.print("\n");

    for (int i = 0; i < matrix.size(); i++) {
      String output = String.format("%2s", (i+1));
      System.out.print("   " + output + " │");
      for (int j = 0; j < matrix.get(i).size(); j++) {
        output = String.format("%2s", matrix.get(i).get(j));
        System.out.print(output + " ");
      }
      System.out.print("   (" + getVertexDegree(matrix, i) + ")");
      System.out.print("\n");
    }
  }

  public static ArrayList<ArrayList<Integer>> prepareMatrix(int size) {
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < size; i++) {
      matrix.add(new ArrayList<Integer>());
      for (int j = 0; j < size; j++) {
        matrix.get(i).add(0);
      }
    }
    return matrix;
  }

  public static void returnGraph(ArrayList<Integer> series) {
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    matrix = prepareMatrix(series.size());
    Collections.sort(series, Collections.reverseOrder());

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = i; j < matrix.size(); j++) {
        if (sumArray(matrix.get(i)) < series.get(i)) {
          matrix.get(i).set(j, 1);
          matrix.get(j).set(i, 1);
        } else {
          break;
        }
      }
    }
    displayMatrix(matrix);
  }

  public static void checkSeries(ArrayList<Integer> series) {
    System.out.println("   ───────────────────────────────");
    System.out.println("   Czy podany ciąg jest graficzny?");
    System.out.println("   ───────────────────────────────");
    System.out.println("   " + series);
    System.out.println("   ───────────────────────────────");

    Boolean graphic = isGraphic(series);

    System.out.print(graphic ? "   TAK!" : "   NIE!");
    System.out.print("\n");

    if (graphic) {
      System.out.println("   Ciąg jest graficzny -> zwracam graf.");
      System.out.print("\n");
      returnGraph(series);
    } else {
      System.out.println("   Ciąg nie jest graficzny -> koniec.");
    }

    System.out.print("\n");
  }

  public static void main(String[] args) {
    ArrayList<ArrayList<Integer>> listOfSeries = new ArrayList<ArrayList<Integer>>();
    System.out.print("\n");

    listOfSeries.add(prepareSeries(1, 4, 2, 3, 1));
    listOfSeries.add(prepareSeries(1, 3, 4, 6, 7, 3, 1, 1));
    listOfSeries.add(prepareSeries(2, 3, 2, 3, 2));
    listOfSeries.add(prepareSeries(5, 3, 2, 1, 1, 2, 4, 4, 2, 2));
    listOfSeries.add(prepareSeries(0, 0, 0, 0, 0));

    int counter = 1;
    for (ArrayList<Integer> series : listOfSeries) {
      System.out.println("   ╔═══╗");
      System.out.println("   ║ " + (counter++) + " ║");
      System.out.println("   ╚═══╝");

      checkSeries(series);
    }
  }
}
