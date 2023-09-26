package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.Personnel;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.EmailDetails;
import com.example.gpaie.Model.PersonnelModel;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Repository.PersonnelRepository;
import com.example.gpaie.Repository.RoleRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.MailService;
import com.example.gpaie.Service.PersonnelService;
import com.example.gpaie.Utils.StringUtil;
@Service
public class PersonnelServiceImpl implements PersonnelService{
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  DepartementRepository departementRepository;
    @Autowired
    private  FonctionRepository fonctionRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  MailService mailService;
    @Override
    public PersonnelModel save(PersonnelModel personnelModel) {
        Personnel personnel;
        User user;
        boolean sendMail=false;  
        var pass=StringUtil.randonKey(8);
        if(personnelModel.getId()== null){
            personnel=new Personnel();  
            user=new User();
          
            user.setPassword(passwordEncoder.encode(pass));
            sendMail=true;
        }else{
            personnel=personnelRepository.findById(personnelModel.getId()).get();
            user =personnel.getUser();
            sendMail=false;
        }
        user.setCompteIban(personnelModel.getCompteIban());
        user.setEmail(personnelModel.getEmail());
        user.setEtatCivil(personnelModel.getEtatCivil());
        user.setGenre(personnelModel.getGenre());
        user.setMatricule(generateMatricule());
        user.setNom(personnelModel.getLastname());
        user.setPrenom(personnelModel.getFirstname());
        user.setTelephone(personnelModel.getPhone());
        user.setUsername(personnelModel.getUsername());
        user.setAuthority(roleRepository.findById("ROLE_COMPTABLE").get());
        user.setDepartement(departementRepository.findById(personnelModel.getDepartement_id()).get());
        personnel.setFonction(fonctionRepository.findById(personnelModel.getFonction_id()).get());
        personnel.setSalaireFixe(personnelModel.getSalaireFixe());
        userRepository.saveAndFlush(user);
        personnel.setUser(user);
        personnelRepository.saveAndFlush(personnel);
        if(sendMail){
            EmailDetails emailDetails=new EmailDetails();
            emailDetails.setRecipient(personnel.getUser().getEmail());
            emailDetails.setSubject("Creation du personnel");
            emailDetails.setMsgBody("Informations de connexion: Eamil:"+personnel.getUser().getEmail()+" Password: "+pass);
            mailService.sendMail(emailDetails);
        }
        return personnelModel;
    }
    private String generateMatricule(){
        int random=new Random().nextInt(8);
        String matricule = "GE_" + random;
        return matricule;
    }
    public PersonnelModel personnelToPersonnelModel(Personnel personnel) {
        return new PersonnelModel(personnel);
    }
    @Override
    public Optional<PersonnelModel> partialUpdate(PersonnelModel PersonnelModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'partialUpdate'");
    }

    @Override
    public List<PersonnelModel> findAll() {
        return personnelRepository.findAll().stream().filter(Objects::nonNull).map(this::personnelToPersonnelModel).sorted((x,y)-> y.getId().compareTo(x.getId())).collect(Collectors.toList());  
   
    }

    @Override
    public Optional<PersonnelModel> findOne(Long id) {
        return personnelRepository.findById(id).map(this::personnelToPersonnelModel);
    }

    @Override
    public void delete(Long id) {
       personnelRepository.deleteById(id);
    }

    @Override
    public Optional<PersonnelModel> findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }
    
}
