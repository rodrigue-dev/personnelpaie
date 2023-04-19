package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.PersonnelModel;

public interface PersonnelService {
            /**
     * Save a zone.
     *
     * @param PersonnelModel the entity to save.
     * @return the persisted entity.
     */
    PersonnelModel save(PersonnelModel PersonnelModel);
    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PersonnelModel> partialUpdate(PersonnelModel PersonnelModel);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<PersonnelModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonnelModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    Optional<PersonnelModel> findByEmail(String email);
}
