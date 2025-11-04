public class StudentInformation {
    private String campus;
    private String infoProgramme; 
    private String infoId;
    private String infoName;
    private int infoYearEnrolled;
    private String ic;
    private String email;


    public StudentInformation(String campus, String infoProgramme, String infoId, String infoName, int infoYearEnrolled, String ic, String email) {
        this.campus = campus;
        this.infoProgramme = infoProgramme;
        this.infoId = infoId;
        this.infoName = infoName;
        this.infoYearEnrolled = infoYearEnrolled;
        this.ic = ic;
        this.email = email;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setInfoProgramme(String infoProgramme) {
        this.infoProgramme = infoProgramme;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName;
    }

    public void setInfoYearEnrolled(int infoYearEnrolled) {
        this.infoYearEnrolled = infoYearEnrolled;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getCampus() {
        return campus;
    }

    public String getInfoProgramme() {
        return infoProgramme;
    }

    public String getInfoId() {
        return infoId;
    }

    public String getInfoName() {
        return infoName;
    }

    public int getInfoYearEnrolled() {
        return infoYearEnrolled;
    }

    public String getIc() {
        return ic;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString(){
        return String.format(
            "%-2s %-10s %-2s %-43s %-2s %-8s %-2s %-10s %-2s %-13s %-2s %-14s %-2s %-20s %-2s", 
            "║", getCampus(), "║", getInfoProgramme(), "║", getInfoId(), "║", getInfoName(), "║", getInfoYearEnrolled(), "║", getIc(), "║", getEmail(), "║\n"
        );
    }

   
}
