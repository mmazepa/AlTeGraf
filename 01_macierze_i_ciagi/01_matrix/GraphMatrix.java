import java.util.ArrayList;
import java.util.Collections;

public class GraphMatrix {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static Boolean checkCycle(ArrayList<ArrayList<Integer>> matrix, int v1, int v2, int v3) {
    Boolean v1_v2 = matrix.get(v1).get(v2) > 0;
    Boolean v2_v3 = matrix.get(v2).get(v3) > 0;
    Boolean v3_v1 = matrix.get(v3).get(v1) > 0;

    Boolean icycle = v1_v2 && v2_v3 && v3_v1;
    Boolean diff = (v1 != v2) && (v2 != v3) && (v3 != v1);

    return icycle && diff;
  }

  public static ArrayList<Integer> prepareCycle(int... values) {
    ArrayList<Integer> cycleList = new ArrayList<Integer>();
    for (int value : values) cycleList.add(value);
    return cycleList;
  }

  public static void isomorphicCycle(ArrayList<ArrayList<Integer>> matrix) {
    ArrayList<String> icycles = new ArrayList<String>();

    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.size(); j++) {
        for (int k = 0; k < matrix.size(); k++) {
          if (checkCycle(matrix, i, j, k)) {
            ArrayList<Integer> cycleList = new ArrayList<Integer>();
            cycleList = prepareCycle((i+1), (j+1), (k+1));
            Collections.sort(cycleList);

            String cycle = String.format("%2d %2d %2d", cycleList.get(0), cycleList.get(1), cycleList.get(2));
            if (!icycles.contains(cycle)) icycles.add(cycle);
          }
        }
      }
    }

    System.out.println("   Podgraf izomorficzny do cyklu C3:");
    System.out.print("      ");
    System.out.print(!icycles.isEmpty() ? "TAK!" : "NIE!");
    System.out.print("\n");

    for (int i = 0; i < icycles.size(); i++) {
      if (i%5 == 0) System.out.print("\n");
      System.out.print("   « " + icycles.get(i) + " »");
    }
    System.out.print("\n\n");
  }

  public static void main(String args[]) {
    h.clearScreen();
    System.out.print("\n");

    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    if (args.length > 0) {
      matrix = fm.prepareNewMatrix(args[0]);
    } else {
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym.");
    }

    while (true) {
      System.out.print("\n");
      h.displayMatrix(matrix);

      vm.getMinAndMaxDegree(matrix);
      vm.getEvenOrOdd(matrix);
      vm.getDegreeSeries(matrix);

      isomorphicCycle(matrix);

      h.displayMenu();
      int choice = h.getNumberFromUser("Wybór: ");

      int vertex = 0;
      int v1 = 0;
      int v2 = 0;

      switch (choice) {
        case 1:
          vm.addVertex(matrix);
          break;
        case 2:
          System.out.println("\n   USUWANIE WIERZCHOŁKA:");
          vertex = h.getNumberFromUser("   Numer wierzchołka: ");
          vm.removeVertex(matrix, vertex);
          break;
        case 3:
          System.out.println("\n   DODAWANIE KRAWĘDZI:");
          v1 = h.getNumberFromUser("   Wierzchołek 1: ");
          v2 = h.getNumberFromUser("   Wierzchołek 2: ");
          em.addEdge(matrix, v1, v2);
          break;
        case 4:
          System.out.println("\n   USUWANIE KRAWĘDZI:");
          v1 = h.getNumberFromUser("   Wierzchołek 1: ");
          v2 = h.getNumberFromUser("   Wierzchołek 2: ");
          em.removeEdge(matrix, v1, v2);
          break;
        case 5:
          System.exit(0);
          break;
        default:
          break;
        }

        h.clearScreen();
    }
  }
}
