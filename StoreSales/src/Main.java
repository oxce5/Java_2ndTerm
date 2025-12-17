import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  public static double[] inputSales() {
    System.out.print("How many branches to analyze? ");
    int arrLen = scan.nextInt();
    double[] sales = new double[arrLen];
    
    System.out.println("\nEnter sales for " + arrLen + " branches: ");
    for (int i = 0; i < sales.length; i++) {
      System.out.print("Enter branch " + (i+1) + " sales: ");
      double branchSales = scan.nextDouble();
      sales[i] = branchSales;
    }
    return sales;
  }

  public static String evaluatePerformance(double sales) {
    if (sales > 100_000) {
      return "Excellent Performance";
    } else if ((50_000 <= sales) && (sales <= 100_000)) {
      return "Good Performance";
    } else {
      return "Needs Improvement";
    }
  }
  
  public static void saveFile(String filename, double[] data) {
    try (PrintWriter file = new PrintWriter(new FileWriter(filename))) {
      for (int i = 0; i < data.length; i++) {
        file.printf("Branch %d: ₱%,.2f - %s%n", i+1, data[i], evaluatePerformance(data[i]));
      }
      file.close();
      System.out.println("Report saved to " + filename);
    } catch (IOException e) {

    }
  }

  public static void main(String[] args) {
    double[] sales = inputSales();
    saveFile("sales_report.txt", sales);
  }
}
