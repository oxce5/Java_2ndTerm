import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SecondLabExam {

  public static int countPassed(double[] grades) {
    int passed = 0;
    for (double d : grades) {
      if (d >= 75) passed++;
    }
    return passed;
  }

  public static void writeToFile(String[] names, double[] grades, int passed, String filename) throws IOException {
    PrintWriter file = new PrintWriter(new FileWriter(filename));
    file.write("Student Grades:\n");
    for (int j = 0; j < grades.length; j++) {
      file.printf("%s - %.2f%n", names[j], grades[j]);
    }
    file.printf("%nPassed: %d%n", passed);
    file.printf("Failed: %d%n", (grades.length - passed));
    file.close();
  }

  public static void main(String[] args) throws IOException {
    Scanner scan = new Scanner(System.in);
    System.out.print("Enter number of students: ");
    int arrLen = Integer.parseInt(scan.next());
    double[] grades = new double[arrLen];
    String[] names = new String[arrLen];
    for (int i = 0; i < names.length; i++) {
      scan.nextLine();
      System.out.print("\nEnter student name: ");
      String name =  scan.nextLine();
      System.out.print("Enter student grade: ");
      Double grade = scan.nextDouble();
      names[i] = name;
      grades[i] = grade;
    }
    writeToFile(names, grades, countPassed(grades), "GradesReport.txt");
    scan.close();
  }
}
