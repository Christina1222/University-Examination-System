import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    private static String readPassword() {
        StringBuilder password = new StringBuilder();
        try {
            // Get the terminal
            System.console().printf("Please enter your password: ");
            char[] passwordChars = System.console().readPassword();
            return new String(passwordChars);
        } catch (Exception e) {
            // Fallback method if console is not available
            Scanner scanner = new Scanner(System.in);
            String pass = scanner.nextLine();
            // Clear the line and print asterisks
            for (int i = 0; i < pass.length(); i++) {
                System.out.print("*");
            }
            System.out.println();
            return pass;
        }
    }

    private static String readInput() {
        return sc.nextLine(); // Read regular input normally
    }

    public static void main(String[] args) {
        int num = 0;

        Map<String, Double> gradePoints = Map.of(
            "A", 4.0,
            "B", 3.0,
            "C", 2.0,
            "D", 1.0,
            "F", 0.0
        );

        // Initialize students
        Student student1 = new Student("2411298", "Dominic", "011-1234567", "Faculty of Computer Science", 2021, 3.5, 3.4, 120, "Diploma in Information Technology");
        student1.addSubjectWithMarksAndCredits("CS101", "Introduction to Interface Design", 78.5, 3);
        student1.addSubjectWithMarksAndCredits("CS102", "Software Development Fundamentals", 65.0, 4);
        student1.addSubjectWithMarksAndCredits("MA101", "Calculus and Algebra", 82.0, 3);
        student1.addSubjectWithMarksAndCredits("CS103", "Introduction to Cybersecurity", 59.5, 3);
        student1.addSubjectWithMarksAndCredits("CS104", "Database Development and Applications", 71.0, 3);

        Student student2 = new Student("2402401", "Alice", "012-9876543", "Faculty of Engineering", 2022, 3.8, 3.3, 90, "Bachelor of Electrical Engineering");
        student2.addSubjectWithMarksAndCredits("EE101", "Electrical Machines", 88.0, 4);
        student2.addSubjectWithMarksAndCredits("EE102", "Microprocessors and Microcontrollers", 76.5, 3);
        student2.addSubjectWithMarksAndCredits("EE103", "Control Systems", 92.0, 4);
        student2.addSubjectWithMarksAndCredits("EE104", "Power Systems", 80.0, 3);
        student2.addSubjectWithMarksAndCredits("EE105", "Signals and Systems", 70.5, 3);

        Student student3 = new Student("2402305", "Bob", "013-4567890", "Faculty of Business", 2020, 3.8, 3.7, 150, "Bachelor of Business Administration");
        student3.addSubjectWithMarksAndCredits("BA101", "Organizational Behavior", 90.0, 3);
        student3.addSubjectWithMarksAndCredits("BA102", "International Business", 85.5, 3);
        student3.addSubjectWithMarksAndCredits("BA103", "Managerial Accounting", 78.0, 4);
        student3.addSubjectWithMarksAndCredits("BA104", "Corporate Finance", 82.5, 3);
        student3.addSubjectWithMarksAndCredits("BA105", "Quantitative Studies", 75.0, 3);

        Student student4 = new Student("2402276", "Charlie", "014-1122334", "Faculty of Arts", 2023, 3.0, 3.1, 60, "Bachelor of Arts in Psychology");
        student4.addSubjectWithMarksAndCredits("PS101", "Abnormal Psychology", 72.0, 3);
        student4.addSubjectWithMarksAndCredits("PS102", "Personality Theories", 68.5, 3);
        student4.addSubjectWithMarksAndCredits("PS103", "Biopsychology", 79.0, 3);
        student4.addSubjectWithMarksAndCredits("PS104", "Psychological Assessment", 84.5, 3);
        student4.addSubjectWithMarksAndCredits("PS105", "Experimental Psychology", 70.0, 4);

        // Initialize academic staff
        AcademicStaff staff1 = new AcademicStaff("S1001", "Dr. Alice Smith", "alice.smith@tarc.edu.com", "Faculty of Computer Science", "2020-01-15", "Senior Lecturer");
        AcademicStaff staff2 = new AcademicStaff("S2002", "Prof. Bob Johnson", "bob.johnson@tarc.edu.com", "Faculty of Engineering", "2018-05-20", "Professor");
        AcademicStaff staff3 = new AcademicStaff("S3003", "Dr. Carol Williams", "carol.williams@tarc.edu.com", "Faculty of Business", "2022-09-01", "Lecturer");
        AcademicStaff staff4 = new AcademicStaff("S4004", "Prof. David Brown", "david.brown@tarc.edu.com", "Faculty of Arts", "2019-11-10", "Associate Professor");

        // Create students map
        Map<String, Student> studentsMap = new HashMap<>();
        
        // Try to load students from files
        Student loadedStudent1 = Student.loadFromFile("2411298");
        Student loadedStudent2 = Student.loadFromFile("2402401");
        Student loadedStudent3 = Student.loadFromFile("2402305");
        Student loadedStudent4 = Student.loadFromFile("2402276");

        // If loaded successfully, use loaded data; otherwise, use default data
        if (loadedStudent1 != null) {
            studentsMap.put("2411298", loadedStudent1);
        } else {
            studentsMap.put("2411298", student1);
            student1.saveToFile(); // Save default data
        }

        if (loadedStudent2 != null) {
            studentsMap.put("2402401", loadedStudent2);
        } else {
            studentsMap.put("2402401", student2);
            student2.saveToFile(); // Save default data
        }

        if (loadedStudent3 != null) {
            studentsMap.put("2402305", loadedStudent3);
        } else {
            studentsMap.put("2402305", student3);
            student3.saveToFile(); // Save default data
        }

        if (loadedStudent4 != null) {
            studentsMap.put("2402276", loadedStudent4);
        } else {
            studentsMap.put("2402276", student4);
            student4.saveToFile(); // Save default data
        }

        TARLogo.display();

        do {
            System.out.println("═════════════════════════════════════════════════════════════════");
            System.out.printf("%-22s %-40s %-10s", "║", "Welcome to TARUMT", "║");
            System.out.println("\n═════════════════════════════════════════════════════════════════");
            System.out.printf("%-22s %-40s %-10s", "║", "1. Login as Student", "║");
            System.out.printf("\n%-22s %-40s %-10s", "║", "2. Login as Staff", "║");
            System.out.printf("\n%-22s %-40s %-10s", "║", "3. Login as Admin", "║");
            System.out.printf("\n%-22s %-40s %-10s", "║", "4. Exit", "║");
            System.out.println("\n═════════════════════════════════════════════════════════════════");
            System.out.print("Please select Your Option: ");
            
            try {
                num = Integer.parseInt(readInput());
                
                switch (num) {
                    case 1:
                        boolean validStudentID = false;
                        String studentID = "";
                        while (!validStudentID) {
                            System.out.println("═════════════════════════════════════════════════════════════════");
                            System.out.println("Enter 0 to return to Main Menu");
                            System.out.print("Enter student ID: ");
                            studentID = readInput();  // Normal visible input
                            
                            if (studentID.equals("0")) {
                                break;  // Return to main menu
                            }
                            
                            if (studentID.length() != 7) {
                                System.out.println("--------------------------------------------------");
                                System.out.println("|Error: Student ID must be exactly 7 digits.     |");
                                System.out.println("--------------------------------------------------");
                                continue;
                            }
                            
                            if (studentID.matches("^[0-9]+$")) {
                                validStudentID = true;
                            } else {
                                System.out.println("--------------------------------------------------");
                                System.out.println("|Error: Student ID must contain only numbers.    |");
                                System.out.println("--------------------------------------------------");
                            }
                        }
                        
                        if (studentID.equals("0")) {
                            continue;  // Go back to main menu
                        }
                        
                        boolean validPassword = false;
                        while (!validPassword) {
                            String passwordInput = readPassword();  // This will handle the masking

                            if (passwordInput.equals("abc123")) {
                                validPassword = true;
                            } else {
                                System.out.println("--------------------------------------------------");
                                System.out.println("|Error: Invalid Password                         |");
                                System.out.println("--------------------------------------------------");
                            }
                        }
        
                        // Get logged in student
                        Student loggedInStudent = null;
                        if (studentID.equalsIgnoreCase("2411298")) {
                            loggedInStudent = student1;
                        } else if (studentID.equalsIgnoreCase("2402401")) {
                            loggedInStudent = student2;
                        } else if (studentID.equalsIgnoreCase("2402305")) {
                            loggedInStudent = student3;
                        } else if (studentID.equalsIgnoreCase("2402276")) {
                            loggedInStudent = student4;
                        }

                        if (loggedInStudent != null) {
                            // Only show login successful if student exists
                            System.out.println("═════════════════════════════════════════════════════════════════");
                            System.out.printf("%-23s %-39s %-10s", "║", "Login Successful", "║");
                            System.out.println("\n═════════════════════════════════════════════════════════════════");
                            
                            StudentMenu studentMenu = new StudentMenu(sc, loggedInStudent, gradePoints);
                            studentMenu.run();
                        } else {
                            System.out.println("--------------------------------------------------");
                            System.out.println("|Error: Student not found.                       |");
                            System.out.println("--------------------------------------------------");
                        }
                        break;
        
                    case 2:
                        boolean validStaffID = false;
                        String staffID = "";
                        while (!validStaffID) {
                            System.out.println("═════════════════════════════════════════════════════════════════");
                            System.out.println("Enter 0 to return to Main Menu");
                            System.out.print("Please enter your Staff ID: ");
                            staffID = readInput();  // Normal visible input
                            
                            if (staffID.equals("0")) {
                                break;  // Return to main menu
                            }
                            
                            if (staffID.equalsIgnoreCase("S1001") || staffID.equalsIgnoreCase("S2002") || 
                                staffID.equalsIgnoreCase("S3003") || staffID.equalsIgnoreCase("S4004")) {
                                validStaffID = true;
                            } else {
                                System.out.println("-----------------------------");
                                System.out.println("| Error: Staff ID not found |");
                                System.out.println("-----------------------------");
                            }
                        }
                        
                        if (staffID.equals("0")) {
                            continue;  // Go back to main menu
                        }
                        
                        boolean validStaffPassword = false;
                        while (!validStaffPassword) {
                            String staffPasswordInput = readPassword();  // This will handle the masking
                            
                            if (staffPasswordInput.equals("123456")) {
                                validStaffPassword = true;
                            } else {
                                System.out.println("--------------------------");
                                System.out.println("|Error: Invalid Password |");
                                System.out.println("--------------------------");
                            }
                        }
        
                        // Show login success message
                        System.out.println("═════════════════════════════════════════════════════════════════");
                        System.out.printf("%-23s %-39s %-10s", "║", "Login Successful", "║");
                        System.out.println("\n═════════════════════════════════════════════════════════════════");
                        
                        // Get logged in staff
                        AcademicStaff loggedInStaff = null;
                        if (staffID.equalsIgnoreCase("S1001")) {
                            loggedInStaff = staff1;
                        } else if (staffID.equalsIgnoreCase("S2002")) {
                            loggedInStaff = staff2;
                        } else if (staffID.equalsIgnoreCase("S3003")) {
                            loggedInStaff = staff3;
                        } else if (staffID.equalsIgnoreCase("S4004")) {
                            loggedInStaff = staff4;
                        }

                        if (loggedInStaff != null) {
                            StaffMenu staffMenu = new StaffMenu(sc, loggedInStaff, gradePoints, studentsMap);
                            staffMenu.run();
                        } else {
                            System.out.println("Error: Staff not found.");
                        }
                        break;
        
                    case 3:
                        boolean validAdminID = false;
                        String adminID = "";
                        while (!validAdminID) {
                            System.out.println("═════════════════════════════════════════════════════════════════");
                            System.out.println("Enter 0 to return to Main Menu");
                            System.out.print("Please enter your Admin ID: ");
                            adminID = readInput();  // Normal visible input
                            
                            if (adminID.equals("0")) {
                                break;  // Return to main menu
                            }
                            
                            if (adminID.equalsIgnoreCase("A1001")) {
                                validAdminID = true;
                            } else {
                                System.out.println("-----------------------------");
                                System.out.println("| Error: Admin ID not found |");
                                System.out.println("-----------------------------");
                            }
                        }
                        
                        if (adminID.equals("0")) {
                            continue;  // Go back to main menu
                        }
                        
                        boolean validAdminPassword = false;
                        while (!validAdminPassword) {
                            String adminPasswordInput = readPassword();  // This will handle the masking
                            
                            if (adminPasswordInput.equals("admin123")) {
                                validAdminPassword = true;
                            } else {
                                System.out.println("--------------------------");
                                System.out.println("|Error: Invalid Password |");
                                System.out.println("--------------------------");
                            }
                        }
                        
                        // Show login success message
                        System.out.println("═════════════════════════════════════════════════════════════════");
                        System.out.printf("%-23s %-39s %-10s", "║", "Login Successful", "║");
                        System.out.println("\n═════════════════════════════════════════════════════════════════");
                        
                        // Create admin object
                        Admin admin = new Admin("A1001", "Admin User", "admin@tarc.edu.com", "Administration", "System Admin", studentsMap, gradePoints);
                        
                        // Create staff map
                        Map<String, AcademicStaff> staffMap = new HashMap<>();
                        staffMap.put("S1001", staff1);
                        staffMap.put("S2002", staff2);
                        staffMap.put("S3003", staff3);
                        staffMap.put("S4004", staff4);
                        
                        // Create and run admin menu
                        AdminMenu adminMenu = new AdminMenu(sc, admin, studentsMap, gradePoints, staffMap);
                        adminMenu.run();
                        break;
        
                    case 4:
                        System.out.println("Thank You for accessing TARUMT University and Grading System.");
                        System.exit(0);
                        break;
        
                    default:
                        System.out.println("Invalid input. Please try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (true);
    }
}