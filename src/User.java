package Admin;

import java.util.ArrayList;
import java.util.List;

class User {
    String userId;
    String userName;
    String password;
    String email;
    double fine;
    List<String> booksBorrowed; 

    public User(String userId, String userName, String password, String email) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fine = 0;
        this.booksBorrowed = new ArrayList<>();
    }
}
