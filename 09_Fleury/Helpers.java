import java.util.ArrayList;
import java.util.Collections;

public class Helpers {

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

  public static void breakLine() {
    System.out.print("\n");
  }

  public static void frameIt(String text, Boolean bold) {
    System.out.print(bold ? "   ╔═" : "   ╭─");
    for (int i = 0; i < text.length(); i++) System.out.print(bold ? "═" : "─");
    System.out.print(bold ? "═╗\n" : "─╮\n");
    System.out.print(bold ? "   ║ " + text + " ║\n" : "   │ " + text + " │\n");
    System.out.print(bold ? "   ╚═" : "   ╰─");
    for (int i = 0; i < text.length(); i++) System.out.print(bold ? "═" : "─");
    System.out.print(bold ? "═╝\n" : "─╯\n");
  }

  public static void displayPath(String pathName, ArrayList<Vertex> path) {
    System.out.println("   " + pathName + ":");
    Collections.reverse(path);

    System.out.print("      ");
    System.out.print("s");
    for (int i = 1; i < path.size()-1; i++) {
      System.out.print(" -> ");
      System.out.print("v" + path.get(i).getNumber());
    }
    System.out.print(" -> ");
    System.out.print("t");
    breakLine();
  }

  public static void displayMatrix(int[][] matrix) {
    int n = matrix.length;

    System.out.print("       │");
    for (int i = 0; i < n; i++)
      System.out.print(String.format("%3s", "v" + (i+1)) + " ");
    breakLine();

    System.out.print("   ────┼");
    for (int i = 0; i < n; i++)
      System.out.print("────");
    breakLine();

    for (int i = 0; i < n; i++) {
      System.out.print("   " + String.format("%3s", "v" + (i+1)) + " │");
      for (int j = 0; j < n; j++)
        System.out.print(String.format("%3s", matrix[i][j]) + " ");
      breakLine();
    }
  }
}
