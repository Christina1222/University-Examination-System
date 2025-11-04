public class ExamDemo{
    //Exam timetable for Faculty of Computer Science
    static Exam e1 = new Exam("Problem Solving and Programming", "3 March 2025", "10.00am", "Sport Complex", "Ms Joey");
    static Exam e2 = new Exam("Computer Architecture", "5 March 2025", "2.00pm", "Block A", "A101", "Mr Smith");
    static Exam e3 = new Exam("Systems Analysis and Design", "7 March 2025", "9.00am", "Block B", "B202", "Ms Lee");
    static Exam e4 = new Exam("Fundamentals of Computer Networks", "11 March 2025", "11.00am", "Block C", "C305", "Ms Sangeetha");
    static Exam e5 = new Exam("Probability and Statistics", "12 March 2025", "3.00pm", "Block D", "D404", "Ms Davis");

    //Exam timetable for Faculty of Engineering
    static Exam e6 = new Exam("Software Engineering", "3 March 2025", "1.00pm", "Block E", "E505", "Mr Wilson");
    static Exam e7 = new Exam("Computer Organisation and Architecture", "4 March 2025", "9.00am", "Sport Complex", "Dr. Johnson");
    static Exam e8 = new Exam("Artificial Intelligence", "6 March 2025", "1.00pm", "Block M", "M202", "Prof. Smith");
    static Exam e9 = new Exam("Data Structures and Algorithms", "7 March 2025", "10.00am", "Block M", "M303", "Dr. Brown");
    static Exam e10 = new Exam("Discrete Mathematics", "13 March 2025", "3.00pm", "Block M", "M404", "Prof. Davis");

    //Faculty of Business
    static Exam e11 = new Exam("Principles of Accounting", "3 April 2025", "1.00pm", "Block E", "E205", "Mr Wilson");
    static Exam e12 = new Exam("Quantitative Studies ", "9 April 2025", "10.00am", "Block M", "M103", "Ms Curie");
    static Exam e13 = new Exam("IT Fundamentals and Applications", "10 April 2025", "2.00pm", "Block Q", "Q200", "Mr Zhang");
    static Exam e14 = new Exam("Introduction to Business", "11 April 2025", "10.00am", "Block E", "E505", "Mr Tan");
    static Exam e15 = new Exam("Entrepreneurship", "15 April 2025", "1.00pm", "Block E", "E505", "Ms Lilian");
    static Exam e16 = new Exam("Introduction to Human Resource Management ", "16 April 2025", "1.00pm", "Block E", "E505", "Ms Low");

    //Faculty of Medicine
    static Exam e17 = new Exam("Anatomy and Physiology II", "5 May 2025", "10.00am", "Sport Complex", "Mr Tan");
    static Exam e18 = new Exam("Introduction to Psychology and Sociology ", "6 May 2025", "2.00pm", "Block Q", "Q101", "Mr Kok");
    static Exam e19 = new Exam("Kinesiology", "7 May 2025", "10.00am", "Block M", "M102", "Ms Tan");
    static Exam e20 = new Exam("Biomechanics ", "12 May 2025", "12.00pm", "Block Q", "Q303", "Ms Ong");
    static Exam e21 = new Exam("Anatomy and Physiology I", "13 May 2025", "4.00pm", "Block H", "H201", "Mr Keat");


    public static String getFOCSTimetable() {
        return "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                String.format("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Subject", "║", "Date", "║", "Time", "║", "Venue", "║", "Room", "║", "Invigilators", "║") + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                e1.toString() + "\n" +
                e2.toString() + "\n" +
                e3.toString() + "\n" +
                e4.toString() + "\n" +
                e5.toString() + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    }

    public static String getFOETTimetable() {
        return "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
            String.format("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Subject", "║", "Date", "║", "Time", "║", "Venue", "║", "Room", "║", "Invigilators", "║") + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                e6.toString() + "\n" +
                e7.toString() + "\n" +
                e8.toString() + "\n" +
                e9.toString() + "\n" +
                e10.toString() + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    }

    public static String getFAFBTimetable() {
        return "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
            String.format("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Subject", "║", "Date", "║", "Time", "║", "Venue", "║", "Room", "║", "Invigilators", "║") + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                e11.toString() + "\n" +
                e12.toString() + "\n" +
                e13.toString() + "\n" +
                e14.toString() + "\n" +
                e15.toString() + "\n" +
                e16.toString() + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    }

    public static String getFSSHTimetable() {
        return "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
            String.format("%-16s %-26s %-7s %-11s %-5s %-9s %-8s %-12s %-3s %-6s %-3s %-14s %-8s", "", "Subject", "║", "Date", "║", "Time", "║", "Venue", "║", "Room", "║", "Invigilators", "║") + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n" +
                e17.toString() + "\n" +
                e18.toString() + "\n" +
                e19.toString() + "\n" +
                e20.toString() + "\n" +
                e21.toString() + "\n" +
                "═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════";
    }

}