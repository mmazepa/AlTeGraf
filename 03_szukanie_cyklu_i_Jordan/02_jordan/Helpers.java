import java.util.ArrayList;
import java.util.Scanner;

public class Helpers {

  public static VertexManager vm = new VertexManager();

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void log(String type, String text) {
    System.out.println("[" + type + "]: " + text);
  }

  public static void exitOnPurpose(String text) {
    log("exit", text);
    System.exit(0);
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
      System.out.print("   (" + vm.getVertexDegree(matrix, i) + ")");
      System.out.print("\n");
    }
    System.out.print("\n");
  }

  public static void displayEccentricities(ArrayList<Integer> eccentricities) {
    System.out.print("    index │");
    for (int i = 0; i < eccentricities.size(); i++) {
      String output = String.format("%2s", (i+1));
      System.out.print(output + " ");
    }
    System.out.print("\n");

    System.out.print("   ───────┼");
    for (int i = 0; i < eccentricities.size(); i++) {
      System.out.print("───");
    }
    System.out.print("\n");

    System.out.print("    ecce  │");
    for (int i = 0; i < eccentricities.size(); i++) {
      String output = String.format("%2s", eccentricities.get(i));
      System.out.print(output + " ");
    }
    System.out.print("\n\n");
  }

  public static void displayAnswer(ArrayList<Integer> index, ArrayList<Integer> dist) {
    String indexString = new String();
    String distString = new String();

    if (index.size() == 1 && dist.size() == 1) {
      indexString = String.format("%2d", (index.get(0)+1));
    } else if (index.size() == 2 && dist.size() == 2) {
      indexString = String.format("%2d %2d", (index.get(0)+1), (index.get(1)+1));
    }

    distString = String.format("%2d", dist.get(0));

    System.out.println("   Centrum drzewa to: " + indexString);
    System.out.println("   Najdalszy dystans: " + distString);
  }

  public static int getMax(int[] array) {
    int max = 0;
    for (int i = 0; i < array.length; i++) {
      if (max < array[i]) {
        max = array[i];
      }
    }
    return max;
  }

  public static int sumArray(ArrayList<Integer> array) {
    int sum = 0;
    for (Integer num : array) sum = sum + num;
    return sum;
  }
}
