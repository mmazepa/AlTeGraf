import java.util.ArrayList;
import java.util.Collections;

public class GraphSeries {

  public static ArrayList<Integer> prepareSeries(ArrayList<Integer> series, int... values) {
    series.clear();
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
    for (int i = 0; i < series.size(); i++) {
      if (sumArray(series) <= 0) {
        System.out.println("   " + series);
        System.out.println("   ─────────────────────────────");
        return true;
      }

      Collections.sort(series, Collections.reverseOrder());
      System.out.println("   " + series);

      for (int j = 1; j < series.get(0)+1; j++) {
        if (series.get(j) > 0) {
          series.set(j, series.get(j)-1);
        } else {
          System.out.println("   ─────────────────────────────");
          return false;
        }
      }
      series.set(0, 0);
    }
    return false;
  }

  public static void checkSeries(ArrayList<Integer> series) {
    System.out.println("   Czy podany ciąg jest grafowy?");
    System.out.println("   ─────────────────────────────");
    System.out.println("   " + series);
    System.out.println("   ─────────────────────────────");

    System.out.print(isGraphic(series) ? "   TAK!" : "   NIE!");
    System.out.print("\n");
  }

  public static void main(String[] args) {
    ArrayList<Integer> series = new ArrayList<Integer>();

    System.out.print("\n");

    series = prepareSeries(series, 1, 4, 2, 3, 1);
    checkSeries(series);

    System.out.print("\n");

    series = prepareSeries(series, 2, 3, 2, 3, 2);
    checkSeries(series);

    System.out.print("\n");
  }
}
