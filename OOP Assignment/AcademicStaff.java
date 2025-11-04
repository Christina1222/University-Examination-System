import java.util.List;
import java.util.Map;

public class AcademicStaff extends User {
    private String faculty;
    private String joiningDate;
    private String position;

    public AcademicStaff(String id, String name, String contactNumber, String faculty, String joiningDate, String position) {
        super(id, name, contactNumber, faculty);
        this.faculty = faculty;
        this.joiningDate = joiningDate;
        this.position = position;
    }

    public void displayStaffInfo() {
        System.out.println("---------------------------------------------------------");
        System.out.printf("%-20s %-34s %-10s", "|", "Staff Information", "|");
        System.out.println("\n---------------------------------------------------------");
        System.out.printf("%-22s %-1s %-30s %-1s\n", "| STAFF ID" ,":", id, "|");
        System.out.printf("%-22s %-1s %-30s %-1s\n", "| NAME: ",":", name, "|");
        System.out.printf("%-22s %-1s %-30s %-1s\n", "| EMAIL: ", ":", contactNumber, "|");
        System.out.printf("%-22s %-1s %-30s %-1s\n", "| FACULTY: ", ":", faculty, "|");
        System.out.printf("%-22s %-1s %-30s %-1s\n", "| POSITION: ",":", position, "|");
        System.out.printf("%-22s %-1s %-30s %-1s\n", "| JOINING DATE: ",":", joiningDate, "|");
        System.out.println("---------------------------------------------------------");    
    }

    public String getFaculty() {
        return faculty;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getPosition() {
        return position;
    }

    public String getEmail() {
        return contactNumber; // Using contactNumber as email in this case
    }

    // Helper method to calculate grade
    private String calculateGrade(double marks) {
        if (marks >= 85) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 55) return "C";
        else if (marks >= 40) return "D";
        else return "F";
    }

    public void updateStudentGrade(Student student, String subjectName, double newMarks) {
    // Get the lists from student
    List<String> subjects = student.getSubjects();
    List<Double> marksList = student.getMarksList();
    List<String> grades = student.getGrades();
    
    boolean found = false;
    for (int i = 0; i < subjects.size(); i++) {
        if (subjects.get(i).equalsIgnoreCase(subjectName)) {
            // Update marks and recalculate grade
            marksList.set(i, newMarks);
            grades.set(i, calculateGrade(newMarks));
            found = true;
            
            // Recalculate GPA
            Map<String, Double> gradePoints = Map.of(
                "A", 4.0, "B", 3.0, "C", 2.0, "D", 1.0, "F", 0.0
            );
            double newGPA = student.calculateSemesterGpa(gradePoints);
            student.setGpa(newGPA);
            
            System.out.println("\nSuccessfully updated grade for " + subjectName + 
                             " to marks: " + newMarks + " (Grade: " + calculateGrade(newMarks) + ")");
            System.out.println("Student's updated GPA: " + String.format("%.2f", newGPA));
            break;
        }
    }
    
    if (!found) {
        System.out.println("\nError: Subject '" + subjectName + "' not found for student " + student.getName());
    }
}

public void viewStudentResult(Student student) {
    Map<String, Double> gradePoints = Map.of(
        "A", 4.0, "B", 3.0, "C", 2.0, "D", 1.0, "F", 0.0
    );
    Student.getResultSlips(student, gradePoints);
}

}
