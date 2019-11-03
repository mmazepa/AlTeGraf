import java.util.ArrayList;

public class GraphMatrix {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static int minDistance(int matrixSize, int[] dist, Boolean sptSet[])  {
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

  public static int[] findDistances(ArrayList<ArrayList<Integer>> matrix, int src) {
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
    return dist;
  }

  public static void findCenterJordan(ArrayList<ArrayList<Integer>> matrix) {
    ArrayList<ArrayList<Integer>> fullDist = new ArrayList<ArrayList<Integer>>();

    for (int i = 0; i < matrix.size(); i++) {
      int[] result = findDistances(matrix, i);
      ArrayList<Integer> dist = new ArrayList<Integer>();
      for (int j = 0; j < matrix.size(); j++) {
        dist.add(result[j]);
      }
      fullDist.add(dist);
    }
    h.displayMatrix(fullDist);
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

    findCenterJordan(matrix);
  }
}
