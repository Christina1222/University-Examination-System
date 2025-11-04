import java.util.Map;
import java.util.Scanner;

public class StaffMenu extends Menu {
    private AcademicStaff staff;
    private Map<String, Double> gradePoints;
    private Map<String, Student> students;
    
    public StaffMenu(Scanner sc, AcademicStaff staff, Map<String, Double> gradePoints, Map<String, Student> students) {
        super(sc);
        this.staff = staff;
        this.gradePoints = gradePoints;
        this.students = students;
    }
    
    @Override
    public void displayMenu() {
        displayTitle("Staff Menu");
        displayMenuItem(1, "View Student Information");
        displayMenuItem(2, "Access Students' Grades");
        displayMenuItem(3, "Update Student Grades");
        displayMenuItem(4, "View Staff Information");
        displayMenuItem(5, "View Faculty Courses");
        displayMenuItem(6, "Logout");
        displayFooter();
        System.out.print("Please Select Your Option: ");
    }
    
    @Override
    public void handleOption(int choice) {
        switch (choice) {
            case 1:
                // Display students from staff's faculty only
                String staffFaculty = staff.getFaculty();
                
                switch (staffFaculty) {
                    case "Faculty of Computer Science":
                        Information.displayFOCSStudentInfo();
                        break;
                    case "Faculty of Engineering":
                        Information.displayFOETStudentInfo();
                        break;
                    case "Faculty of Business":
                        Information.displayFAFBStudentInfo();
                        break;
                    case "Faculty of Arts":
                        Information.displayFSSHStudentInfo();
                        break;
                }
                
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 2:
                System.out.print("Enter student ID: ");
                String studentID = sc.next();
                
                Student studentToDisplay = students.get(studentID);
                
                if (studentToDisplay != null) {
                    // Check if student is from staff's faculty
                    if (studentToDisplay.getFaculty().equals(staff.getFaculty())) {
                        System.out.println("\nStudent Grades:");
                        System.out.println("--------------");
                        Student.getResultSlips(studentToDisplay, gradePoints);
                    } else {
                        System.out.println("------------------------------------------------------------------");
                        System.out.println("|Error: You can only access grades of students                   |");
                        System.out.println("|from your own faculty (" + staff.getFaculty() + ")"+"             |");
                        System.out.println("------------------------------------------------------------------");
                    }
                } else {
                    System.out.println("--------------------------------------------------");
                    System.out.println("|Error: Student with ID " + studentID + " not found.|");
                    System.out.println("--------------------------------------------------");
                }
                
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 3:
                updateStudentGrades();
                break;
                
            case 4:
                staff.displayStaffInfo();
                System.out.println("Press ENTER to continue...");
                sc.nextLine(); // Clear buffer
                break;
                
            case 5:
                CourseManager.displayAllFacultyCourses(staff.getFaculty());
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine();
                break;
                
            case 6:
                logout();
                break;
                
            default:
                System.out.println("Invalid input. Please try again");
        }
    }

    private void updateStudentGrades() {
        System.out.print("Enter student ID: ");
        String studentID = sc.next();
        sc.nextLine(); // Clear buffer
        
        Student student = students.get(studentID);
        
        if (student == null) {
            System.out.println("--------------------------------------------------");
            System.out.println("|Error: Student with ID " + studentID + " not found.|");
            System.out.println("--------------------------------------------------");
            return;
        }
        
        // Check if student is from staff's faculty
        if (!student.getFaculty().equals(staff.getFaculty())) {
            System.out.println("------------------------------------------------------------------");
            System.out.println("|Error: You can only update grades of students                   |");
            System.out.println("|from your own faculty (" + staff.getFaculty() + ")"+"             |");
            System.out.println("------------------------------------------------------------------");
            return;
        }
        
        // Display current subjects and grades
        System.out.println("\nCurrent Subjects and Grades:");
        System.out.println("---------------------------");
        student.displaySubjects();
        
        System.out.print("\nEnter subject code to update: ");
        String subjectCode = sc.nextLine();
        
        // Check if subject exists
        if (!student.getSubjectCodes().contains(subjectCode)) {
            System.out.println("\nError: Subject code '" + subjectCode + "' not found in student's result slip");
            System.out.println("Please check the subject code and try again.");
            return;
        }
        
        System.out.print("Enter new marks: ");
        try {
            double newMarks = Double.parseDouble(sc.nextLine().trim());
            
            // Validate marks
            if (newMarks < 0 || newMarks > 100) {
                System.out.println("\nError: Marks must be between 0 and 100");
                System.out.println("Please enter valid marks and try again.");
                return;
            }
            
            // Update the subject marks
            student.updateSubjectMarks(subjectCode, newMarks);
            
            System.out.println("\nGrade updated successfully!");
            System.out.println("---------------------------");
            System.out.println("Updated Subject Details:");
            System.out.println("Subject Code: " + subjectCode);
            System.out.println("New Marks: " + newMarks);
            System.out.println("---------------------------");
            
            // Save changes to file
            student.saveToFile();
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid marks. Please enter a valid number.");
        }
        
        System.out.println("\nPress ENTER to continue...");
        sc.nextLine(); // Clear buffer
    }
} 