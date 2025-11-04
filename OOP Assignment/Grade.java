public class Grade {
    private String courseID;
    private double marks;
    private String letterGrade;

    public Grade() {

    }

    public Grade(String courseID, double marks, String letterGrade) {
        this.courseID = courseID;
        this.marks = marks;
        this.letterGrade = letterGrade;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public double getMarks() {
        return marks;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public String calculateLetterGrade(double marks) {
        if (marks >= 90) return "A";
        if (marks >= 80) return "B";
        if (marks >= 70) return "C";
        if (marks >= 60) return "D";  // Added D grade for 60-69 range
        return "F";
    }
    
}
