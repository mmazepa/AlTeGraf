import java.util.ArrayList;

public class GraphMatrix {

    public static FileManager fm = new FileManager();
    public static VertexManager vm = new VertexManager();
    public static EdgeManager em = new EdgeManager();
    public static Helpers h = new Helpers();

    public static void isomorphicCycle(ArrayList<ArrayList<Integer>> matrix) {
      boolean icycle = false;
      ArrayList<String> icycles = new ArrayList<String>();

      for (int i = 0; i < matrix.size(); i++) {
        for (int j = 0; j < matrix.size(); j++) {
          for (int k = 0; k < matrix.size(); k++) {
            boolean v1_v2 = matrix.get(i).get(j) > 0;
            boolean v2_v3 = matrix.get(j).get(k) > 0;
            boolean v3_v1 = matrix.get(k).get(i) > 0;

            icycle = v1_v2 && v2_v3 && v3_v1;
            if (icycle) icycles.add((i+1) + " " + (j+1) + " " + (k+1));
          }
        }
      }

      System.out.print("   Cykl izomorficzny: ");
      System.out.print(!icycles.isEmpty() ? "TAK" : "NIE");
      System.out.print("\n");
      for (int i = 0; i < icycles.size(); i++) {
        if (i%5 == 0) System.out.print("\n   ");
        System.out.print("   " + icycles.get(i));
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
