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

      Collections.sort(tmpSeries, Collections.reverseOrder());
      System.out.println("   " + tmpSeries);

      if (tmpSeries.get(0) >= tmpSeries.size()) {
        System.out.println("   Co najmniej jedna z wartości jest większa lub równa długości ciągu!");
        System.out.println("   ───────────────────────────────");
        return false;
      }

      if (tmpSeries.get(tmpSeries.size()-1) < 0) {
        System.out.println("   Co najmniej jedna z wartości jest liczbą ujemną!");
        System.out.println("   ───────────────────────────────");
        return false;
      }

      if (sum % 2 != 0) {
        System.out.println("   Suma elementów ciągu nie jest parzysta!");
        System.out.println("   ───────────────────────────────");
        return false;
      }

      if (sum == 0) {
        System.out.println("   ───────────────────────────────");
        return true;
      }

      for (int j = 1; j < tmpSeries.get(0)+1; j++) {
        if (tmpSeries.get(j) > 0) {
          tmpSeries.set(j, tmpSeries.get(j)-1);
        } else {
          System.out.println("   Kolejny krok wytworzy liczbę ujemną!");
          System.out.println("   ───────────────────────────────");
          return false;
        }
      }
      tmpSeries.remove(0);
      i--;
    }
    return false;
  }

  public static void checkSeries(ArrayList<Integer> series) {
    System.out.println("   ───────────────────────────────");
    System.out.println("   Czy podany ciąg jest graficzny?");
    System.out.println("   ───────────────────────────────");
    System.out.println("   " + series);
    System.out.println("   ───────────────────────────────");

    Boolean graphic = isGraphic(series);

    System.out.print(graphic ? "   TAK!" : "   NIE!");
    System.out.print("\n\n");
  }

  public static void main(String[] args) {
    ArrayList<ArrayList<Integer>> listOfSeries = new ArrayList<ArrayList<Integer>>();
    System.out.print("\n");

    listOfSeries.add(prepareSeries(1, 4, 2, 3, 1));
    listOfSeries.add(prepareSeries(1, 3, 4, 6, 7, 3, 1, 1));
    listOfSeries.add(prepareSeries(2, 3, 2, 3, 2));
    listOfSeries.add(prepareSeries(5, 3, 2, 1, 1, 2, 4, 4, 2, 2));
    listOfSeries.add(prepareSeries(0, 0, 0, 0, 0));
    listOfSeries.add(prepareSeries(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10));
    listOfSeries.add(prepareSeries(-1, 1, 1, 0, 1, 0));
    listOfSeries.add(prepareSeries(10, 3, 1, 4, 0));
    listOfSeries.add(prepareSeries(4, 4, 4));

    int counter = 1;
    for (ArrayList<Integer> series : listOfSeries) {
      System.out.println("   ╔════╗");
      System.out.println("   ║ " + (String.format("%2d", counter++)) + " ║");
      System.out.println("   ╚════╝");

      checkSeries(series);
    }
  }
}
