import org.junit.Test;
import java.io.File;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.planar.*;

public class PlanarTest {

  public static GraphManager gm = new GraphManager();
  public static FileManager fm = new FileManager();
  public static Helpers h = new Helpers();

  @Test
  public void planarTest() {
    h.breakLine();

    h.frameIt("Pakiet: JGraphT", true);
    h.breakLine();

    DefaultUndirectedGraph<String, DefaultEdge> graph = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);
    File file = fm.getFileFromResources("input01.txt");
    graph = fm.prepareGraph(file);

    gm.neighboursList(graph);
    h.breakLine();

    BoyerMyrvoldPlanarityInspector bmpi = new BoyerMyrvoldPlanarityInspector(graph);

    h.horizontalLine(35);
    System.out.print("   Czy podany graf jest planarny?  ");
    System.out.print(bmpi.isPlanar() ? "TAK" : "NIE");
    h.breakLine();
    h.horizontalLine(35);

    h.breakLine();
  }
}
