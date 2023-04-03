package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Departement;
import com.example.gpaie.Model.DepartementModel;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Service.DepartementService;
@Service
public class DepartementServiceImpl implements DepartementService{
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private DepartementRepository departementRepository;
    @Override
    public DepartementModel save(DepartementModel departementModel) {
        Departement departement;
        if(departementModel.getId()== null){
            departement=new Departement();  
        }else{
            departement =departementRepository.findById(departementModel.getId()).get();
        }
        departement.setNomDepartement(departementModel.getNomDepartement());
        departementRepository.saveAndFlush(departement);
        return departementModel;
    }
    @Override
    public Optional<DepartementModel> partialUpdate(DepartementModel zoneDTO) {
        
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }
    @Override
    public List<DepartementModel> findAll() {
        return departementRepository.findAll().stream().filter(Objects::nonNull).map(this::departementToDepartementModel).collect(Collectors.toList());
    }
    @Override
    public Optional<DepartementModel> findOne(Long id) {
       return departementRepository.findById(id).map(e->departementToDepartementModel(e));
    }
    @Override
    public void delete(Long id) {
     departementRepository.deleteById(id);;
    }
    public DepartementModel departementToDepartementModel(Departement departement) {
        return new DepartementModel(departement);
    }
}
