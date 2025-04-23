package Admin;

import java.util.Date;

class FineService {
    private static final double DAILY_FINE = 15.0;
    private static final double DAMAGE_FINE = 200.0;

    public static void calculateFine(Book book, User user) {
        if (book.issuedTo == null || !book.issuedTo.equals(user.userId)) {
            System.out.println("This book is not issued to this user");
            return;
        }

        long diffInMillies = Math.abs(new Date().getTime() - book.issueDate.getTime());
        long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);

        if (diffInDays > 15) {
            double fineAmount = (diffInDays - 15) * DAILY_FINE;
            user.fine += fineAmount;
            System.out.println("Fine of Rs." + fineAmount + " applied for late return");
        }

        if (book.isDamaged) {
            user.fine += DAMAGE_FINE;
            System.out.println("Fine of Rs." + DAMAGE_FINE + " applied for damaged book");
        }
    }

    public static void applyDamageFine(User user) {
        user.fine += DAMAGE_FINE;
        System.out.println("Damage fine of Rs." + DAMAGE_FINE + " applied to " + user.userName);
    }
}