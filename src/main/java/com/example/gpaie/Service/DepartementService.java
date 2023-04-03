package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.DepartementModel;

public interface DepartementService {
    
    /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    DepartementModel save(DepartementModel userRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DepartementModel> partialUpdate(DepartementModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<DepartementModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepartementModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
