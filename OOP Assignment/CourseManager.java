import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManager {
    private static Map<String, List<Course>> allFacultyCourses = new HashMap<>();
    private static Map<String, List<String>> studentEnrollments = new HashMap<>(); // studentID -> list of courseIDs
    private static Map<String, List<String>> facultyCourses = new HashMap<>(); // facultyID -> list of courseIDs
    private static Map<String, List<String>> facultyStudents = new HashMap<>(); // facultyID -> list of studentIDs
    private static Map<String, String> staffFaculty = new HashMap<>(); // staffID -> facultyID
    private static Map<String, AcademicStaff> staffDetails = new HashMap<>(); // staffID -> AcademicStaff object

    static {
        initializeCourses();
    }

    private static void initializeCourses() {
        // Initialize FOCS courses
        List<Course> focsCourses = new ArrayList<>();
        focsCourses.add(new Course("CS101", "Introduction to Interface Design", "Faculty of Computer Science", 3));
        focsCourses.add(new Course("CS102", "Software Development Fundamentals", "Faculty of Computer Science", 4));
        focsCourses.add(new Course("CS103", "Introduction to Cybersecurity", "Faculty of Computer Science", 3));
        focsCourses.add(new Course("CS104", "Database Development and Applications", "Faculty of Computer Science", 3));
        focsCourses.add(new Course("MA101", "Calculus and Algebra", "Faculty of Computer Science", 3));
        
        // Initialize Engineering courses
        List<Course> foetCourses = new ArrayList<>();
        foetCourses.add(new Course("EE101", "Electrical Machines", "Faculty of Engineering", 4));
        foetCourses.add(new Course("EE102", "Microprocessors and Microcontrollers", "Faculty of Engineering", 3));
        foetCourses.add(new Course("EE103", "Control Systems", "Faculty of Engineering", 4));
        foetCourses.add(new Course("EE104", "Power Systems", "Faculty of Engineering", 3));
        foetCourses.add(new Course("EE105", "Signals and Systems", "Faculty of Engineering", 3));

        // Initialize Business courses
        List<Course> fafbCourses = new ArrayList<>();
        fafbCourses.add(new Course("BA101", "Organizational Behavior", "Faculty of Business", 3));
        fafbCourses.add(new Course("BA102", "International Business", "Faculty of Business", 3));
        fafbCourses.add(new Course("BA103", "Managerial Accounting", "Faculty of Business", 4));
        fafbCourses.add(new Course("BA104", "Corporate Finance", "Faculty of Business", 3));
        fafbCourses.add(new Course("BA105", "Quantitative Studies", "Faculty of Business", 3));

        // Initialize Arts courses
        List<Course> fsshCourses = new ArrayList<>();
        fsshCourses.add(new Course("PS101", "Abnormal Psychology", "Faculty of Arts", 3));
        fsshCourses.add(new Course("PS102", "Personality Theories", "Faculty of Arts", 3));
        fsshCourses.add(new Course("PS103", "Biopsychology", "Faculty of Arts", 3));
        fsshCourses.add(new Course("PS104", "Psychological Assessment", "Faculty of Arts", 3));
        fsshCourses.add(new Course("PS105", "Experimental Psychology", "Faculty of Arts", 4));

        // Add all courses to the map
        allFacultyCourses.put("Faculty of Computer Science", focsCourses);
        allFacultyCourses.put("Faculty of Engineering", foetCourses);
        allFacultyCourses.put("Faculty of Business", fafbCourses);
        allFacultyCourses.put("Faculty of Arts", fsshCourses);
    }

    public static void addCourse(Course course) {
        allFacultyCourses.computeIfAbsent(course.getFaculty(), k -> new ArrayList<>()).add(course);
    }

    public static void registerStaff(AcademicStaff staff) {
        staffFaculty.put(staff.getId(), staff.getFaculty());
        staffDetails.put(staff.getId(), staff);
    }

    public static void addStudentToFaculty(String studentId, String facultyId) {
        facultyStudents.computeIfAbsent(facultyId, k -> new ArrayList<>()).add(studentId);
    }

    public static void enrollStudent(String studentId, String courseId) {
        if (!allFacultyCourses.containsKey(courseId)) {
            System.out.println("Course not found!");
            return;
        }

        List<Course> courseList = allFacultyCourses.get(courseId);
        if (courseList == null || courseList.isEmpty()) {
            System.out.println("Course not found!");
            return;
        }

        Course course = courseList.get(0);
        // Add to student's enrollment list
        studentEnrollments.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseId);
    }

    public static void displayStudentCourses(String studentId) {
        if (!studentEnrollments.containsKey(studentId)) {
            System.out.println("No courses found for this student.");
            return;
        }

        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Course", "║", "Code", "║", "Credits", "║", "Instructor", "║", "Status", "║", "Enrollment", "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        for (String courseId : studentEnrollments.get(studentId)) {
            List<Course> courseList = allFacultyCourses.get(courseId);
            if (courseList != null && !courseList.isEmpty()) {
                Course course = courseList.get(0);
                if (course != null) {
                    System.out.printf("%-43s %-3s %-15s %-3s %-10s %-3s",
                        course.getCourseName(), "║", course.getCourseCode(), "║", course.getCreditHours(), "║");
                    System.out.println();
                }
            }
        }
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    public static void displayFacultyCourses(String staffId) {
        String facultyId = staffFaculty.get(staffId);
        if (facultyId == null) {
            System.out.println("Staff member not found or not registered to any faculty.");
            return;
        }

        if (!allFacultyCourses.containsKey(facultyId)) {
            System.out.println("No courses found for this faculty member.");
            return;
        }

        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Course", "║", "Code", "║", "Credits", "║", "Description", "║", "Status", "║", "Enrollment", "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        for (Course course : allFacultyCourses.get(facultyId)) {
            if (course != null) {
                System.out.printf("%-43s %-3s %-15s %-3s %-10s %-3s",
                    course.getCourseName(), "║", course.getCourseCode(), "║", course.getCreditHours(), "║");
                System.out.println();
            }
        }
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    public static void displayFacultyStudents(String staffId) {
        String facultyId = staffFaculty.get(staffId);
        if (facultyId == null) {
            System.out.println("Staff member not found or not registered to any faculty.");
            return;
        }

        if (!facultyStudents.containsKey(facultyId)) {
            System.out.println("No students found in this faculty.");
            return;
        }

        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Student ID", "║", "Name", "║", "Programme", "║", "Year", "║", "GPA", "║", "Credits", "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        for (String studentId : facultyStudents.get(facultyId)) {
            Student student = Student.loadFromFile(studentId);
            if (student != null) {
                System.out.printf("%-43s %-3s %-15s %-4s %-10s %-5s %-15s %-3s %-6s %-3s %-14s %-5s",
                    student.getId(), "║", student.getName(), "║", student.getProgramme(), "║",
                    (2025 - student.getYearEnrolled()), "║", String.format("%.2f", student.getGpa()), "║", 
                    student.getCreditHours(), "║");
                System.out.println();
            }
        }
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    public static void displayAllCourses(String staffId) {
        String facultyId = staffFaculty.get(staffId);
        if (facultyId == null) {
            System.out.println("Staff member not found or not registered to any faculty.");
            return;
        }

        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Course", "║", "Code", "║", "Credits", "║", "Instructor", "║", "Status", "║", "Enrollment", "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        for (Course course : allFacultyCourses.get(facultyId)) {
            if (course != null) {
                System.out.printf("%-43s %-3s %-15s %-3s %-10s %-3s",
                    course.getCourseName(), "║", course.getCourseCode(), "║", course.getCreditHours(), "║");
                System.out.println();
            }
        }
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    public static void displayAllStaff() {
        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Staff ID", "║", "Name", "║", "Faculty", "║", "Position", "║", "Email", "║", "Join Date", "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");

        for (AcademicStaff staff : staffDetails.values()) {
            System.out.printf("%-43s %-3s %-15s %-4s %-10s %-5s %-15s %-3s %-6s %-3s %-14s %-5s",
                staff.getId(), "║", staff.getName(), "║", staff.getFaculty(), "║",
                staff.getPosition(), "║", staff.getEmail(), "║", staff.getJoiningDate(), "║");
            System.out.println();
        }
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
    }

    public static void displayStudentCourses(Student student) {
        System.out.println("\n═════════════════════════════════════════════════════════════════");
        System.out.printf("%-23s %-39s %-10s", "║", "Your Registered Courses", "║");
        System.out.println("\n═════════════════════════════════════════════════════════════════");
        System.out.printf("%-15s %-38s %-7s\n", 
            "Course Code", "Course Name", "Credits");
        System.out.println("─────────────────────────────────────────────────────────────────");

        try {
            String studentFaculty = student.getFaculty();
            List<Course> availableCourses = allFacultyCourses.get(studentFaculty);
            List<String> registeredCodes = student.getSubjectCodes();

            if (availableCourses != null && registeredCodes != null) {
                boolean foundCourses = false;
                for (String code : registeredCodes) {
                    for (Course course : availableCourses) {
                        if (course.getCourseCode().equals(code)) {
                            System.out.printf("%-15s %-38s %-7d\n",
                                course.getCourseCode(),
                                course.getCourseName(),
                                course.getCreditHours());
                            foundCourses = true;
                            break;
                        }
                    }
                }
                if (!foundCourses) {
                    System.out.println("No matching courses found for your registered subjects.");
                }
            } else {
                System.out.println("No courses found for faculty: " + studentFaculty);
            }
        } catch (Exception e) {
            System.out.println("Error displaying courses: " + e.getMessage());
        }
        
        System.out.println("═════════════════════════════════════════════════════════════════");
    }

    public static void displayAllFacultyCourses(String faculty) {
        List<Course> courses = allFacultyCourses.get(faculty);
        if (courses != null) {
            System.out.println("\n═════════════════════════════════════════════════════════════════");
            System.out.printf("%-23s %-39s %-10s", "║", faculty + " Courses", "║");
            System.out.println("\n═════════════════════════════════════════════════════════════════");
            System.out.printf("%-15s %-40s %-7s\n", 
                "Course Code", "Course Name", "Credits");
            System.out.println("─────────────────────────────────────────────────────────────────");

            for (Course course : courses) {
                System.out.printf("%-15s %-45s %-10d\n",
                    course.getCourseCode(),
                    course.getCourseName(),
                    course.getCreditHours());
            }
        } else {
            System.out.println("No courses found for faculty: " + faculty);
        }
        System.out.println("═════════════════════════════════════════════════════════════════");
    }

    public static void displayAllCourses() {
        for (String faculty : allFacultyCourses.keySet()) {
            displayAllFacultyCourses(faculty);
        }
    }
} 