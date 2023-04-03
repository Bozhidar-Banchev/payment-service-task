package com.company.dto;

public enum UserRole {

  ROLE_MERCHANT("merchant"),
  ROLE_ADMIN("admin");

  private final String name;

  UserRole(String name) {
    this.name = name;
  }
}
