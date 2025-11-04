import java.util.Map;
import java.util.Scanner;

public class Admin extends User {
    private String adminID;
    private String position;
    private Map<String, Student> students;
    private Map<String, Double> gradePoints;

    public Admin(String adminID, String name, String email, String department, String position, 
                Map<String, Student> students, Map<String, Double> gradePoints) {
        super(name, email, department, "Admin");
        this.adminID = adminID;
        this.position = position;
        this.students = students;
        this.gradePoints = gradePoints;
    }

    public void updateStudentGrade(Scanner sc) {
        System.out.print("Enter student ID: ");
        String studentID = sc.nextLine().trim();
        
        // Validate student ID is not empty
        while (studentID.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("|Error: Student ID cannot be empty.        |");
            System.out.println("--------------------------------------------");
            System.out.print("Please enter student ID again: ");
            studentID = sc.nextLine().trim();
        }
        
        Student student = students.get(studentID);
        if (student == null) {
            System.out.println("--------------------------------------------");
            System.out.println("|Error: Student with ID " + studentID + " not found.|");
            System.out.println("--------------------------------------------");
            return;
        }

        System.out.println("\nCurrent Subjects and Grades:");
        System.out.println("---------------------------");
        student.displaySubjects();

        System.out.print("\nEnter subject code to update: ");
        String subjectCode = sc.nextLine().trim();
        
        // Validate subject code is not empty
        if (subjectCode.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("|Error: Subject code cannot be empty.      |");
            System.out.println("--------------------------------------------");
            return;
        }

        // Check if subject exists in student's result slip
        if (!student.getSubjectCodes().contains(subjectCode)) {
            System.out.println("\nError: Subject code '" + subjectCode + "' not found in student's result slip");
            System.out.println("Please check the subject code and try again.");
            return;
        }

        System.out.print("Enter new marks: ");
        String marksInput = sc.nextLine().trim();
        
        // Validate marks input is not empty
        if (marksInput.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println("|Error: Marks cannot be empty.             |");
            System.out.println("--------------------------------------------");
            return;
        }
        
        try {
            double newMarks = Double.parseDouble(marksInput);
            // Validate marks
            if (newMarks < 0 || newMarks > 100) {
                System.out.println("\nError: Marks must be between 0 and 100");
                System.out.println("Please enter valid marks and try again.");
                return;
            }

            // Update the subject marks
            student.updateSubjectMarks(subjectCode, newMarks);

            System.out.println("\nSuccessfully updated grade for " + subjectCode + 
                             " to marks: " + newMarks + " (Grade: " + student.getGrades().get(student.getSubjectCodes().indexOf(subjectCode)) + ")");
            System.out.println("Student's updated GPA: " + String.format("%.2f", student.getGpa()));
            
            // Save the updated student information to file
            student.saveToFile();
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks. Please enter a valid number.");
        }
    }

