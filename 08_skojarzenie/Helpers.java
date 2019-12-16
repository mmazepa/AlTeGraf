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

  public static void displayMatrix(int[][] matrix, Boolean realVerticesOnly) {
    int s;
    int t;
    int n;

    if (!realVerticesOnly) {
      s = matrix.length-2;
      t = matrix.length-1;
      n = matrix.length;
    } else {
      s = -1;
      t = -1;
      n = matrix.length-2;
    }

    System.out.print("       │");
    for (int i = 0; i < n; i++) {
      if (i == s)
        System.out.print(String.format("%4s", "s "));
      else if (i == t)
        System.out.print(String.format("%4s", "t "));
      else
        System.out.print(String.format("%3s", "v" + (i+1)) + " ");
    }
    breakLine();

    System.out.print("   ────┼");
    for (int i = 0; i < n; i++)
      System.out.print("────");
    breakLine();

    for (int i = 0; i < n; i++) {
      if (i == s)
        System.out.print("   " + String.format("%3s", " s") + " │");
      else if (i == t)
        System.out.print("   " + String.format("%3s", " t") + " │");
      else
        System.out.print("   " + String.format("%3s", "v" + (i+1)) + " │");

      for (int j = 0; j < n; j++)
        System.out.print(String.format("%3s", matrix[i][j]) + " ");
      breakLine();
    }
  }

  public static void showTab(int tab[]) {
    int n = tab.length;

    System.out.print("   " + String.format("%3s", "v" + 1));
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

    System.out.print("   " + String.format("%3d", tab[0]));
    for (int i = 1; i < n; i++)
      System.out.print(String.format(" │%3d", tab[i]));
    breakLine();
  }

  public static void displaySet(String setName, ArrayList<Integer> vertices) {
    ArrayList<Integer> tmpVertices = new ArrayList<Integer>();
    tmpVertices.addAll(vertices);

    System.out.print("   " + setName + ": {");
    if (!vertices.isEmpty()) {
      System.out.print("v" + tmpVertices.get(0));
      tmpVertices.remove(0);
      tmpVertices.forEach(n -> System.out.print(", v" + n));
    }
    System.out.println("}");
  }

  public static void horizontalLine(int length) {
    String segment = "╌◇╌◆";
    int counter = 0;

    System.out.print("   ▸");
    while ((length-2) > 0) {
      System.out.print(segment.charAt(counter++ % segment.length()));
      length--;
    }
    System.out.print("◂");
    breakLine();
  }
}
