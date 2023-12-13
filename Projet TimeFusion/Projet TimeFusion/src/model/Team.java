package model;

import java.util.List;

public class Team {
    private Long id;
    private String name;
    private User manager; // Un utilisateur spécifique comme manager
    private List<User> members; // Liste des membres de l'équipe

    // Constructeurs, getters, setters

    public Team() {
    }

    // autres constructeurs, getters et setters...
}

