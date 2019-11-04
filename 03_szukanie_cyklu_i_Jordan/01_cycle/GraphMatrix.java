import java.util.ArrayList;
import java.util.Iterator;

public class GraphMatrix {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static ArrayList<Integer> path = new ArrayList<Integer>();

  public static ArrayList<Integer> getAdjacents(ArrayList<ArrayList<Integer>> matrix, int vertex) {
    ArrayList<Integer> adjacentNodes = new ArrayList<Integer>();
    for (int i = 0; i < matrix.size(); i++) {
      if (em.areConnected(matrix, vertex, i)) {
        adjacentNodes.add(i);
      }
    }
    return adjacentNodes;
  }

  public static Boolean isCyclicUtil(ArrayList<ArrayList<Integer>> matrix, int v, Boolean[] visited, int parent) {
    visited[v] = true;

    Integer i;
    Iterator<Integer> it = getAdjacents(matrix, v).iterator();
    while (it.hasNext()) {
      i = it.next();
      if (!visited[i]) {
        if (isCyclicUtil(matrix, i, visited, v)) {
          path.add(i);
          return true;
        }
      } else if (i != parent) {
        path.add(i);
        return true;
      }
    }
    return false;
  }

  public static Boolean isCyclic(ArrayList<ArrayList<Integer>> matrix) {
    Boolean visited[] = new Boolean[matrix.size()];
    for (int i = 0; i < matrix.size(); i++) visited[i] = false;

    for (int u = 0; u < matrix.size(); u++) {
      if (!visited[u]) {
        if (isCyclicUtil(matrix, u, visited, -1)) {
          return true;
        }
      }
    }
    return false;
  }

  public static void main(String args[]) {
    h.clearScreen();

    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    if (args.length > 0)
      matrix = fm.prepareNewMatrix(args[0]);
    else
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym.");

    System.out.print("\n");
    // System.out.println("   " + args[0]);
    h.frameIt(args[0]);

    System.out.print("\n");
    h.displayMatrix(matrix);

    if (!vm.allDegreesNotLessThan(matrix, 2))
      h.exitOnPurpose("Minimalny stopień mniejszy niż 2.");

    if (isCyclic(matrix))
      System.out.println("   Podany graf zawiera co najmniej 1 cykl!");

    if (em.areConnected(matrix, path.get(0), path.get(path.size()-1)))
      path.add(path.get(0));

    System.out.print("   Przykładowy cykl:");
    path.forEach(n -> System.out.print(" " + (n+1)));
    System.out.print("\n");

    System.out.println("   Długość cyklu: " + (path.size()-1));
    System.out.print("\n");
  }
}
