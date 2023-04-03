package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.TypePlaining;
import com.example.gpaie.Model.TypePlaningModel;
import com.example.gpaie.Repository.TypePlaningRepository;
import com.example.gpaie.Service.TypeplaningService;
@Service
public class TypePlaningServiceImpl implements TypeplaningService{
    private final Logger log = LoggerFactory.getLogger(TypePlaningServiceImpl.class);
    @Autowired
    private TypePlaningRepository typePlaningRepository;
    @Override
    public TypePlaningModel save(TypePlaningModel typePlaningModel) {
        TypePlaining typePlaining;
        if(typePlaningModel.getId()== null){
            typePlaining=new TypePlaining();  
        }else{
            typePlaining =typePlaningRepository.findById(typePlaningModel.getId()).get();
        }
        typePlaining.setNomType(typePlaningModel.getNomType());
        typePlaningRepository.saveAndFlush(typePlaining);
        return typePlaningModel;
    }
    @Override
    public Optional<TypePlaningModel> partialUpdate(TypePlaningModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }
    @Override
    public List<TypePlaningModel> findAll() {
        return typePlaningRepository.findAll().stream().map(this::typePlainingToTypePlainingModel).collect(Collectors.toList());
    }
    @Override
    public Optional<TypePlaningModel> findOne(Long id) {
       return typePlaningRepository.findById(id).map(this::typePlainingToTypePlainingModel);
    }
    @Override
    public void delete(Long id) {
      typePlaningRepository.deleteById(id);
    }
    public TypePlaningModel typePlainingToTypePlainingModel(TypePlaining typePlaining) {
        return new TypePlaningModel(typePlaining);
    }
}
