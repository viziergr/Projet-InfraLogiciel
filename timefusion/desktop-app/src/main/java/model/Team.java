package main.java.model;

import java.util.List;

public class Team {

  private Long id;
  private String name;
  private String createdAt;
  private User leader; // Un utilisateur spécifique comme manager
  private List<User> coleaders; // Liste des membres de l'équipe
  private List<User> elders; // Liste des membres de l'équipe
  private List<User> members; // Liste des membres de l'équipe

  // Constructeurs, getters, setters

  public Team(
    Long id,
    String name,
    String createdAt,
    User leader,
    List<User> coleaders,
    List<User> elders,
    List<User> members
  ) {
    this.id = id;
    this.name = name;
    this.createdAt = createdAt;
    this.leader = leader;
    this.coleaders = coleaders;
    this.elders = elders;
    this.members = members;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getCreatedAt() {
    return this.createdAt;
  }

  public User getLeader() {
    return this.leader;
  }

  public List<User> getColeaders() {
    return this.coleaders;
  }

  public List<User> getElders() {
    return this.elders;
  }

  public List<User> getMembers() {
    return this.members;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setLender(User leader) {
    this.leader = leader;
  }

  public void setColeaders(List<User> coleaders) {
    this.coleaders = coleaders;
  }

  public void setElders(List<User> elders) {
    this.elders = elders;
  }

  public void setMembers(List<User> members) {
    this.members = members;
  }
}
