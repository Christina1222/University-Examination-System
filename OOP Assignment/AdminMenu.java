import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AdminMenu extends Menu {
    private Admin admin;
    private Map<String, Student> students;
    private Map<String, Double> gradePoints;
    private Map<String, AcademicStaff> staff;
    
    public AdminMenu(Scanner sc, Admin admin, Map<String, Student> students, Map<String, Double> gradePoints, Map<String, AcademicStaff> staff) {
        super(sc);
        this.admin = admin;
        this.students = students;
        this.gradePoints = gradePoints;
        this.staff = staff;
    }
    
    @Override
    public void displayMenu() {
        displayTitle("Admin Menu");
        displayMenuItem(1, "Update Student Grades");
        displayMenuItem(2, "Add New Student");
        displayMenuItem(3, "Remove Student");
        displayMenuItem(4, "View Student Information");
        displayMenuItem(5, "View Staff Information");
        displayMenuItem(6, "View Admin Information");
        displayMenuItem(7, "View All Courses");
        displayMenuItem(8, "Logout");
        displayFooter();
        System.out.print("Please Select Your Option: ");
    }
    
    @Override
    public void handleOption(int choice) {
        switch (choice) {
            case 1:
                admin.updateStudentGrade(sc);
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 2:
                admin.addNewStudent(sc);
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 3:
                admin.removeStudent(sc);
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 4:
                boolean validFaculty = false;
                int facultyChoice = 0;
                
                while (!validFaculty) {
                    System.out.println("\nSelect Faculty:");
                    System.out.println("1. Faculty of Computer Science (FOCS)");
                    System.out.println("2. Faculty of Engineering and Technology (FOET)");
                    System.out.println("3. Faculty of Accountancy, Finance and Business (FAFB)");
                    System.out.println("4. Faculty of Social Science and Humanities (FSSH)");
                    System.out.print("Enter your choice (1-4): ");
                    
                    try {
                        facultyChoice = sc.nextInt();
                        if (facultyChoice >= 1 && facultyChoice <= 4) {
                            validFaculty = true;
                        } else {
                            System.out.println("--------------------------------------------");
                            System.out.println("|Error: Please enter a number between 1-4.|");
                            System.out.println("--------------------------------------------");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("--------------------------------------------");
                        System.out.println("|Error: Please enter a valid number.      |");
                        System.out.println("--------------------------------------------");
                        sc.next(); // Clear invalid input
                    }
                }
                
                switch (facultyChoice) {
                    case 1:
                        Information.displayFOCSStudentInfo();
                        break;
                    case 2:
                        Information.displayFOETStudentInfo();
                        break;
                    case 3:
                        Information.displayFAFBStudentInfo();
                        break;
                    case 4:
                        Information.displayFSSHStudentInfo();
                        break;
                }
                
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                sc.nextLine();
                break;
                
            case 5:
                System.out.println("\n═════════════════════════════════════════════════════════════════");
                System.out.printf("%-23s %-39s %-10s", "║", "STAFF MEMBERS", "║");
                System.out.println("\n═════════════════════════════════════════════════════════════════");
                
                // Sort staff by ID before displaying
                staff.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> entry.getValue().displayStaffInfo());
                
                System.out.println("═════════════════════════════════════════════════════════════════");
                
                boolean validOption = false;
                while (!validOption) {
                    // Add options to add or delete staff
                    System.out.println("\n1. Add New Staff");
                    System.out.println("2. Delete Staff");
                    System.out.println("0. Return to Admin Menu");
                    System.out.print("Please select an option: ");
                    
                    try {
                        int staffOption = Integer.parseInt(sc.nextLine());
                        switch (staffOption) {
                            case 1:
                                addNewStaff(sc);
                                validOption = true;
                                break;
                            case 2:
                                deleteStaff(sc);
                                validOption = true;
                                break;
                            case 0:
                                validOption = true;
                                break;
                            default:
                                System.out.println("--------------------------------------------");
                                System.out.println("|Error: Please enter a valid option (0-2).|");
                                System.out.println("--------------------------------------------");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("--------------------------------------------");
                        System.out.println("|Error: Please enter a valid number.      |");
                        System.out.println("--------------------------------------------");
                    }
                }
                
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 6:
                admin.displayAdminInfo();
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 7:
                CourseManager.displayAllCourses();
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 8:
                logout();
                break;
                
            default:
                System.out.println("Invalid input. Please try again");
        }
    }
    
    private void addNewStaff(Scanner sc) {
        boolean continueAdding = true;
        while (continueAdding) {
            System.out.print("Enter staff ID (e.g., S1001) or press 0 to return to menu: ");
            String staffID = sc.nextLine().trim();
            
            if (staffID.equals("0")) {
                return;
            }
            
            // Validate staff ID format (S followed by 4 digits)
            while (staffID.isEmpty() || !staffID.matches("S\\d{4}")) {
                if (staffID.isEmpty()) {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Staff ID cannot be empty.          |");
                    System.out.println("--------------------------------------------");
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Staff ID must start with 'S'       |");
                    System.out.println("|followed by 4 digits (e.g., S1001).       |");
                    System.out.println("--------------------------------------------");
                }
                System.out.print("Please enter staff ID again or press 0 to return to menu: ");
                staffID = sc.nextLine().trim();
                if (staffID.equals("0")) {
                    return;
                }
            }
            
            if (staff.containsKey(staffID)) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Staff ID already exists.           |");
                System.out.println("--------------------------------------------");
                continue;
            }
            
            System.out.print("Enter staff name or press 0 to return to menu: ");
            String name = sc.nextLine().trim();
            
            if (name.equals("0")) {
                return;
            }
            
            // Validate staff name is not empty and contains only letters and spaces
            while (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
                if (name.isEmpty()) {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Staff name cannot be empty.        |");
                    System.out.println("--------------------------------------------");
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Staff name must contain only       |");
                    System.out.println("|letters and spaces.                       |");
                    System.out.println("--------------------------------------------");
                }
                System.out.print("Please enter staff name again or press 0 to return to menu: ");
                name = sc.nextLine().trim();
                if (name.equals("0")) {
                    return;
                }
            }
            
            // Generate email from name
            String email = name.toLowerCase().replace(" ", ".") + "@tarc.edu.my";
            System.out.println("Generated email: " + email);
            
            // Faculty selection menu
            boolean validFaculty = false;
            String faculty = "";
            while (!validFaculty) {
                System.out.println("\nSelect Faculty:");
                System.out.println("1. Faculty of Computer Science (FOCS)");
                System.out.println("2. Faculty of Engineering and Technology (FOET)");
                System.out.println("3. Faculty of Accountancy, Finance and Business (FAFB)");
                System.out.println("4. Faculty of Social Science and Humanities (FSSH)");
                System.out.println("0. Return to menu");
                System.out.print("Enter your choice (0-4): ");
                
                try {
                    int facultyChoice = Integer.parseInt(sc.nextLine());
                    if (facultyChoice == 0) {
                        return;
                    }
                    switch (facultyChoice) {
                        case 1:
                            faculty = "Faculty of Computer Science";
                            validFaculty = true;
                            break;
                        case 2:
                            faculty = "Faculty of Engineering and Technology";
                            validFaculty = true;
                            break;
                        case 3:
                            faculty = "Faculty of Accountancy, Finance and Business";
                            validFaculty = true;
                            break;
                        case 4:
                            faculty = "Faculty of Social Science and Humanities";
                            validFaculty = true;
                            break;
                        default:
                            System.out.println("--------------------------------------------");
                            System.out.println("|Error: Please enter a number between 0-4.|");
                            System.out.println("--------------------------------------------");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Please enter a valid number.      |");
                    System.out.println("--------------------------------------------");
                }
            }
            
            System.out.print("Enter joining date (YYYY-MM-DD) or press 0 to return to menu: ");
            String joiningDate = sc.nextLine().trim();
            
            if (joiningDate.equals("0")) {
                return;
            }
            
            // Validate joining date format (YYYY-MM-DD)
            while (joiningDate.isEmpty() || !joiningDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                if (joiningDate.isEmpty()) {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Joining date cannot be empty.      |");
                    System.out.println("--------------------------------------------");
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Joining date must be in            |");
                    System.out.println("|YYYY-MM-DD format (e.g., 2024-01-01).     |");
                    System.out.println("--------------------------------------------");
                }
                System.out.print("Please enter joining date again or press 0 to return to menu: ");
                joiningDate = sc.nextLine().trim();
                if (joiningDate.equals("0")) {
                    return;
                }
            }
            
            // Position selection menu
            boolean validPosition = false;
            String position = "";
            while (!validPosition) {
                System.out.println("\nSelect Position:");
                System.out.println("1. Lecturer");
                System.out.println("2. Senior Lecturer");
                System.out.println("3. Associate Professor");
                System.out.println("4. Professor");
                System.out.println("0. Return to menu");
                System.out.print("Enter your choice (0-4): ");
                
                try {
                    int positionChoice = Integer.parseInt(sc.nextLine());
                    if (positionChoice == 0) {
                        return;
                    }
                    switch (positionChoice) {
                        case 1:
                            position = "Lecturer";
                            validPosition = true;
                            break;
                        case 2:
                            position = "Senior Lecturer";
                            validPosition = true;
                            break;
                        case 3:
                            position = "Associate Professor";
                            validPosition = true;
                            break;
                        case 4:
                            position = "Professor";
                            validPosition = true;
                            break;
                        default:
                            System.out.println("--------------------------------------------");
                            System.out.println("|Error: Please enter a number between 0-4.|");
                            System.out.println("--------------------------------------------");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Please enter a valid number.      |");
                    System.out.println("--------------------------------------------");
                }
            }
            
            // Create new staff member
            AcademicStaff newStaff = new AcademicStaff(staffID, name, email, faculty, joiningDate, position);
            staff.put(staffID, newStaff);
            
            System.out.println("\nNew staff member added successfully!");
            System.out.println("--------------------------------------------");
            continueAdding = false;
        }
    }
    
    private void deleteStaff(Scanner sc) {
        boolean continueDeleting = true;
        while (continueDeleting) {
            System.out.print("Enter staff ID to delete or press 0 to return to menu: ");
            String staffID = sc.nextLine().trim();
            
            if (staffID.equals("0")) {
                return;
            }
            
            // Validate staff ID is not empty
            while (staffID.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Staff ID cannot be empty.          |");
                System.out.println("--------------------------------------------");
                System.out.print("Please enter staff ID again or press 0 to return to menu: ");
                staffID = sc.nextLine().trim();
                if (staffID.equals("0")) {
                    return;
                }
            }
            
            if (!staff.containsKey(staffID)) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Staff ID not found.                |");
                System.out.println("--------------------------------------------");
                continue;
            }
            
            // Remove staff member
            staff.remove(staffID);
            
            System.out.println("\nStaff member removed successfully!");
            System.out.println("--------------------------------------------");
            continueDeleting = false;
        }
    }
} 