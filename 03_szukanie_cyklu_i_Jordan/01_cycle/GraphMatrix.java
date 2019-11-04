import java.util.ArrayList;
import java.util.Iterator;

public class GraphMatrix {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static Helpers h = new Helpers();

  public static ArrayList<Integer> getAdjacentNodes(ArrayList<ArrayList<Integer>> matrix, int vertex) {
    ArrayList<Integer> adjacentNodes = new ArrayList<Integer>();
    for (int i = 0; i < matrix.size(); i++) {
      if (matrix.get(vertex).get(i) == 1) {
        adjacentNodes.add(i);
      }
    }
    return adjacentNodes;
  }

  public static Boolean isCyclicUtil(ArrayList<ArrayList<Integer>> matrix, int v, Boolean[] visited, int parent) {
    visited[v] = true;
    Integer i;
    Iterator<Integer> it = getAdjacentNodes(matrix, v).iterator();
    while (it.hasNext()) {
      i = it.next();
      if (!visited[i]) {
        if (isCyclicUtil(matrix, i, visited, v)) {
          return true;
        }
      } else if (i != parent) {
        return true;
      }
    }
    return false;
  }

  public static Boolean isCyclic(ArrayList<ArrayList<Integer>> matrix) {
    Boolean visited[] = new Boolean[matrix.size()];
    for (int i = 0; i < matrix.size(); i++) {
      visited[i] = false;
    }

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
    if (args.length > 0) {
      matrix = fm.prepareNewMatrix(args[0]);
    } else {
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym.");
    }

    System.out.print("\n");
    h.displayMatrix(matrix);

    if (!vm.allDegreesNotLessThan(matrix, 2)) {
      h.exitOnPurpose("Minimalny stopień mniejszy niż 2.");
    }

    for (int i = 0; i < matrix.size(); i++) {
      Boolean cycle = isCyclic(matrix);
      if (cycle) {
        System.out.println("   Podany graf zawiera co najmniej 1 cykl!");
        break;
      }
    }

    System.out.print("\n");
  }
}
