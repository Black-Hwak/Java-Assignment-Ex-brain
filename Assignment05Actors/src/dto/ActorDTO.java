package dto;

public class ActorDTO {
    private String first_name;
    private String last_name;

    public ActorDTO(){
    };
    public ActorDTO(String fname, String lname){
        this.first_name = fname;
        this.last_name = lname;
    }
    public  String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstName) {
        first_name = firstName;
    }

    public  String getLast_name() {
        return last_name;
    }

    public  void setLast_name(String lastName) {
        last_name = lastName;
    }
}
