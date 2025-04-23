package Admin;

import java.util.Date;

class IssueService {
    public static void issueBook(Book book, User user) {
        if (book.issuedTo == null) {
            book.issuedTo = user.userId;
            book.issueDate = new Date(); // current date
            user.booksBorrowed.add(book.bookId);
            System.out.println("Book issued successfully to " + user.userName);
        } else {
            System.out.println("Book is already issued to another user");
        }
    }

    public static void returnBook(Book book, User user) {
        if (book.issuedTo != null && book.issuedTo.equals(user.userId)) {
            book.issuedTo = null;
            book.issueDate = null;
            user.booksBorrowed.remove(book.bookId);
            System.out.println("Book returned successfully by " + user.userName);
        } else {
            System.out.println("This book was not issued to this user");
        }
    }
}