import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Student extends User {
    private int yearEnrolled;
    private double gpa;
    private double cgpa;
    private int creditHours;
    private String programme;
    
    private List<String> subjectCodes = new ArrayList<>();
    private List<String> subjects = new ArrayList<>();
    private List<Double> marksList = new ArrayList<>(); // To store marks for each subject
    private List<String> grades = new ArrayList<>();   // To store grades for each subject
    private List<Integer> creditsList = new ArrayList<>();

    public Student(){
        super();
        this.yearEnrolled = 0;
        this.gpa = 0.0;
        this.cgpa = 0.0;
        this.creditHours = 0;
    }
    
    public Student(String id, String name, String contactNumber, String faculty, int yearEnrolled, double gpa, double cgpa, int creditHours, String programme) {
        super(id, name, contactNumber, faculty);
        this.yearEnrolled = yearEnrolled;
        this.gpa = gpa;
        this.cgpa = cgpa;
        this.creditHours = creditHours;
        this.programme = programme;
    }

    public void setYearEnrolled(int yearEnrolled) {
        this.yearEnrolled = yearEnrolled;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getProgramme() {
        return programme;
    }

    public void addSubjectWithMarksAndCredits(String subjectCode, String subject, double marks, int credits) {
        this.subjectCodes.add(subjectCode);
        this.subjects.add(subject);
        this.marksList.add(marks);
        this.grades.add(calculateGrade(marks));
        this.creditsList.add(credits);
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public List<Double> getMarksList() {
        return marksList;
    }

    public List<String> getGrades() {
        return grades;
    }

    public List<Integer> getCreditsList() {
        return creditsList;
    }

    public List<String> getSubjectCodes() {
        if (subjectCodes == null) {
            subjectCodes = new ArrayList<>();
        }
        return subjectCodes;
    }

    private String calculateGrade(double marks) {
        if (marks >= 85) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 55) return "C";
        else if (marks >= 40) return "D";
        else return "F";
    }

    public double calculateSemesterGpa(Map<String, Double> gradePoints) {
        if (subjects.isEmpty()) {
            return 0.0;
        }

        double totalGradePoints = 0;
        int totalCredits = 0;

        for (int i = 0; i < subjects.size(); i++) {
            String grade = grades.get(i);
            int credits = creditsList.get(i);
            if (gradePoints.containsKey(grade)) {
                totalGradePoints += gradePoints.get(grade) * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits > 0) {
            return totalGradePoints / totalCredits;
        } else {
            return 0.0;
        }
    }

    public double calculateCgpa() {
        return this.cgpa;
    }

    public void displayStudentInfo() {
        String border = "═════════════════════════════════════════════════════════════════";
        System.out.println(border);
        System.out.printf("%-22s %-40s %-10s%n", "║", "STUDENT INFORMATION", "║");
        System.out.println(border);
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ STUDENT ID", ":", this.id, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ NAME", ":", this.name, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ FACULTY", ":", this.faculty, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ PROGRAMME", ":", this.programme, "║");
        System.out.printf("%-22s %-1s %-38d %-1s%n", "║ YEAR ENROLLED", ":", this.yearEnrolled, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ CONTACT NUMBER", ":", this.contactNumber, "║");
        System.out.printf("%-22s %-1s %-38.2f %-1s%n", "║ CURRENT GPA", ":", this.gpa, "║");
        System.out.printf("%-22s %-1s %-38.2f %-1s%n", "║ CGPA", ":", this.cgpa, "║");
        System.out.printf("%-22s %-1s %-38d %-1s%n", "║ CREDIT HOURS", ":", this.creditHours, "║");
        System.out.println(border);
    }

    public void displaySubjects() {
        String border = "═════════════════════════════════════════════════════════════════════════════════════";
        System.out.println(border);
        System.out.printf("%-32s %-50s %-4s%n", "║", "SUBJECTS AND GRADES", "║");
        System.out.println(border);
        System.out.printf("%-10s %-45s %-12s %-12s %-10s%n", "║ CODE", "SUBJECT", "MARKS", "GRADE", "║");
        System.out.println(border);
        for (int i = 0; i < subjects.size(); i++) {
            System.out.printf("%-10s %-45s %-12.2f %-12s %-10s%n", 
                "║ " + subjectCodes.get(i),
                subjects.get(i),
                marksList.get(i),
                grades.get(i),
                "║");
        }
        System.out.println(border);
    }

    public void updateSubjectMarks(String subjectCode, double newMarks) {
        int index = subjectCodes.indexOf(subjectCode);
        if (index != -1) {
            marksList.set(index, newMarks);
            grades.set(index, calculateGrade(newMarks));
            // Recalculate GPA
            this.gpa = calculateSemesterGpa(Map.of(
                "A", 4.0,
                "B", 3.0,
                "C", 2.0,
                "D", 1.0,
                "F", 0.0
            ));
        }
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("student_" + this.id + ".txt"))) {
            // Save student info
            writer.println(this.id);
            writer.println(this.name);
            writer.println(this.contactNumber);
            writer.println(this.faculty);
            writer.println(this.yearEnrolled);
            writer.println(this.gpa);
            writer.println(this.cgpa);
            writer.println(this.creditHours);
            writer.println(this.programme);

            // Save subjects info
            writer.println(subjectCodes.size());
            for (int i = 0; i < subjectCodes.size(); i++) {
                writer.println(subjectCodes.get(i));
                writer.println(subjects.get(i));
                writer.println(marksList.get(i));
                writer.println(grades.get(i));
                writer.println(creditsList.get(i));
            }
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    public static Student loadFromFile(String studentId) {
        try (Scanner scanner = new Scanner(new File("student_" + studentId + ".txt"))) {
            // Load student info
            String id = scanner.nextLine();
            String name = scanner.nextLine();
            String contactNumber = scanner.nextLine();
            String faculty = scanner.nextLine();
            int yearEnrolled = Integer.parseInt(scanner.nextLine());
            double gpa = Double.parseDouble(scanner.nextLine());
            double cgpa = Double.parseDouble(scanner.nextLine());
            int creditHours = Integer.parseInt(scanner.nextLine());
            String programme = scanner.nextLine();

            Student student = new Student(id, name, contactNumber, faculty, yearEnrolled, gpa, cgpa, creditHours, programme);

            // Load subjects info
            int numSubjects = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numSubjects; i++) {
                String subjectCode = scanner.nextLine();
                String subject = scanner.nextLine();
                double marks = Double.parseDouble(scanner.nextLine());
                String grade = scanner.nextLine();
                int credits = Integer.parseInt(scanner.nextLine());

                student.subjectCodes.add(subjectCode);
                student.subjects.add(subject);
                student.marksList.add(marks);
                student.grades.add(grade);
                student.creditsList.add(credits);
            }

            return student;
        } catch (FileNotFoundException e) {
            System.out.println("Student data file not found: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Error loading student data: " + e.getMessage());
            return null;
        }
    }

    public static void getResultSlips(Student student, Map<String, Double> gradePoints) {
        String border = "═════════════════════════════════════════════════════════════════════════════════════";
        System.out.println(border);
        System.out.printf("%-32s %-50s %-21s%n", "║", "RESULT SLIP", "║");
        System.out.println(border);
        System.out.printf("%-22s %-1s %-58s %-1s%n", "║ FACULTY", ":", student.faculty, "║");
        System.out.printf("%-22s %-1s %-58s %-1s%n", "║ PROGRAMME", ":", student.programme, "║");
        System.out.printf("%-22s %-1s %-58s %-1s%n", "║ NAME", ":", student.name, "║");
        System.out.printf("%-22s %-1s %-58s %-1s%n", "║ STUDENT ID", ":", student.id, "║");
        System.out.println(border);

        if (!student.getSubjects().isEmpty()) {
            System.out.printf("%-10s %-50s %-10s %10s %-8s%n", "║ CODE", "SUBJECT", "MARKS", "GRADE", "║");
            System.out.println(border);
            for (int i = 0; i < student.getSubjects().size(); i++) {
                System.out.printf("%-10s %-50s %-10s %10s %-8s%n", 
                    "║ " + student.subjectCodes.get(i),
                    student.subjects.get(i),
                    student.marksList.get(i),
                    student.grades.get(i),
                    "║");
            }
            System.out.println(border);
            double semesterGpa = student.calculateSemesterGpa(gradePoints);
            double cgpa = student.calculateCgpa();

            System.out.printf("%-61s %-15s %-5.2f %-10s%n", "║", "SEMESTER GPA", semesterGpa, "║");
            System.out.printf("%-61s %-15s %-5.2f %-10s%n", "║", "CGPA", cgpa, "║");
            System.out.println(border);
        } else {
            System.out.println("║ No subjects enrolled for this student.                                  ║");
            System.out.println(border);
        }
    }

    public static void getExamSlips(Student student) {
        String border = "═════════════════════════════════════════════════════════════════";
        System.out.println(border);
        System.out.printf("%-22s %-40s %-10s%n", "║", "EXAMINATION SLIP", "║");
        System.out.println(border);
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ FACULTY", ":", student.faculty, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ CAMPUS", ":", "KUALA LUMPUR CAMPUS", "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ PROGRAMME", ":", student.programme, "║");
        System.out.printf("%-22s %-1s %-38d %-1s%n", "║ YEAR OF STUDY", ":", (2025 - student.yearEnrolled), "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ NAME", ":", student.name, "║");
        System.out.printf("%-22s %-1s %-38s %-1s%n", "║ STUDENT ID", ":", student.id, "║");
        System.out.println(border);
    }

    public String getFaculty() {
        return faculty;
    }
}