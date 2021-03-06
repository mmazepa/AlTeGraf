import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileManager {

  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static ArrayList<ArrayList<Integer>> prepareNewMatrix(String fileName) {
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    try {
      FileInputStream fileInputStream = new FileInputStream(fileName);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

      int matrixSize = 0;
      int counter = 0;

      String line;
      while ((line = bufferedReader.readLine()) != null) {
        if (counter == 0) {
          matrixSize = Integer.parseInt(line);
          for (int i = 0; i < matrixSize; i++)
            vm.addVertex(matrix);
        } else {
          String[] parts = line.split("->");
          em.addEdge(matrix, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        }
        counter++;
      }
    } catch (Exception e) {
      // e.printStackTrace();
      h.exitOnPurpose("Plik \"" + fileName + "\": coś poszło nie tak...");
    }
    return matrix;
  }
}
