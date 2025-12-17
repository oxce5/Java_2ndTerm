import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  /*
   * Summary: Asks for input from user with a dynamic length array O(n)
   * @return An array of doubles storing sales of each branch
  */
  public static double[] inputSales() {
    Scanner scan = new Scanner(System.in);
    System.out.print("How many branches to analyze? ");
    int arrLen = scan.nextInt();
    double[] sales = new double[arrLen];
    
    System.out.println("\nEnter sales for " + arrLen + " branches: ");
    for (int i = 0; i < sales.length; i++) {
      System.out.print("Enter branch " + (i+1) + " sales: ₱");
      double branchSales = scan.nextDouble();
      sales[i] = branchSales;
    }
    System.out.println();
    scan.close();
    return sales;
  }

  /*
   * Summary: This method evaluates performance levels O(1)
   * @param sales The sales amount to be evaluated
   * @return String corresponding to the performance level
  */
  public static String evaluatePerformance(double sales) {
    if (sales > 100_000) {
      return "Excellent Performance";
    } else if ((50_000 <= sales) && (sales <= 100_000)) {
      return "Good Performance";
    } else {
      return "Needs Improvement";
    }
  }

  /*
   * Summary: Aggregates and formats data for saving to a file O(n)
   * @param An array of doubles
   * @return String of data with Branch index, sales, and performance level
  */
  public static String aggregateData(double[] sales) {
    StringBuilder sb = new StringBuilder();
    int branchCount = 1;
    for (double d : sales) {
      sb.append(String.format("Branch %d: ₱%,.2f - %s%n", branchCount, d, evaluatePerformance(d)));
      branchCount++;
    }
    return sb.toString();
  }  
  
  /*
   * Summary: Saves aggregated data to a file
   * @param Strings corresponding to file name and data
  */
  public static void saveFile(String filename, String data) {
    try (BufferedWriter Writer = new BufferedWriter(new FileWriter(filename))) {

      Writer.write(data);
      Writer.close();
      System.out.println("Report saved to " + filename);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String data = aggregateData(inputSales());
    System.out.println(data);
    saveFile("sales_report.txt", data);
  }
}
