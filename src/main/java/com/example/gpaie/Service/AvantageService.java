package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.AvantageModel;

public interface AvantageService {
     /**
     * Save a avantage.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    AvantageModel save(AvantageModel userRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AvantageModel> partialUpdate(AvantageModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<AvantageModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AvantageModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    List<AvantageModel> findAllByFonction(Long id);
}
