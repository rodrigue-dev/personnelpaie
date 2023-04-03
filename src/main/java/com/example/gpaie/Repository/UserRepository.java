package com.example.gpaie.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.example.gpaie.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    public Optional<User> findByEmail(String email);
    public Optional<User> findByUsername(String username);
    public Optional<User> findTopByOrderByIdDesc();
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);
    Optional<User> findOneByEmailIgnoreCase(String email);
    List<User> findAllByIdNotNullAndActivatedIsTrue();
} 
/* 
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findByName(@Param("name") String name);
}*/