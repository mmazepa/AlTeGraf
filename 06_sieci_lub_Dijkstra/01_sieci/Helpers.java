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

  public static void displaySchedule(Graph graph) {
    System.out.println("    Label | ES | EF | LS | LF");
    System.out.println("   -------+----+----+----+----");

    for (Edge e : graph.getEdges()) {
      String label = String.format(" %5s", e.getLabel() + "|" + e.getWeight());
      String es = String.format("%2d", e.getEarlyStart());
      String ef = String.format("%2d", e.getEarlyFinish());
      String ls = String.format("%2d", e.getLateStart());
      String lf = String.format("%2d", e.getLateFinish());

      System.out.println("   " + label + " | " + es + " | " + ef + " | " + ls + " | " + lf);
    }
  }
}
