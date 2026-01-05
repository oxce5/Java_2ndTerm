import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  private String[] getProductName(int length) {
    String[] productNames = new String[length];
    scan.nextLine();
    for (int i = 0; i < productNames.length; i++) {
      System.out.printf("Enter product %d name: ", (i+1));
      productNames[i] = scan.nextLine().trim();
    }
    return productNames;
  }

  private int[] getProductStock(int length, String[] products) {
    int[] productStocks = new int[length];
    for (int i = 0; i < productStocks.length; i++) {
      System.out.printf("Enter %s stock: ", products[i]);
      productStocks[i] = scan.nextInt();
      
    }
    return productStocks;
  }

  private String getStockStatus(int stock) {
    if (stock > 500) return "Overstocked";
    else if ((500 >= stock) && (stock <= 200)) return "Normal stock";
    else return "Critical stock";
  }

  private String formatData(String[] names, int[] stocks) {
    StringBuilder sb = new StringBuilder();
    String time = "temporary";
    sb.append(String.format("---------- %s ----------\n", "STOCKS REPORT"));
    for (int i = 0; i < stocks.length; i++) {
      sb.append(String.format("%s: %d units — %s\n", names[i], stocks[i], getStockStatus(stocks[i])));
    }
    sb.append(String.format("---------- %s ----------\n", time));

    return sb.toString();
  }

  private void writeToFile(String filename, String data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      writer.write(data);
      writer.newLine();
      writer.close();
    } catch (IOException e) {
      System.err.printf("%s: File %s not found.", e, filename);
    }
  }

  public void MainThread() {
    System.out.println("--------- STOCK MANAGER ----------");
    System.out.print("Please enter the amount of products: ");
    int length = scan.nextInt();
    String[] productNames = getProductName(length);
    int[] productStocks = getProductStock(length, productNames);
    writeToFile("stocks_report.txt", formatData(productNames, productStocks));
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.MainThread();
  }
}
