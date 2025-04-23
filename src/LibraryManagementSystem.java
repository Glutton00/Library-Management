package Admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class LibraryManagementSystem {
    private static List<User> users = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
       
        initializeData();
        
        loginScreen();
    }

    private static void initializeData() {
       
        users.add(new User("U001", "Ritik", "ritik123", "Ritik@example.com"));
        users.add(new User("U002", "Aman", "aman123", "Aman@example.com"));
        
        
        admins.add(new Admin("A001", "Yatin", "Yatin123", "Yatin@example.com"));
        
        
        books.add(new Book("Java Programming", "B001"));
        books.add(new Book("Data Structures", "B002"));
        books.add(new Book("Algorithms", "B003"));
        
        
        try {
            Book book1 = books.get(0);
            book1.issuedTo = "U001";
            book1.issueDate = dateFormat.parse("01/01/2023"); 
            users.get(0).booksBorrowed.add(book1.bookId);
            
            Book book2 = books.get(1);
            book2.issuedTo = "U002";
            book2.issueDate = new Date(); 
            users.get(1).booksBorrowed.add(book2.bookId);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void loginScreen() {
        System.out.println("==== Library Management System ====");
        System.out.println("1. User Login");
        System.out.println("2. Admin Login");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (choice == 1) {
            User user = authenticateUser(userId, password);
            if (user != null) {
                userMenu(user);
            } else {
                System.out.println("Invalid User ID or Password");
            }
        } else if (choice == 2) {
            Admin admin = authenticateAdmin(userId, password);
            if (admin != null) {
                adminMenu();
            } else {
                System.out.println("Invalid Admin ID or Password");
            }
        } else {
            System.out.println("Invalid Choice");
        }
    }

    private static User authenticateUser(String userId, String password) {
        for (User user : users) {
            if (user.userId.equals(userId) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static Admin authenticateAdmin(String adminId, String password) {
        for (Admin admin : admins) {
            if (admin.adminId.equals(adminId) && admin.password.equals(password)) {
                return admin;
            }
        }
        return null;
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\n==== User Menu ====");
            System.out.println("1. Show books borrowed by me");
            System.out.println("2. Check my fine");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    showUserBooks(user);
                    break;
                case 2:
                    System.out.println("Your current fine: Rs." + user.fine);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void showUserBooks(User user) {
        if (user.booksBorrowed.isEmpty()) {
            System.out.println("You haven't borrowed any books");
            return;
        }

        System.out.println("Books borrowed by you:");
        for (String bookId : user.booksBorrowed) {
            Book book = findBookById(bookId);
            if (book != null) {
                System.out.println("- " + book.bookName + " (ID: " + book.bookId + ")");
                System.out.println("  Issued on: " + dateFormat.format(book.issueDate));
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n==== Admin Menu ====");
            System.out.println("1. Check all borrowed books");
            System.out.println("2. Check fine on all students");
            System.out.println("3. Calculate fine for overdue books");
            System.out.println("4. Report damaged book");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showAllBorrowedBooks();
                    break;
                case 2:
                    showAllFines();
                    break;
                case 3:
                    calculateAllFines();
                    break;
                case 4:
                    reportDamagedBook();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void showAllBorrowedBooks() {
        System.out.println("All borrowed books:");
        boolean found = false;
        for (Book book : books) {
            if (book.issuedTo != null) {
                found = true;
                User user = findUserById(book.issuedTo);
                System.out.println("- " + book.bookName + " (ID: " + book.bookId + ")");
                System.out.println("  Issued to: " + (user != null ? user.userName : "Unknown"));
                System.out.println("  Issued on: " + dateFormat.format(book.issueDate));
            }
        }
        if (!found) {
            System.out.println("No books are currently borrowed");
        }
    }

    private static void showAllFines() {
        System.out.println("Fines for all users:");
        for (User user : users) {
            System.out.println("- " + user.userName + ": Rs." + user.fine);
        }
    }

    private static void calculateAllFines() {
        for (Book book : books) {
            if (book.issuedTo != null) {
                User user = findUserById(book.issuedTo);
                if (user != null) {
                    FineService.calculateFine(book, user);
                }
            }
        }
        System.out.println("Fines calculated for all overdue books");
    }

    private static void reportDamagedBook() {
        System.out.print("Enter book ID: ");
        String bookId = scanner.nextLine();
        Book book = findBookById(bookId);
        
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        
        if (book.issuedTo == null) {
            System.out.println("This book is not currently issued to anyone");
            return;
        }
        
        User user = findUserById(book.issuedTo);
        if (user != null) {
            book.isDamaged = true;
            FineService.applyDamageFine(user);
            System.out.println("Book marked as damaged and fine applied");
        }
    }

    private static Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.bookId.equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    private static User findUserById(String userId) {
        for (User user : users) {
            if (user.userId.equals(userId)) {
                return user;
            }
        }
        return null;
    }
}