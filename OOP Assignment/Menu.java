import java.util.Scanner;

public abstract class Menu {
    protected Scanner sc;
    protected boolean isLoggedIn;
    
    public Menu(Scanner sc) {
        this.sc = sc;
        this.isLoggedIn = true;
    }
    
    // Abstract methods that must be implemented by subclasses
    public abstract void displayMenu();
    public abstract void handleOption(int choice);
    
    // Common method for displaying borders
    protected void displayBorder() {
        System.out.println("\n═════════════════════════════════════════════════════════════════");
    }

    protected void displayFooter(){
        System.out.println("═════════════════════════════════════════════════════════════════"); 
    }
    
    // Common method for displaying menu title
    protected void displayTitle(String title) {
        displayBorder();
        System.out.printf("%-22s %-40s %-10s", "║", title, "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════");
    }
    
    // Common method for displaying menu item
    protected void displayMenuItem(int number, String text) {
        System.out.printf("%-22s %-40s %-1s", "║", number + ". " + text, "║\n");
    }
    
    // Common method for handling menu loop
    public void run() {
        while (isLoggedIn) {
            displayMenu();
            try {
                String input = sc.nextLine();  // Use simple nextLine()
                int choice = Integer.parseInt(input.trim());
                handleOption(choice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
    
    // Common method for logout
    protected void logout() {
        isLoggedIn = false;
        System.out.println("Logged out successfully.");
    }
} 