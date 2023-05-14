package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.AbsenceModel;

public interface AbsenceService {
         /**
     * Save a avantage.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    AbsenceModel save(AbsenceModel userRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AbsenceModel> partialUpdate(AbsenceModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<AbsenceModel> findAll();
    List<AbsenceModel> findAllOwn(Long id);

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AbsenceModel> findOne(Long id);
        /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

}
