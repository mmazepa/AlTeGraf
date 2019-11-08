import java.util.ArrayList;
import java.util.Collections;

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

    h.displayEdges(graph.getEdges());
    h.breakLine();

    ArrayList<Vertex> forest = graph.getVertices();
    ArrayList<Edge> edges = graph.getEdges();
    ArrayList<Edge> tree = new ArrayList<Edge>();

    for (int i = 0; i < edges.size(); i++) {
      for (int j = i+1; j < edges.size(); j++) {
          if (edges.get(i).getWeight() > edges.get(j).getWeight())
            Collections.swap(edges, i, j);
      }
    }

    while (!edges.isEmpty()) {
      Edge edge = edges.get(0);
      // if łączy dwa różne drzewa - połącz
      // else odrzuć
      tree.add(edge);
      edges.remove(edge);
    }

    h.displayEdges(tree);
    h.breakLine();
  }
}
