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
    int counter = 1;

    while (true) {
      String fileName = "input0" + (counter++) + ".txt";
      File file = fm.getFileFromResources(fileName);
      if (file == null) break;
      graph = fm.prepareGraph(file);

      h.frameIt(fileName, false);

      h.horizontalLine(35);
      gm.neighboursList(graph);
      h.breakLine();

      BoyerMyrvoldPlanarityInspector bmpi = new BoyerMyrvoldPlanarityInspector(graph);
      System.out.print("   Czy podany graf jest planarny?  ");

      Boolean planar = bmpi.isPlanar();
      System.out.print(planar ? "TAK" : "NIE");
      h.breakLine();

      if (!planar) {
        System.out.println("   PODGRAF KURATOWSKIEGO:");
        gm.neighboursList(bmpi.getKuratowskiSubdivision());
      }

      h.horizontalLine(35);
      h.breakLine();
    }
  }
}
