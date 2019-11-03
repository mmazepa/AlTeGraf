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

  public static int sumArray(ArrayList<Integer> array) {
    int sum = 0;
    for (Integer num : array) sum = sum + num;
    return sum;
  }
}
