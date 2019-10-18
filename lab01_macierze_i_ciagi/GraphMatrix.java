import java.util.ArrayList;

public class GraphMatrix {

    public static VertexManager vm = new VertexManager();
    public static EdgeManager em = new EdgeManager();
    public static Helpers h = new Helpers();

    public static ArrayList<ArrayList<Integer>> prepareNewMatrix(int matrixSize) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < matrixSize; i++) {
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < matrixSize; j++) {
                matrix.get(i).add(0);
            }
        }
        return matrix;
    }

    public static void main(String args[]) {
        h.clearScreen();
        System.out.print("\n");

        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        matrix = prepareNewMatrix(10);

        while (true) {
            h.displayMatrix(matrix);

            vm.getVertexDegree(matrix, 1);
            vm.getVertexDegree(matrix, 2);

            vm.getMinAndMaxDegree(matrix);
            vm.getEvenOrOdd(matrix);
            vm.getDegreeSeries(matrix);

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
            System.out.print("\n");
        }
    }
}
