package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.gpaie.Model.JwtRequest;
import com.example.gpaie.Model.UserModel;

public interface UserServiceInterface {
        /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    UserModel save(UserModel userRequest);
    void changePassword(String oldpass,String newpass,long id);
    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserModel> partialUpdate(UserModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<UserModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    Optional<UserModel> findByEmail(String email);
}
