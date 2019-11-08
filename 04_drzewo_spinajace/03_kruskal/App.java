import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static Helpers h = new Helpers();

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0) {
      h.frameIt("Plik: " + args[0], true);
      graph = fm.prepareGraph(args[0]);
    } else {
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym!");
    }
    h.breakLine();

    h.displayEdges(graph);
    h.breakLine();
  }
}
