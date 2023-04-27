package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.HeureSupplModel;

public interface HeureSupplService {
         /**
     * Save a avantage.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    HeureSupplModel save(HeureSupplModel heureSupplModel);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HeureSupplModel> partialUpdate(HeureSupplModel heureSupplModel);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<HeureSupplModel> findAll();
    List<HeureSupplModel> heureSuppByUser(Long userid);
    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HeureSupplModel> findOne(Long id);
        /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
