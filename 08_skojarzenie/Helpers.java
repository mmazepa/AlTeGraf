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

  public static void breakLine(int times) {
    for (int i = 0; i < times; i++)
      breakLine();
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

  public static void displayMatrix(int[][] matrix) {
    System.out.print("       │");
    for (int i = 0; i < matrix.length; i++)
      System.out.print(String.format("%3s", "v" + (i+1)) + " ");
    breakLine();

    System.out.print("   ────┼");
    for (int i = 0; i < matrix.length; i++)
      System.out.print("────");
    breakLine();

    for (int i = 0; i < matrix.length; i++) {
      System.out.print("   " + String.format("%3s", "v" + (i+1)) + " │");
      for (int j = 0; j < matrix.length; j++)
        System.out.print(String.format("%3s", matrix[i][j]) + " ");
      breakLine();
    }
  }

  public static void showTab(int tab[]) {
    int n = tab.length;
    System.out.print("   ");
    System.out.print(String.format("%3s", "v" + 1));
    for (int i = 1; i < n; i++)
      System.out.print(String.format(" │%3s", "v" + (i+1)));
    breakLine();

    System.out.print("   ");
    for (int i = 0; i < (n*5)-1; i++) {
      if ((i+1)%5 == 0)
        System.out.print("┼");
      else
        System.out.print("─");
    }
    breakLine();

    System.out.print("   ");
    System.out.print(String.format("%3d", tab[0]));
    for (int i = 1; i < n; i++)
      System.out.print(String.format(" │%3d", tab[i]));
    breakLine();
  }
}
