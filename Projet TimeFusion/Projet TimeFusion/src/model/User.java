package model;

public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password; // Stocké crypté

    // Constructeurs, getters, setters

    public User(long id, String email, String firstName, String lastName, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public long getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getPassword(){
        return this.password;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }   

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String toString(){
        return "Id: " + id + ", Email: " + email + ", First name: " + firstName + ", Last name: " + lastName + ", Password: " + password;
    }

    
    // autres constructeurs, getters et setters...
}
