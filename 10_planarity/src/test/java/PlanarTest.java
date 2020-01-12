import static org.junit.Assert.assertTrue;
import org.junit.Test;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.planar.*;

public class PlanarTest {

  public static GraphManager gm = new GraphManager();

  @Test
  public void planarTest() {
    gm.breakLine();

    gm.frameIt("Planarność Grafu", true);
    // gm.breakLine();

    DefaultUndirectedGraph<String, DefaultEdge> graph = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);

    gm.addVertices(graph, 7);

    gm.addEdges(graph, "v1", "v2", "v3");
    gm.addEdges(graph, "v3", "v2", "v7");

    gm.frameIt("Lista Sąsiedztwa", false);
    gm.breakLine();

    gm.neighboursList(graph);
    gm.breakLine();

    BoyerMyrvoldPlanarityInspector bmpi = new BoyerMyrvoldPlanarityInspector(graph);

    gm.horizontalLine(35);
    System.out.print("   Czy podany graf jest planarny?  ");
    System.out.print(bmpi.isPlanar() ? "TAK" : "NIE");
    gm.breakLine();
    gm.horizontalLine(35);

    gm.breakLine();
  }
}
