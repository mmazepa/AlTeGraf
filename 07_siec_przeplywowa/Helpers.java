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
    path.get(0).show();
    for (int i = 1; i < path.size(); i++) {
      System.out.print(" -> ");
      path.get(i).show();
    }
    breakLine();
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

  public static void horizontalLine(int length) {
    String segment = "╌◇╌◆";
    int counter = 0;
    int segLen = segment.length();

    System.out.print("   ▸");
    while ((length-2) > 0) {
      System.out.print(segment.charAt(counter % segLen));
      length--;
      counter++;
    }
    System.out.print("◂");
    breakLine();
  }

  public static String setToString(ArrayList<Edge> set) {
    String setString = "{";
    for (Edge e : set)
      setString += "(" + e.getVertex1().getNumber() + "," + e.getVertex2().getNumber() + "),";
    if (setString.length() > 1)
      setString = setString.substring(0, setString.length()-1);
    setString += "}";
    return setString;
  }

  public static int sumWeights(ArrayList<Edge> edges) {
    int sum = 0;
    for (Edge edge : edges)
      sum += edge.getWeight();
    return sum;
  }
}
