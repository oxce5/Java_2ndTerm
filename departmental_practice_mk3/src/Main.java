import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);

  public enum CATEGORY {
    HIGH,
    AVERAGE,
    LOW
  }

  public void MainProcess() {
    System.out.print("How many households? ");
    String[] householdNames = new String[scan.nextInt()];
    for (String household : householdNames) {
      SaveData("ElectricityBillingReport.txt", DataConstructor(householdNames));
    }
  }

  private String DataConstructor(String[] households) {
    StringBuilder sb = new StringBuilder();
    scan.nextLine();
    System.out.print("Enter household name: ");
    String name = scan.nextLine().trim();
    sb.append(String.format("Household: %s%n", name));
    System.out.print("Enter monthly consumption (in kWh): ");
    double consumption = scan.nextDouble();

    sb.append(String.format("Consumption: %.2f kWh%n", consumption));
    sb.append(String.format("Original Bill: %,.2f%n",ComputeBill(consumption)));
    if (SetCategory(consumption).equals(CATEGORY.LOW)) {
      sb.append(String.format("Discounted bill: %,.2f%n", ApplyDiscount(ComputeBill(consumption), SetCategory(consumption))));
    } else {
      sb.append(String.format("Discounted bill: Not Applicable.%n"));
    }
    sb.append("-----------------------");
    return sb.toString();
  }

  private CATEGORY SetCategory(double consumption) {
    if (consumption >= 500) {
      return CATEGORY.HIGH;
    } else if ((consumption >= 200) && (consumption < 500)) {
      return CATEGORY.AVERAGE;
    } else {
      return CATEGORY.LOW;
    }
  }

  private double ComputeBill(double consumption) {
    double bill = consumption;
    CATEGORY category = SetCategory(consumption);
    if (category.equals(CATEGORY.HIGH)) {
      return bill * 12.00;
    } else if (category.equals(CATEGORY.AVERAGE)) {
      return bill * 10.00;
    } else {
      return bill * 8.00;
    }
  }

  private double ApplyDiscount(double bill, CATEGORY category) {
    if (!category.equals(CATEGORY.LOW)) return bill;
    return bill * 0.9;
  }

  private static void SaveData(String filename, String data) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      writer.write(data);
      writer.newLine();
      writer.close();
    } catch (Exception e) {
    }
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.MainProcess();
    scan.close();
  }
}
