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

  public static int eccentricity(ArrayList<Integer> vertexDist) {
    int ecce = 0;
    for (int i = 0; i < vertexDist.size(); i++) {
      if (ecce < vertexDist.get(i)) {
        ecce = vertexDist.get(i);
      }
    }
    return ecce;
  }

  public static void displayAnswer(ArrayList<Integer> index, ArrayList<Integer> dist) {
    String indexString = new String();
    String distString = new String();

    if (index.size() == 1 && dist.size() == 1) {
      indexString = String.format("%2d", (index.get(0)+1));
    } else if (index.size() == 2 && dist.size() == 2) {
      indexString = String.format("%2d %2d", (index.get(0)+1), (index.get(1)+1));
    }

    distString = String.format("%2d", dist.get(0));

    System.out.println("   Centrum drzewa to: " + indexString);
    System.out.println("   Najdalszy dystans: " + distString);
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

    ArrayList<Integer> eccentricities = new ArrayList<Integer>();
    for (int i = 0; i < matrix.size(); i++) {
      eccentricities.add(eccentricity(fullDist.get(i)));
    }
    displayEccentricities(eccentricities);

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

    displayAnswer(minIndex, minDist);
  }

  public static void displayEccentricities(ArrayList<Integer> eccentricities) {
    System.out.print("    index │");
    for (int i = 0; i < eccentricities.size(); i++) {
      String output = String.format("%2s", (i+1));
      System.out.print(output + " ");
    }
    System.out.print("\n");

    System.out.print("   ───────┼");
    for (int i = 0; i < eccentricities.size(); i++) {
      System.out.print("───");
    }
    System.out.print("\n");

    System.out.print("    ecce  │");
    for (int i = 0; i < eccentricities.size(); i++) {
      String output = String.format("%2s", eccentricities.get(i));
      System.out.print(output + " ");
    }
    System.out.print("\n\n");
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
    System.out.print("\n");
  }
}
