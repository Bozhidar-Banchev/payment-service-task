package com.company.dto;

public enum UserRole {

  MERCHANT("merchant"),
  ADMIN("admin");

  private final String name;

  UserRole(String name) {
    this.name = name;
  }
}
