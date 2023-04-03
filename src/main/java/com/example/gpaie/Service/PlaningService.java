package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.PlaningModel;

public interface PlaningService {
          /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    PlaningModel save(PlaningModel userRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PlaningModel> partialUpdate(PlaningModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<PlaningModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PlaningModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
