import java.util.ArrayList;
import java.util.Collections;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static void analyzeSchedule(Graph graph) {
    Edge first = graph.getEdges().get(0);
    first.setEarlyStart(0);
    first.setEarlyFinish(first.getWeight());

    // ...

    h.displaySchedule(graph);
  }

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0)
      graph = fm.prepareGraph(args[0]);
    else
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym!");

    h.frameIt("Plik: " + args[0], true);
    h.breakLine();

    h.frameIt("Graf Wejściowy", false);
    em.displayEdges(graph.getEdges());
    h.breakLine();

    // // sortowanie krawędzi podłóg zależności
    // for (int i = 0; i < graph.getEdges().size(); i++) {
    //   for (int j = 0; j < graph.getEdges().size(); j++) {
    //     if (i != j) {
    //       Edge e1 = graph.getEdges().get(i);
    //       Edge e2 = graph.getEdges().get(j);
    //
    //
    //       if (e1.getVertex1().getNumber() < e2.getVertex1().getNumber()) {
    //         Collections.swap(graph.getEdges(), i, j);
    //       } else if ((e1.getVertex1().getNumber() == e2.getVertex1().getNumber()) && (e1.getVertex2().getNumber() < e2.getVertex2().getNumber())) {
    //         Collections.swap(graph.getEdges(), i, j);
    //       }
    //     }
    //   }
    // }

    // h.frameIt("Graf Posortowany", false);
    // em.displayEdges(graph.getEdges());
    // h.breakLine();

    h.frameIt("Szeregowanie Sieci", false);
    analyzeSchedule(graph);
    h.breakLine();
  }
}
