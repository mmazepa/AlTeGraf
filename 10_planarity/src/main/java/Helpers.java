public class Helpers {

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
}
