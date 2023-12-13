package model;

import java.util.List;

public class Team {
    private Long id;
    private String name;
    private User manager; // Un utilisateur spécifique comme manager
    private List<User> members; // Liste des membres de l'équipe

    // Constructeurs, getters, setters

    public Team(Long id, String name, User manager, List<User> members) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.members = members;
    }

    public Long getId(){
        return this.id;
    }   

    public String getName(){
        return this.name;
    }

    public User getManager(){
        return this.manager;
    }

    public List<User> getMembers(){
        return this.members;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setManager(User manager){
        this.manager = manager;
    }

    public void setMembers(List<User> members){
        this.members = members;
    }

    public String toString(){
        return "Team [id=" + this.id + ", name=" + this.name + ", manager=" + this.manager + ", members=" + this.members + "]";
    }


    
}


