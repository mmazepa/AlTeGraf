import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileManager {

  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static Graph prepareGraph(String fileName) {
    Graph graph = new Graph();
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    try {
      FileInputStream fileInputStream = new FileInputStream(fileName);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      int graphSize = 0;
      int counter = 0;

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (counter == 0) {
          graphSize = Integer.parseInt(line);
          for (int i = 0; i < graphSize; i++) {
            Vertex vertex = new Vertex();
            vertex.setNumber(i+1);
            vm.addVertex(graph, vertex);
          }
        } else {
          String[] parts = line.split("->");
          String[] parts2 = parts[1].split(" : ");
          em.addEdge(graph,
            new Vertex(Integer.parseInt(parts[0])),
            new Vertex(Integer.parseInt(parts2[0])),
            Integer.parseInt(parts2[1]));
        }
        counter++;
      }
    } catch (Exception e) {
      e.printStackTrace();
      h.exitOnPurpose("Plik \"" + fileName + "\": coś poszło nie tak...");
    }
    return graph;
  }
}
