import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        System.out.print("\n");
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

        clearScreen();
    }

    public static void removeEdge(int from, int to) {
        from = from - 1;
        to = to - 1;
        int val1 = matrix.get(from).get(to);
        int val2 = matrix.get(to).get(from);

        if (val1 != 0 && val2 != 0) {  
            if (from != to) {
                matrix.get(from).set(to, val1-1);
                matrix.get(to).set(from, val2-1);
            } else {
                matrix.get(from).set(to, val1-2);
            }
        }

        clearScreen();
    }

    public static void addVertex() {
        matrixSize++;
        for (ArrayList<Integer> row : matrix) {
            row.add(0);
        }
        ArrayList<Integer> newRow = new ArrayList<Integer>();
        for (int i = 0; i < matrixSize; i++) newRow.add(0);
        matrix.add(newRow);

        clearScreen();
    }

    public static void removeVertex(int vertex) {
        vertex = vertex - 1;
        matrixSize--;
        for (ArrayList<Integer> row : matrix) row.remove(vertex);
        matrix.remove(vertex);

        clearScreen();
    }

    public static void getVertexDegree(int vertex) {
        vertex = vertex - 1;
        int degree = 0;
        for (int i = 0; i < matrix.get(vertex).size(); i++) {
            degree = degree + matrix.get(vertex).get(i);
        }
        System.out.println("   Stopień wierzchołka " + (vertex+1) + ": " + degree);
        System.out.print("\n");
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
        System.out.print("\n");
    }

    public static int sumArray(ArrayList<Integer> array) {
        int sum = 0;
        for (Integer num : array) {
            sum = sum + num;
        }
        return sum;
    }

    public static void getEvenOrOdd(ArrayList<ArrayList<Integer>> matrix) {
        int even = 0;
        int odd = 0;

        for (ArrayList<Integer> row : matrix) {
            int degree = sumArray(row);
            if (degree%2 == 0) even++;
            else odd++;
        }

        System.out.println("   Wierzchołków parzystych:    " + even);
        System.out.println("   Wierzchołków nieparzystych: " + odd);
        System.out.print("\n");
    }

    public static void getDegreeSeries(ArrayList<ArrayList<Integer>> matrix) {
        ArrayList<Integer> series = new ArrayList<Integer>();
        for (ArrayList<Integer> row : matrix) {
            series.add(sumArray(row));
        }
        System.out.println("   Ciąg stopni (niesort): " + series);
        Collections.sort(series, Collections.reverseOrder()); 
        System.out.println("   Ciąg stopni (sort):    " + series);
        System.out.print("\n");
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        //System.out.flush();
    }

    public static void displayMenu() {
        System.out.println("   MENU:");
        System.out.println("   Co chcesz zrobić?");
        System.out.println("      1 - dodaj wierzchołek");
        System.out.println("      2 - usuń wierzchołek");
        System.out.println("      3 - dodaj krawędź");
        System.out.println("      4 - usuń krawędź");
        System.out.println("      5 - wyjdź");
        System.out.print("\n");
    }

    public static int getNumberFromUser(String textToDisplay) {
        Scanner in = new Scanner(System.in);
        System.out.print("   " + textToDisplay);
        int choice = in.nextInt();
        return choice;
    }

    public static void main(String args[]) {
        clearScreen();
        System.out.print("\n");
        
        for (int i = 0; i < matrixSize; i++) {
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < matrixSize; j++) {
                matrix.get(i).add(0);
            }
        }

        int x = 0;

        while (true) {
            displayMatrix(matrix);

            getVertexDegree(1);
            getVertexDegree(2);
            
            getMinAndMaxDegree(matrix);
            getEvenOrOdd(matrix);
            getDegreeSeries(matrix);
            
            displayMenu();
            int choice = getNumberFromUser("Wybór: ");

            int vertex = 0;
            int v1 = 0;
            int v2 = 0;

            switch (choice) {
                case 1:
                    addVertex();
                    break;
                case 2:
                    System.out.println("\n   USUWANIE WIERZCHOŁKA:");
                    vertex = getNumberFromUser("   Numer wierzchołka: ");
                    removeVertex(vertex);
                    break;
                case 3:
                    System.out.println("\n   DODAWANIE KRAWĘDZI:");
                    v1 = getNumberFromUser("   Wierzchołek 1: ");
                    v2 = getNumberFromUser("   Wierzchołek 2: ");
                    addEdge(v1, v2);
                    break;
                case 4:
                    System.out.println("\n   USUWANIE KRAWĘDZI:");
                    v1 = getNumberFromUser("   Wierzchołek 1: ");
                    v2 = getNumberFromUser("   Wierzchołek 2: ");
                    removeEdge(v1, v2);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }

            clearScreen();
            System.out.print("\n");
        }
    }
}