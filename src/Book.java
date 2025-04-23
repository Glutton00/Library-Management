package Admin;

import java.util.Date;

public class Book {
	String bookName;
    String bookId;
    String issuedTo; 
    Date issueDate;
    boolean isDamaged;

    public Book(String bookName, String bookId) {
        this.bookName = bookName;
        this.bookId = bookId;
        this.issuedTo = null;
        this.issueDate = null;
        this.isDamaged = false;
}

}