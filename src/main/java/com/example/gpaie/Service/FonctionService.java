package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.FontionModel;
import com.example.gpaie.Model.fonctionItemRequest;

public interface FonctionService {
      /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    FontionModel save(FontionModel userRequest);
    FontionModel addAvantage(fonctionItemRequest aRequest);
    FontionModel removeAvantage(fonctionItemRequest aRequest);
    FontionModel addDepartement(fonctionItemRequest aRequest);
    FontionModel removeDepartement(fonctionItemRequest aRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FontionModel> partialUpdate(FontionModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<FontionModel> findAll();
    List<FontionModel> findAllByDepartement(long departement_id);

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FontionModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