    public void addNewStudent(Scanner sc) {
        System.out.print("Enter student ID (7 digits): ");
        String studentID = sc.nextLine().trim();
        
        // Validate student ID is not empty and is exactly 7 digits
        while (studentID.isEmpty() || !studentID.matches("\\d{7}")) {
            if (studentID.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Student ID cannot be empty.        |");
                System.out.println("--------------------------------------------");
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Student ID must be exactly         |");
                System.out.println("|7 digits (e.g., 2402305).                |");
                System.out.println("--------------------------------------------");
            }
            System.out.print("Please enter student ID again: ");
            studentID = sc.nextLine().trim();
        }
        
        if (students.containsKey(studentID)) {
            System.out.println("--------------------------------------------");
            System.out.println("|Error: Student ID already exists.         |");
            System.out.println("--------------------------------------------");
            return;
        }

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();
        
        // Validate student name is not empty and contains only letters and spaces
        while (name.isEmpty() || !name.matches("[a-zA-Z\\s]+")) {
            if (name.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Student name cannot be empty.      |");
                System.out.println("--------------------------------------------");
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Student name must contain only     |");
                System.out.println("|letters and spaces.                       |");
                System.out.println("--------------------------------------------");
            }
            System.out.print("Please enter student name again: ");
            name = sc.nextLine().trim();
        }
        
        // Faculty selection menu
        boolean validFaculty = false;
        String faculty = "";
        while (!validFaculty) {
            System.out.println("\nSelect Faculty:");
            System.out.println("1. Faculty of Computer Science (FOCS)");
            System.out.println("2. Faculty of Engineering and Technology (FOET)");
            System.out.println("3. Faculty of Accountancy, Finance and Business (FAFB)");
            System.out.println("4. Faculty of Social Science and Humanities (FSSH)");
            System.out.print("Enter your choice (1-4): ");
            
            try {
                int facultyChoice = Integer.parseInt(sc.nextLine());
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
                        System.out.println("|Error: Please enter a number between 1-4.|");
                        System.out.println("--------------------------------------------");
                }
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Please enter a valid number.      |");
                System.out.println("--------------------------------------------");
            }
        }
        
