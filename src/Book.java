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
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getIssuedTo() {
		return issuedTo;
	}

	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public boolean isDamaged() {
		return isDamaged;
	}

	public void setDamaged(boolean isDamaged) {
		this.isDamaged = isDamaged;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", bookId=" + bookId + ", issuedTo=" + issuedTo + ", issueDate="
				+ issueDate + ", isDamaged=" + isDamaged + "]";
	}

}
