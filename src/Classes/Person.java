package Classes;
public class Person {
    private String firstName,lastName;

    public Person(){
        this("","");
    }

    public Person(String fname, String lname){
        this.firstName = fname;
        this.lastName = lname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }    
    
}