        // Programme selection based on faculty
        boolean validProgramme = false;
        String programme = "";
        while (!validProgramme) {
            System.out.println("\nSelect Programme:");
            switch (faculty) {
                case "Faculty of Computer Science":
                    System.out.println("1. Diploma in Information Technology");
                    System.out.println("2. Diploma in Computer Science");
                    System.out.println("3. Bachelor of Software Engineering");
                    System.out.println("4. Bachelor in Data Science");
                    break;
                case "Faculty of Engineering and Technology":
                    System.out.println("1. Bachelor of Electrical Engineering");
                    System.out.println("2. Bachelor of Mechanical Engineering");
                    System.out.println("3. Bachelor of Mechatronics Engineering");
                    break;
                case "Faculty of Accountancy, Finance and Business":
                    System.out.println("1. Bachelor of Business Administration");
                    System.out.println("2. Bachelor of Commerce");
                    System.out.println("3. Bachelor in Marketing");
                    System.out.println("4. Bachelor in Entrepreneurship");
                    System.out.println("5. Bachelor of Economics");
                    break;
                case "Faculty of Social Science and Humanities":
                    System.out.println("1. Bachelor of Arts in Psychology");
                    System.out.println("2. Bachelor of Gastropreneurship");
                    System.out.println("3. Bachelor of Tourism Management");
                    break;
            }
            System.out.print("Enter your choice: ");
            
            try {
                int programmeChoice = Integer.parseInt(sc.nextLine());
                switch (faculty) {
                    case "Faculty of Computer Science":
                        switch (programmeChoice) {
                            case 1:
                                programme = "Diploma in Information Technology";
                                validProgramme = true;
                                break;
                            case 2:
                                programme = "Diploma in Computer Science";
                                validProgramme = true;
                                break;
                            case 3:
                                programme = "Bachelor of Software Engineering";
                                validProgramme = true;
                                break;
                            case 4:
                                programme = "Bachelor in Data Science";
                                validProgramme = true;
                                break;
                            default:
                                System.out.println("--------------------------------------------");
                                System.out.println("|Error: Please enter a valid choice.     |");
                                System.out.println("--------------------------------------------");
                        }
                        break;
                    case "Faculty of Engineering and Technology":
                        switch (programmeChoice) {
                            case 1:
                                programme = "Bachelor of Electrical Engineering";
                                validProgramme = true;
                                break;
                            case 2:
                                programme = "Bachelor of Mechanical Engineering";
                                validProgramme = true;
                                break;
                            case 3:
                                programme = "Bachelor of Mechatronics Engineering";
                                validProgramme = true;
                                break;
                            default:
                                System.out.println("--------------------------------------------");
                                System.out.println("|Error: Please enter a valid choice.     |");
                                System.out.println("--------------------------------------------");
                        }
                        break;
                    case "Faculty of Accountancy, Finance and Business":
                        switch (programmeChoice) {
                            case 1:
                                programme = "Bachelor of Business Administration";
                                validProgramme = true;
                                break;
                            case 2:
                                programme = "Bachelor of Commerce";
                                validProgramme = true;
                                break;
                            case 3:
                                programme = "Bachelor in Marketing";
                                validProgramme = true;
                                break;
                            case 4:
                                programme = "Bachelor in Entrepreneurship";
                                validProgramme = true;
                                break;
                            case 5:
                                programme = "Bachelor of Economics";
                                validProgramme = true;
                                break;
                            default:
                                System.out.println("--------------------------------------------");
                                System.out.println("|Error: Please enter a valid choice.     |");
                                System.out.println("--------------------------------------------");
                        }
                        break;
                    case "Faculty of Social Science and Humanities":
                        switch (programmeChoice) {
                            case 1:
                                programme = "Bachelor of Arts in Psychology";
                                validProgramme = true;
                                break;
                            case 2:
                                programme = "Bachelor of Gastropreneurship";
                                validProgramme = true;
                                break;
                            case 3:
                                programme = "Bachelor of Tourism Management";
                                validProgramme = true;
                                break;
                            default:
                                System.out.println("--------------------------------------------");
                                System.out.println("|Error: Please enter a valid choice.     |");
                                System.out.println("--------------------------------------------");
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Please enter a valid number.      |");
                System.out.println("--------------------------------------------");
            }
        }
        
        System.out.print("Enter year enrolled: ");
        String yearInput = sc.nextLine().trim();
        
        // Validate year input is not empty and is exactly 4 digits
        while (yearInput.isEmpty() || !yearInput.matches("\\d{4}")) {
            if (yearInput.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Year cannot be empty.              |");
                System.out.println("--------------------------------------------");
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Year must be exactly 4 digits.     |");
                System.out.println("--------------------------------------------");
            }
            System.out.print("Please enter year enrolled again: ");
            yearInput = sc.nextLine().trim();
        }
        
        int yearEnrolled = Integer.parseInt(yearInput);
        
        // GPA input with validation
        double gpa = 0.0;
        boolean validGPA = false;
        while (!validGPA) {
            System.out.print("Enter initial GPA (0.0 - 4.0): ");
            String gpaInput = sc.nextLine().trim();
            
            if (gpaInput.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: GPA cannot be empty.               |");
                System.out.println("--------------------------------------------");
                continue;
            }
            
            try {
                gpa = Double.parseDouble(gpaInput);
                if (gpa >= 0.0 && gpa <= 4.0) {
                    validGPA = true;
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: GPA must be between 0.0 and 4.0   |");
                    System.out.println("--------------------------------------------");
                }
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Please enter a valid number.        |");
                System.out.println("--------------------------------------------");
            }
        }
        
        // CGPA input with validation
        double cgpa = 0.0;
        boolean validCGPA = false;
        while (!validCGPA) {
            System.out.print("Enter initial CGPA (0.0 - 4.0): ");
            String cgpaInput = sc.nextLine().trim();
            
            if (cgpaInput.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: CGPA cannot be empty.              |");
                System.out.println("--------------------------------------------");
                continue;
            }
            
            try {
                cgpa = Double.parseDouble(cgpaInput);
                if (cgpa >= 0.0 && cgpa <= 4.0) {
                    validCGPA = true;
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: CGPA must be between 0.0 and 4.0  |");
                    System.out.println("--------------------------------------------");
                }
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Please enter a valid number.        |");
                System.out.println("--------------------------------------------");
            }
        }
        
        // Credit hours input with validation
        int creditHours = 0;
        boolean validCredits = false;
        while (!validCredits) {
            System.out.print("Enter credit hours: ");
            String creditsInput = sc.nextLine().trim();
            
            if (creditsInput.isEmpty()) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Credit hours cannot be empty.      |");
                System.out.println("--------------------------------------------");
                continue;
            }
            
            try {
                creditHours = Integer.parseInt(creditsInput);
                if (creditHours > 0) {
                    validCredits = true;
                } else {
                    System.out.println("--------------------------------------------");
                    System.out.println("|Error: Credit hours must be positive.     |");
                    System.out.println("--------------------------------------------");
                }
            } catch (NumberFormatException e) {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Please enter a valid number.        |");
                System.out.println("--------------------------------------------");
            }
        }
        
        // IC number input with format validation
        boolean validIC = false;
        String ic = "";
        sc.nextLine(); // Clear buffer
        while (!validIC) {
            System.out.println("\nIC Number Format: XXXXXX-XX-XXXX");
            System.out.print("Enter IC number: ");
            ic = sc.nextLine();
            
            // Validate IC format (XXXXXX-XX-XXXX)
            if (ic.matches("\\d{6}-\\d{2}-\\d{4}")) {
                validIC = true;
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("|Error: Invalid IC format.                 |");
                System.out.println("|Please follow the format: XXXXXX-XX-XXXX  |");
                System.out.println("--------------------------------------------");
            }
        }

        // Create new student
        Student newStudent = new Student(studentID, name, "", faculty, yearEnrolled, gpa, cgpa, creditHours, programme);
        students.put(studentID, newStudent);

        // Add to Information class based on faculty
        StudentInformation newInfo = new StudentInformation(
            "KL Campus",
            programme,
            studentID,
            name,
            yearEnrolled,
            ic,
            name.toLowerCase().replace(" ", ".") + "@tarc.edu.my" // Default email
        );

        // Add to appropriate faculty array
        switch (faculty) {
            case "Faculty of Computer Science":
                Information.addFOCSStudent(newInfo);
                break;
            case "Faculty of Engineering and Technology":
                Information.addFOETStudent(newInfo);
                break;
            case "Faculty of Accountancy, Finance and Business":
                Information.addFAFBStudent(newInfo);
                break;
            case "Faculty of Social Science and Humanities":
                Information.addFSSHStudent(newInfo);
                break;
        }

        System.out.println("\nNew student added successfully!");
        System.out.println("---------------------------");
    }

