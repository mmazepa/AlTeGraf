import java.util.ArrayList;
import java.util.Collections;

public class GraphMatrix {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static void main(String args[]) {
    h.clearScreen();
    System.out.print("\n");

    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    if (args.length > 0) {
      matrix = fm.prepareNewMatrix(args[0]);
    } else {
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym.");
    }

    System.out.print("\n");
    h.displayMatrix(matrix);
  }
}
