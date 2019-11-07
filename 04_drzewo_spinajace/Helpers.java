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

  public static void sortNeigbours(Vertex vertex) {
    for (int i = 0; i < vertex.getNeighbours().size(); i++) {
      for (int j = 0; j < vertex.getNeighbours().size(); j++) {
        if (vertex.getNeighbours().get(i).getNumber() < vertex.getNeighbours().get(j).getNumber())
          Collections.swap(vertex.getNeighbours(), i, j);
      }
    }
  }
}
