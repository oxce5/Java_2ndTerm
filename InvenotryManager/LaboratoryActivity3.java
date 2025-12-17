import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class LaboratoryActivity3 {
  static Scanner scan = new Scanner(System.in);
  static int stock = 0;
  static int totalQuantity = 0;
  public static void fileWrite(String filename, String[] names, int[] quantities) {
    try (PrintWriter file = new PrintWriter(new FileWriter(filename, true))) {
      file.write("\nProducts and Quantities: \n");
      for (int i = 0; i < quantities.length; i++) {
        file.printf("%s - %d\n", names[i], quantities[i]);
      }
      file.printf("\nTotal quantity: %d", totalQuantity);
      file.printf("\nIn stock: %d", stock);
      file.printf("\nOut of stock: %d\n", (names.length - stock));
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static void InventoryManager(String[] names, int[] quantities) {
    scan.nextLine();
    for (int i = 0; i < names.length; i++) {
      System.out.print("Enter product name: ");
      String productName = scan.nextLine();
      System.out.print("Enter " + productName + " quantity: ");
      int productQuantity = Integer.parseInt(scan.nextLine());
      names[i] = productName;
      quantities[i] = productQuantity;
      if (productQuantity > 0) stock++;
      totalQuantity += productQuantity;
    }
  }
  public static void main(String[] args) {
    System.out.print("Enter the number of products: ");
    int productsLength = scan.nextInt();
    String[] productNames = new String[productsLength];
    int[] productQuantities = new int[productsLength];
    InventoryManager(productNames, productQuantities);
    fileWrite("invetory.txt", productNames, productQuantities);
    scan.close();
  }
}
