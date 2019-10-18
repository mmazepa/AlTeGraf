import java.util.ArrayList;
import java.util.Scanner;

public class Helpers {

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

  public static int sumArray(ArrayList<Integer> array) {
      int sum = 0;
      for (Integer num : array) {
          sum = sum + num;
      }
      return sum;
  }
}
