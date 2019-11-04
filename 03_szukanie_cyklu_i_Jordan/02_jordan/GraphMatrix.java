import java.util.ArrayList;
import java.util.Iterator;

public class GraphMatrix {

  public static FileManager fm = new FileManager();
  public static Helpers h = new Helpers();

  public static ArrayList<Integer> getAdjacents(ArrayList<ArrayList<Integer>> matrix, int vertex) {
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
    Iterator<Integer> it = getAdjacents(matrix, v).iterator();
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

  public static int minDistance(int matrixSize, int[] dist, Boolean sptSet[]) {
    int min = Integer.MAX_VALUE;
    int min_index = -1;

    for (int v = 0; v < matrixSize; v++)  {
      if (sptSet[v] == false && dist[v] <= min) {
        min = dist[v];
        min_index = v;
      }
    }
    return min_index;
  }

  public static int findMaxDistance(ArrayList<ArrayList<Integer>> matrix, int src) {
    int dist[] = new int[matrix.size()];
    Boolean sptSet[] = new Boolean[matrix.size()];

    for (int i = 0; i < matrix.size(); i++) {
      dist[i] = Integer.MAX_VALUE;
      sptSet[i] = false;
    }

    dist[src] = 0;
    for (int count = 0; count < matrix.size() - 1; count++) {
      int u = minDistance(matrix.size(), dist, sptSet);
      sptSet[u] = true;
      for (int v = 0; v < matrix.size(); v++) {
        if (!sptSet[v] && matrix.get(u).get(v) != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix.get(u).get(v) < dist[v]) {
          dist[v] = dist[u] + matrix.get(u).get(v);
        }
      }
    }
    return h.getMax(dist);
  }

  public static void findCenterJordan(ArrayList<ArrayList<Integer>> matrix) {
    ArrayList<Integer> eccentricities = new ArrayList<Integer>();

    for (int i = 0; i < matrix.size(); i++) {
      int result = findMaxDistance(matrix, i);
      if (result == Integer.MAX_VALUE) {
        h.exitOnPurpose("Graf nie jest spójny!");
      }
      eccentricities.add(result);
    }

    h.displayEccentricities(eccentricities);

    ArrayList<Integer> minDist = new ArrayList<Integer>();
    ArrayList<Integer> minIndex = new ArrayList<Integer>();

    minDist.add(Integer.MAX_VALUE);
    minIndex.add(0);

    for (int i = 0; i < eccentricities.size(); i++) {
      if (minDist.get(0) >= eccentricities.get(i)) {
        if (minDist.get(0) > eccentricities.get(i)) {
          minDist.clear();
          minIndex.clear();
        }
        minDist.add(eccentricities.get(i));
        minIndex.add(i);
      }
    }
    h.displayAnswer(minIndex, minDist);
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

    for (int i = 0; i < matrix.size(); i++) {
      Boolean cycle = isCyclic(matrix);
      if (cycle) {
        h.exitOnPurpose("Podany graf zawiera cykl!");
      }
    }

    findCenterJordan(matrix);
    System.out.print("\n");
  }
}
