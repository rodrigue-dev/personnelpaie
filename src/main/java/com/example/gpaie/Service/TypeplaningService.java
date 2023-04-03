package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.TypePlaningModel;

public interface TypeplaningService {
      /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    TypePlaningModel save(TypePlaningModel userRequest);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypePlaningModel> partialUpdate(TypePlaningModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<TypePlaningModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypePlaningModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
