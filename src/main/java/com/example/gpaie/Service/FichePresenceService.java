package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.FichePresenceModel;

public interface FichePresenceService {
     /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    FichePresenceModel save(FichePresenceModel userRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FichePresenceModel> partialUpdate(FichePresenceModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<FichePresenceModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FichePresenceModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
