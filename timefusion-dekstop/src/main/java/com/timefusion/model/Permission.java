package com.timefusion.model;

public class Permission {

  private Long id;
  private String name; // Par exemple, "ADMIN", "MEMBER", etc.

  // Constructeurs, getters, setters

  public Permission(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return "Permission [id=" + this.id + ", name=" + this.name + "]";
  }
  // autres constructeurs, getters et setters...
}
