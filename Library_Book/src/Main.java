import java.util.Scanner;

public class Main {
  static Scanner scan = new Scanner(System.in);
  static String finalString = "";

  public static String BookUtil(String id, String title, String author, String genre, int stock) {
    String tempString = String.format("\n ID: %s | TITLE: %s | AUTHOR: %s | GENRE: %s | %s ", id, title, author, genre, (stock > 0) ? "AVAILABLE" : "CHECKED OUT");
    finalString = finalString + tempString; 
    return finalString;
  }

  public static void UI() {
    while (true) {
      System.out.println("LIBRARY BOOK MANAGEMENT");
      System.out.println("(1) ADD BOOK");
      System.out.println("(2) LIST ALL BOOK/S");
      System.out.print("(3) EXIT\n-> ");
      switch (scan.next().toLowerCase()) {
        case "1":
          addBook();
          break;

        case "2":
          listBook();
          break;
      
        case "3":
          System.out.println("Exiting...");
          System.exit(0);

        case "exit":
          System.out.println("Exiting...");
          System.exit(0);

        default:
          System.out.println("Invalid input. Please try again.");
          continue;
      }
      scan.close();
    }
  }

  public static void addBook() {
    String id, title, author, genre;
    int stock;
    scan.nextLine();
    System.out.print("Enter book ID: ");
    id = scan.nextLine();
    System.out.print("Enter book title: ");
    title = scan.nextLine();
    System.out.print("Enter book author: ");
    author = scan.nextLine();
    System.out.print("Enter book genre: ");
    genre = scan.nextLine();
    stock = 0;
    while (true) {
      try {
        System.out.print("Enter book stock: ");
        stock = scan.nextInt();
        break;
      } catch (Exception e) {
        System.out.println("Invalid input. Numbers only.");
        scan.nextLine();
      }
    }
    BookUtil(id, title, author, genre, stock);
    System.out.println("Book added successfully!");
    scan.close();
  }

  public static void listBook() {
    if (finalString.isEmpty()) {
      System.out.println("No books stored.");
      return;
    }
    System.out.println("BOOKS STORED");
    System.out.println(finalString + "\n");
  }

  public static void main(String[] args) {
    UI();
  }
}

