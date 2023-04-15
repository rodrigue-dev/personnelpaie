package com.example.gpaie.Service;

import java.util.List;
import java.util.Optional;

import com.example.gpaie.Model.PaiementModel;

public interface PaiementService {
      /**
     * Save a zone.
     *
     * @param UserModel the entity to save.
     * @return the persisted entity.
     */
    PaiementModel save(PaiementModel userRequest);
    List<PaiementModel> calculSalaire(int month,int year);
    List<PaiementModel> calculHeureSupp(int month,int year);
    List<PaiementModel> calculPrimeRetenue(int month,int year);
    List<PaiementModel> sendMail(int month,int year);
    List<PaiementModel> generatePaie(int month,int year);

    /**
     * Partially updates a zone.
     *
     * @param UserModel the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaiementModel> partialUpdate(PaiementModel zoneDTO);

    /**
     * Get all the zones.
     *
     * @return the list of entities.
     */
    List<PaiementModel> findAll();

    /**
     * Get the "id" zone.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaiementModel> findOne(Long id);

    /**
     * Delete the "id" zone.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