    public void removeStudent(Scanner sc) {
        System.out.print("Enter student ID to remove: ");
        String studentID = sc.nextLine();
        
        if (!students.containsKey(studentID)) {
            System.out.println("--------------------------------------------");
            System.out.println("|Error: Student ID not found.              |");
            System.out.println("--------------------------------------------");
            return;
        }

        // Get the student's faculty before removing
        Student studentToRemove = students.get(studentID);
        String faculty = studentToRemove.getFaculty();

        // Remove from students map
        students.remove(studentID);

        // Convert full faculty name to abbreviated name and remove from Information class
        switch (faculty) {
            case "Faculty of Computer Science":
                Information.removeFOCSStudent(studentID);
                break;
            case "Faculty of Engineering and Technology":
                Information.removeFOETStudent(studentID);
                break;
            case "Faculty of Accountancy, Finance and Business":
                Information.removeFAFBStudent(studentID);
                break;
            case "Faculty of Social Science and Humanities":
                Information.removeFSSHStudent(studentID);
                break;
        }

        System.out.println("\nStudent removed successfully!");
        System.out.println("---------------------------");
    }

    public void displayAdminInfo() {
        String border = "═════════════════════════════════════════════════════════════════";
        System.out.println(border);
        System.out.printf("%-22s %-40s %-10s%n", "║", "ADMIN INFORMATION", "║");
        System.out.println(border);
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ ADMIN ID", ":", this.adminID, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ NAME", ":", this.getName(), "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ FACULTY", ":", this.getFaculty(), "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ POSITION", ":", this.position, "║");
        System.out.println(border);
    }

    // Getters
    public String getAdminID() {
        return adminID;
    }

    public String getPosition() {
        return position;
    }
} 