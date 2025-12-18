import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  /**
   * Main UI method
   * @throws IOException
   */
  public static void MainUI() throws IOException{
    System.out.println("===Library Book Management System===");
    System.out.println("1) Add New Book");
    System.out.println("2) View All Books");
    System.out.println("3) Exit");
    System.out.print("Choice: ");
    int choice = scan.nextInt();

    if (choice > 3) {
      System.out.println("Invalid choice. ");
      MainUI();
    }

    switch (choice) {
      case 1:
        saveFile("library_report.txt", constructData("library_report.txt"));
        MainUI();
        break;
      case 2:
        readFile("library_report.txt", false);
        MainUI();
        break;
      default:
        System.out.println("Goodbye!");
        scan.close();
        break;
    }
  }

  /**
   * Constructs a String based on user input 
   * @param filename
   * @return formatted String of data
   * @throws IOException
   */
  public static String constructData(String filename) throws IOException {
      StringBuilder sb = new StringBuilder();

      // Consume newline
      scan.nextLine();

      // Define prompts and field labels
      String[][] fields = {
        {"Enter book ID: ", "ID"},
        {"Enter book name: ", "Title"},
        {"Enter book author: ", "Author"},
        {"Enter book genre: ", "Genre"},
        {"Enter book availability (Available/Not Available): ", "Status"}
      };

      // Get next index once
      int index = readFile(filename, true);

      // Iterate through field 2D array and build a String
      for (int i = 0; i < fields.length; i++) {
        System.out.print(fields[i][0]);
        String input = scan.nextLine().trim();

        if (i == 0) {
          // For ID, prepend the index
          sb.append(String.format("%d. %s: %s", index, fields[i][1], input));
        } else {
          sb.append(" | ").append(fields[i][1]).append(": ").append(input);
        }
      }

      return sb.toString();
  }

  // Checks if file is empty through exists() or 0 file len
  private static boolean isFileEmpty(String filename) {
    File file = new File(filename);
    if (!(file.exists()) || (file.length() == 0)) return true;
    return false;
  }

  /**
   * @param filename name of file
   * @param data formatted String
   */
  public static void saveFile(String filename, String data) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
      if (isFileEmpty(filename)) writer.write("===All Books===\n");
      writer.write(data);
      writer.newLine();
      writer.close();
      System.out.println("\nBook saved:");
      System.out.println(data);
      System.out.printf("%nData saved to %s.%n", filename);
    }
  }

  /**
   * @param filename
   * @param fetchLastIndex
   * @return null if fetchLastIndex is set to false.
   * @return 1 if fetchLastIndex is true and file has no file Length or does not exist.
   * @return index + 1 of last line of file if file exists
   * @throws IOException
   */
  public static Integer readFile(String filename, boolean fetchLastIndex) throws IOException {
      if (isFileEmpty(filename) && fetchLastIndex) return 1;
      String lastLine = null;

      try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
          String line;
          while ((line = reader.readLine()) != null) {
              if (!fetchLastIndex) {
                  System.out.println(line);
              }
              lastLine = line;
          }
      }

      // Housekeeping
      if (!fetchLastIndex || lastLine == null || lastLine.isEmpty()) {
          return null;
      }

      String[] index = lastLine.split("\\.");
      return Integer.parseInt(index[0]) + 1;
  }
  
  public static void main(String[] args) throws IOException {
    MainUI();
  }
}
