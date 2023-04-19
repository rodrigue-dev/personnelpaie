package com.example.gpaie.Service.Impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Role;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.EmailDetails;
import com.example.gpaie.Model.JwtRequest;
import com.example.gpaie.Model.UserModel;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Repository.RoleRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.MailService;
import com.example.gpaie.Service.UserServiceInterface;
import com.example.gpaie.Utils.DateUtil;

import io.micrometer.common.util.StringUtils;
@Service
public class UserServiceImpl implements UserServiceInterface{

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  DepartementRepository departementRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  MailService mailService;
    @Override
    public UserModel save(UserModel userRequest) {
        User user;
        boolean sendMail=false;
        if(userRequest.getId()== null){
            user=new User();  
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            sendMail=true;
        }else{
             user =userRepository.findById(userRequest.getId()).get();
             sendMail=false;
        }
        user.setCompteIban(userRequest.getCompteIban());
        user.setEmail(userRequest.getEmail());
        user.setEtatCivil(userRequest.getEtatCivil());
        user.setGenre(userRequest.getGenre());
        user.setMatricule(generateMatricule());
        user.setNom(userRequest.getLastname());
        user.setPrenom(userRequest.getFirstname());
        user.setTelephone(userRequest.getPhone());
        user.setUsername(userRequest.getUsername());
        user.setAuthority(roleRepository.findById(userRequest.getRole()).get());
        user.setDepartement(departementRepository.findById(userRequest.getDepartement_id()).get());
        userRepository.saveAndFlush(user);
        if(sendMail){
            EmailDetails emailDetails=new EmailDetails();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setSubject("Creation du personnel");
            emailDetails.setMsgBody("Informations de connexion: Eamil:"+user.getEmail()+" Password: "+userRequest.getPassword());
            mailService.sendMail(emailDetails);
        }
        return userRequest;
    }
    private String generateMatricule(){
        int random=new Random().nextInt(8);
        String matricule = "GE_" + random;
        return matricule;
    }

    @Override
    public Optional<UserModel> partialUpdate(UserModel zoneDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll().stream()
        .filter(Objects::nonNull).filter(e->e.getAuthority().getAuthority().equals(Role.ROLE_USER))
        .map(this::userToUserModel).collect(Collectors.toList());  
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
            user.setAuthority(roleRepository.findById(userDTO.getRole()).get());
            return user;
        }
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        System.out.println(email);
       return userRepository.findByEmail(email).map(this::userToUserModel);
    }

    @Override
    public void changePassword(String oldpass, String newpass, long id) {
        var user =userRepository.findById(id);
        if(user.isEmpty())
        throw new UsernameNotFoundException("Could not findUser with id = " + id);
        String oldencript=passwordEncoder.encode(oldpass);

        user.get().setPassword(passwordEncoder.encode(newpass));
        userRepository.flush();
    }
   
    
}
