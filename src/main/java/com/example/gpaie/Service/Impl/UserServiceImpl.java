package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.UserModel;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.UserServiceInterface;
@Service
public class UserServiceImpl implements UserServiceInterface{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  DepartementRepository departementRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Override
    public UserModel save(UserModel userRequest) {
        User user;
        if(userRequest.getId()== null){
            user=new User();  
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }else{
             user =userRepository.findById(userRequest.getId()).get();
        }
        user.setCompteIban(userRequest.getCompteIban());
        user.setEmail(userRequest.getEmail());
        user.setEtatCivil(userRequest.getEtatCivil());
        user.setGenre(userRequest.getGenre());
        user.setMatricule(userRequest.getMatricule());
        user.setNom(userRequest.getLastname());
        user.setPrenom(userRequest.getFirstname());
        user.setTelephone(userRequest.getPhone());
        user.setUsername(userRequest.getUsername());
        user.setDepartement(departementRepository.findById(userRequest.getDepartement_id()).get());
        userRepository.saveAndFlush(user);
        return userRequest;
    }

    @Override
    public Optional<UserModel> partialUpdate(UserModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll().stream().filter(Objects::nonNull).map(this::userToUserModel).collect(Collectors.toList());  
   }

    @Override
    public Optional<UserModel> findOne(Long id) {
      return userRepository.findById(id).map(this::userToUserModel);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);;
    }
    public UserModel userToUserModel(User user) {
        return new UserModel(user);
    }
    public User userModelToUser(UserModel userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setEmail(userDTO.getEmail());
            user.setNom(userDTO.getLastname());
            user.setEmail(userDTO.getEmail());
            user.setPrenom(userDTO.getFirstname());
            user.setTelephone(userDTO.getPhone());
            user.setCompteIban(userDTO.getCompteIban());
            user.setGenre(userDTO.getGenre());
            user.setAuthority(null);
            return user;
        }
    }
   
    
}
