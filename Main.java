import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class LoginService {
    private Map<String, User> userDatabase;

    public LoginService() {
        this.userDatabase = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        if (!userDatabase.containsKey(username)) {
            User newUser = new User(username, password);
            userDatabase.put(username, newUser);
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists. Please choose another username.");
        }
    }

    public boolean authenticateUser(String username, String password) {
        User user = userDatabase.get(username);
        return user != null && user.getPassword().equals(password);
    }
}

public class Main {
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    loginService.registerUser(regUsername, regPassword);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    if (loginService.authenticateUser(loginUsername, loginPassword)) {
                        System.out.println("Login successful! Welcome, " + loginUsername + "!");
                        // Add logic to redirect to the secured page or perform other actions
                    } else {
                        System.out.println("Login failed. Please check your username and password.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
