import java.util.ArrayList;

public class App {

  public static FileManager fm = new FileManager();
  public static VertexManager vm = new VertexManager();
  public static EdgeManager em = new EdgeManager();
  public static Helpers h = new Helpers();

  public static int getMin(int num1, int num2) {
    if (num1 < num2) {
      return num1;
    }
    return num2;
  }

  public static void displaySet(ArrayList<Vertex> vertices) {
    int counter = 0;
    String set = new String();

    set = set + "{";
    for (Vertex v : vertices) {
      set = set + v.getNumber();
      if (counter < vertices.size()-1)
        set = set + ",";
      counter++;
    }
    set = set + "}";

    System.out.print(String.format("%15s", set));
  }

  public static void displayOneRow(ArrayList<Vertex> vertices, Vertex v, ArrayList<Integer> row, int i, int size) {
    System.out.print("   " + String.format("%8d", i) + " │ ");
    System.out.print(v.getNumber() + " │ ");
    displaySet(vertices);
    for (int j = 0; j < size; j++) {
      System.out.print(" │");
      if (row.get(j) == Integer.MAX_VALUE)
        System.out.print(String.format("%5s", "∞"));
      else
        System.out.print(String.format("%5d", row.get(j)));
    }
    h.breakLine();
  }

  public static void displayHeader(Graph g) {
    System.out.print("   Iteracja │ u │ ");
    System.out.print(String.format("%15s", "U"));
    for (int i = 0; i < g.getVertices().size(); i++)
      System.out.print(" │ X[" + (i+1) + "]");
    h.breakLine();
  }

  public static ArrayList<ArrayList<Integer>> theDijkstraAlgorithm(Graph g, Vertex s) {
    displayHeader(g);

    ArrayList<Vertex> vertices = g.getVertices();

    int n = vertices.size();
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

    for (int i = 0; i < n; i++) {
      ArrayList<Integer> tmpList = new ArrayList<Integer>();
      for (int j = 0; j < n; j++) {
        tmpList.add(0);
      }
      matrix.add(tmpList);
    }

    matrix.get(0).set(s.getNumber()-1, 0);
    vertices.remove(s);

    for (Vertex v : vertices)
      matrix.get(0).set(v.getNumber()-1, em.getWeightTwoVertices(g, s.getNumber(), v.getNumber()));

    int counter = 1;
    displayOneRow(vertices, s, matrix.get(0), 0, n);

    while (!vertices.isEmpty()) {
      Vertex u = new Vertex();
      int min = Integer.MAX_VALUE;

      for (int i = 0; i < n; i++)
        matrix.get(counter).set(i, matrix.get(counter-1).get(i));

      for (Vertex vertex : vertices) {
        if (matrix.get(counter).get(vertex.getNumber()-1) < min) {
          min = matrix.get(counter).get(vertex.getNumber()-1);
          u = vertex;
        }
      }
      vertices.remove(u);

      for (Vertex v : vertices) {
        int firstValue = matrix.get(counter).get(v.getNumber()-1);
        int secondValue = matrix.get(counter).get(u.getNumber()-1) + em.getWeightTwoVertices(g, u.getNumber(), v.getNumber());

        // ------------------------------------------------------------------------
        if (getMin(firstValue, secondValue) != -Integer.MAX_VALUE) // ???
          matrix.get(counter).set(v.getNumber()-1, getMin(firstValue, secondValue));
        // ------------------------------------------------------------------------
      }

      displayOneRow(vertices, u, matrix.get(counter), counter, n);
      counter++;
    }

    return matrix;
  }

  public static void main(String args[]) {
    h.clearScreen();
    h.breakLine();

    Graph graph = new Graph();

    if (args.length > 0)
      graph = fm.prepareGraph(args[0]);
    else
      h.exitOnPurpose("Nie podano pliku z grafem wejściowym!");

    h.frameIt("Plik: " + args[0], true);
    h.breakLine();

    h.frameIt("Graf Wejściowy", false);
    em.displayEdges(graph.getEdges());
    h.breakLine();

    int n = graph.getVertices().size();
    ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    matrix = theDijkstraAlgorithm(graph, vm.getVertexByNumber(graph, 1));
    h.breakLine();
  }
}
