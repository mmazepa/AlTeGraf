import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.planar.*;

public class FileManager {

  public static GraphManager gm = new GraphManager();
  public static Helpers h = new Helpers();

  public File getFileFromResources(String fileName) throws IllegalArgumentException {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(fileName);
    if (resource == null)
      throw new IllegalArgumentException("Nie znaleziono pliku!");
    else
      return new File(resource.getFile());
  }

  public static DefaultUndirectedGraph<String, DefaultEdge> prepareGraph(File file) {
    DefaultUndirectedGraph<String, DefaultEdge> graph = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);

    try {
      int graphSize = 0;
      int counter = 0;

      FileReader reader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(reader);

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (counter == 0) {
          graphSize = Integer.parseInt(line);
          gm.addVertices(graph, graphSize);
        } else {
          String[] parts = line.split("->");
          gm.addEdges(graph, "v" + parts[0], "v" + parts[1]);
        }
        counter++;
      }
    } catch (IOException e) {
      // e.printStackTrace();
      h.exitOnPurpose("Plik: coś poszło nie tak...");
    }

    return graph;
  }
}
