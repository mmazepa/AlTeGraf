import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class FileManager {

  public static EdgeManager em = new EdgeManager();

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
                for (int i = 0; i < matrixSize; i++) {
                    matrix.add(new ArrayList<Integer>());
                    for (int j = 0; j < matrixSize; j++) {
                        matrix.get(i).add(0);
                    }
                }
              } else {
                String[] parts = line.split("->");
                em.addEdge(matrix, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
              }
              counter++;
          }
      } catch (Exception e) {
          e.printStackTrace();
          System.out.println("Plik \"" + fileName + "\" nie został znaleziony.");
          System.exit(0);
      }
      return matrix;
  }
}
