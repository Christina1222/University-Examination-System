public class User{
    protected String id;
    protected String name;
    protected String contactNumber;
    protected String faculty;

    public User(){
        this.id = "";
        this.name = "";
        this.contactNumber = "";
        this.faculty = "";
    }

    public User(String id, String name, String contactNumber, String faculty){
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.faculty = faculty;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public void setFaculty(String faculty){
        this.faculty = faculty;
    }

    public String getFaculty(){
        return faculty;
    }

    @Override
    public String toString(){
        return "Name: " + name +
               "\nContact Number: " + contactNumber + 
               "\nFaculty: " + faculty;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof User){
            System.out.println(((User)o).name);
        }
        return false;
    }

}