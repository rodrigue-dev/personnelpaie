package com.example.gpaie.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gpaie.Entity.Role;

public interface RoleRepository  extends JpaRepository<Role, String> {
}
