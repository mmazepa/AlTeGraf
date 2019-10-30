import java.util.ArrayList;

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

    ArrayList<String> cycle = new ArrayList<String>();
    Boolean[] visited = new Boolean[matrix.size()];

    for (int i = 0; i < visited.length; i++) visited[i] = false;

    for (int i = 1; i < matrix.size()+1; i++) {
      for (int j = 1; j < matrix.size()+1; j++) {
        if (em.areConnected(matrix, i, j) && !visited[i-1]) {
          visited[i-1] = true;
          cycle.add(i + "->" + j);
          em.removeEdge(matrix, i, j);
          i = j;
          j = 0;
        }
      }
    }

    System.out.println("   Cykl: " + cycle);
    System.out.print("\n");
  }
}
