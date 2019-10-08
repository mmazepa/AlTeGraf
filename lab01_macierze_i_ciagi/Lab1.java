import java.util.ArrayList;

public class Lab1 {

    public static ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    public static int matrixSize = 10;

    public static void displayMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            System.out.print("   ");
            for (int j = 0; j < matrix.get(i).size(); j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.print("\n");
        }
    }

    public static void addEdge(int from, int to) {
        from = from - 1;
        to = to - 1;
        int val1 = matrix.get(from).get(to);
        int val2 = matrix.get(to).get(from);

        if (from != to) {
            matrix.get(from).set(to, val1+1);
            matrix.get(to).set(from, val2+1);
        } else {
            matrix.get(from).set(from, val1+2);
        }
    }

    public static void removeEdge(int from, int to) {
        from = from - 1;
        to = to - 1;
        int val1 = matrix.get(from).get(to);
        int val2 = matrix.get(to).get(from);
  
        if (val1 != 0 && val2 != 0) {
            matrix.get(from).set(to, val1-1);
            matrix.get(to).set(from, val2-1);
        } 
    }

    public static void getVertexDegree(int vertex) {
        vertex = vertex - 1;
        int degree = 0;
        for (int i = 0; i < matrix.get(vertex).size(); i++) {
            degree = degree + matrix.get(vertex).get(i);
        }
        System.out.println("   Stopień wierzchołka " + (vertex+1) + ": " + degree);
    }

    public static void getMinAndMaxDegree(ArrayList<ArrayList<Integer>> matrix) {
        int min = 0;
        int max = 0;

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (matrix.get(i).get(j) < min) min = matrix.get(i).get(j);
                if (matrix.get(i).get(j) > max) max = matrix.get(i).get(j);
            }
        }

        System.out.println("   Min stopień grafu: " + min);
        System.out.println("   Max stopień grafu: " + max);
    }

    public static void main(String args[]) {
        for (int i = 0; i < matrixSize; i++) {
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < matrixSize; j++) {
                matrix.get(i).add(0);
            }
        }

        addEdge(1,1);
        addEdge(1,1);
        addEdge(1,1);
        addEdge(1,1);
        addEdge(1,4);
        addEdge(1,4);
        addEdge(1,4);
        addEdge(2,4);
        addEdge(2,7);
        addEdge(2,7);
        addEdge(2,7);
        addEdge(2,10);
        addEdge(3,5);

        removeEdge(1,4);
        removeEdge(3,5);

        System.out.print("\n");

        displayMatrix(matrix);
        System.out.print("\n");

        getVertexDegree(1);
        getVertexDegree(2);
        System.out.print("\n");

        getMinAndMaxDegree(matrix);
        System.out.print("\n");
    }
}