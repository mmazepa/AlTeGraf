import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

  public static void resetTable(int tab[]) {
    int n = tab.length;
    for (int i = 0; i < n; i++)
      tab[i] = 0;
  }

  public static Boolean checkBipartiteness(Graph graph) {
    int n = graph.getVertices().size();
    int color[] = new int[n];
    ArrayList<Vertex> queue = new ArrayList<Vertex>();

    resetTable(color);

    for (int i = 0; i < n; i++) {
      if (color[i] == 0) {
        color[i] = 1;
        queue.add(vm.getVertexByNumber(graph, (i+1)));

        while (!queue.isEmpty()) {
          Vertex v = queue.get(0);
          queue.remove(0);
          for (Vertex u : v.getNeighbours()) {
            if (color[v.getNumber()-1] == color[u.getNumber()-1]) {
              h.showTab(color);
              return false;
            }
            if (color[u.getNumber()-1] == 0) {
              color[u.getNumber()-1] = -color[v.getNumber()-1];
              queue.add(u);
            }
          }
        }
      }
    }
    h.showTab(color);
    return true;
  }

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0)
      graph = fm.prepareGraph(args[0]);
    else
      h.exitOnPurpose("Nie podano pliku z siecią wejściową!");

    h.frameIt("Plik: " + args[0], true);
    h.breakLine();

    h.frameIt("Graf Wejściowy", false);
    h.breakLine();
    for (Vertex vertex : graph.getVertices()) {
      vertex.show();
      h.breakLine();
    }
    h.breakLine();

    if (checkBipartiteness(graph)) {
      h.breakLine();
      System.out.println("   GRAF JEST DWUDZIELNY!");
    } else {
      h.breakLine();
      System.out.println("   GRAF NIE JEST DWUDZIELNY!");
      h.breakLine();
      h.exitOnPurpose("Graf nie jest grafem dwudzielnym!");
    }
    h.breakLine();
  }
}
