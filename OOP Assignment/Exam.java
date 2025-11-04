public class Exam{
    private String subject;
    private String date;
    private String time;
    private String venue;
    private String room;
    private String invigilators;

    public Exam(String subject, String date, String time, String venue, String room, String invigilators){
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.room = room;
        this.invigilators = invigilators;
    }

    public Exam(String subject, String date, String time, String venue, String invigilators){
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.room = "";
        this.invigilators = invigilators;

    }
    
    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getSubject(){
        return subject;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public String getTime(){
        return time;
    }

    public void setVenue(String venue){
        this.venue = venue;
    }

    public String getVenue(){
        return venue;
    }

    public void setRoom(String room){
        this.room = room;
    }

    public String getRoom(){
        return room;
    }

    public void setInvigilator(String invigilators){
        this.invigilators = invigilators;
    }

    public String getInvigilators(){
        return invigilators;
    }

    public String toString(){
        return String.format(
            "%-43s %-3s %-15s %-4s %-10s %-5s %-15s %-3s %-6s %-3s %-14s %-5s",
            subject, "║", date, "║", time, "║", venue, "║", room, "║", invigilators, "║"
        );
    }
}