public class Course {
    private String courseCode;
    private String courseName;
    private String faculty;
    private int creditHours;

    public Course(String courseCode, String courseName, String faculty, int creditHours) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.faculty = faculty;
        this.creditHours = creditHours;
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public String getFaculty() { return faculty; }
    public int getCreditHours() { return creditHours; }
}