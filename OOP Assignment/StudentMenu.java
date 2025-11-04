import java.util.Map;
import java.util.Scanner;

public class StudentMenu extends Menu {
    private Student student;
    private Map<String, Double> gradePoints;
    
    public StudentMenu(Scanner sc, Student student, Map<String, Double> gradePoints) {
        super(sc);
        this.student = student;
        this.gradePoints = gradePoints;
    }
    
    @Override
    public void displayMenu() {
        displayTitle("Student Menu");
        displayMenuItem(1, "View Exam Timetable");
        displayMenuItem(2, "View Exam Slip");
        displayMenuItem(3, "View Result Slip");
        displayMenuItem(4, "View Registered Courses");
        displayMenuItem(5, "Logout");
        displayFooter();
        System.out.print("Please Select Your Option: ");
    }
    
    @Override
    public void handleOption(int choice) {
        switch (choice) {
            case 1:
                System.out.println();
                if (student.getId().equals("2411298")) {
                    System.out.println(ExamDemo.getFOCSTimetable());
                } else if (student.getId().equals("2402401")) {
                    System.out.println(ExamDemo.getFOETTimetable());
                } else if (student.getId().equals("2402305")) {
                    System.out.println(ExamDemo.getFAFBTimetable());
                } else if (student.getId().equals("2402276")) {
                    System.out.println(ExamDemo.getFSSHTimetable());
                }
                System.out.println("Press ENTER to continue...");
                sc.nextLine();
                break;
                
            case 2:
                Student.getExamSlips(student);
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine();
                break;
                
            case 3:
                System.out.println();
                // Load the latest student data from file before displaying result slip
                Student updatedStudent = Student.loadFromFile(student.getId());
                if (updatedStudent != null) {
                    Student.getResultSlips(updatedStudent, gradePoints);
                } else {
                    Student.getResultSlips(student, gradePoints);
                }
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine();
                break;
                
            case 4:
                CourseManager.displayStudentCourses(student);
                System.out.println("\nPress ENTER to continue...");
                sc.nextLine();
                break;
                
            case 5:
                logout();
                break;
                
            default:
                System.out.println("Invalid input. Please try again");
        }
    }
} 