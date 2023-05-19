package com.example.gpaie.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.Makeplaning;
import com.example.gpaie.Model.PlaningModel;
import com.example.gpaie.Model.PlaningUserModel;

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
    List<PlaningUserModel> getPlaningWeek(String localdate);
    List<PlaningUserModel> getPlaningMonth(String localdate);
    List<LocalDate> getPlaningHeaderMonth(String localdate);
    List<LocalDate> getPlaningHeaderWeek(String localdate);
    List<PlaningModel> getPlaningByUserBetwennDate(Long user_id,String datedebut,String datefin);
    List<Makeplaning> getPlaningByUser(Long user_id,String localdate);

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
