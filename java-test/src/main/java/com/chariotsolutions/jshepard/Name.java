package com.chariotsolutions.jshepard;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

public class Name {
  private int id;
  private String lastName;
  private String firstName;
  private Optional<String> middleName;
  private LocalDateTime lastUpdate;

  public Name() {}

  public Name(int id, String lastName, String firstName, Optional<String> middleName, LocalDateTime lastUpdate) {
    this.id = id;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastUpdate = lastUpdate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Optional<String> getMiddleName() {
    return middleName;
  }

  public void setMiddleName(Optional<String> middleName) {
    this.middleName = middleName;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(LocalDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("id", id)
        .append("lastName", lastName)
        .append("firstName", firstName)
        .append("middleName", middleName)
        .append("lastUpdate", lastUpdate)
        .toString();
  }
}
