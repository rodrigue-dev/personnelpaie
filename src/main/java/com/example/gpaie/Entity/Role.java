package com.example.gpaie.Entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "authority")
public class Role implements GrantedAuthority {

  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  public static final String ROLE_COMPTABLE = "ROLE_COMPTABLE";
  public static final String ROLE_USER = "ROLE_USER";
  @Nonnull
  @Id
  @Column(length = 60)
  private String authority;

  public void setAuthority(String authority) {
   this.authority = authority;
  }

  @Override
  public String getAuthority() {
    return authority;
  }
}
