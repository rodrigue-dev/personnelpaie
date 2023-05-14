package com.example.gpaie.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gpaie.Entity.User;
import com.example.gpaie.Model.EmailDetails;
import com.example.gpaie.Model.EmailModel;
import com.example.gpaie.Model.UserModel;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Repository.PaiementRepository;
import com.example.gpaie.Repository.RoleRepository;
import com.example.gpaie.Repository.UserRepository;
import com.example.gpaie.Service.FileService;
import com.example.gpaie.Service.MailService;
import com.example.gpaie.Service.PlaningService;
import com.example.gpaie.Service.UserServiceInterface;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private FonctionRepository fonctionRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PlaningService planingService;

    @Override
    public UserModel save(UserModel userRequest) {
        User user;
        boolean sendMail = false;
        var pass=randonKey(10);
        if (userRequest.getId() == null) {
            user = new User();
            user.setPassword(passwordEncoder.encode(pass));
            user.setTypeplaning(userRequest.getTypeplaning());
            sendMail = true;
        } else {
            user = userRepository.findById(userRequest.getId()).get();
            sendMail = false;
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
        user.setFonction(fonctionRepository.findById(userRequest.getFonction_id()).get());
        if(!userRequest.getImageFile().isEmpty()){
            var photo=fileService.convertImage(userRequest.getImageFile());
            user.setImageUrl(photo);
            user.setImage(fileService.convertImageByte(userRequest.getImageFile()));
        }
        User u=userRepository.saveAndFlush(user);
        if(u.getTypeplaning()==0){

        }else if(u.getTypeplaning()==1){

        }else{

        }
        if (sendMail) {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(user.getEmail());
            emailDetails.setSubject("Creation du personnel");
            emailDetails.setMsgBody(
                    "Informations de connexion: Eamil:" + user.getEmail() + " Password: " + pass);
          //  mailService.sendMail(emailDetails);
        }
        userRequest.setId(u.getId());
        return userRequest;
    }

    private String generateMatricule() {
        String matricule = "GE_" + randonIntKey(10).toLowerCase();
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
                .filter(e->e.isEnabled()).filter(e -> e.getAuthority().getAuthority().isBlank()==false)
                .map(this::userToUserModel).collect(Collectors.toList());
    }

    @Override
    public Optional<UserModel> findOne(Long id) {
        return userRepository.findById(id).map(this::userToUserModel);
    }

    @Override
    public void delete(Long id) {
       userRepository.findById(id).ifPresent(e->e.setEnabled(false));
       userRepository.flush();
      /*  var paiements=paiementRepository.findAllByUser(userRepository.findById(id).get());
       paiementRepository.deleteAll(paiements);
       userRepository.deleteById(id); */
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
        var user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with id = " + id);
        String oldencript = passwordEncoder.encode(oldpass);

        user.get().setPassword(passwordEncoder.encode(newpass));
        userRepository.flush();
    }

    @Override
    public String resetpassword(String email) {
        var user = userRepository.findByEmail(email);
        /* for (int i = 0; i < 100; i++) {
            System.out.println(randonIntKey(10).toLowerCase());
        } */
         if (user.isPresent()) {
            var pass = randonKey(10).toLowerCase();
            user.get().setPassword(passwordEncoder.encode(pass));
            userRepository.flush();
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setRecipient(email);
            emailDetails.setSubject("Mot de passe retrouve");
            emailDetails.setMsgBody("Informations de connexion: Email:" + email + " Password: " + pass);
            mailService.sendMail(emailDetails);
            return "Message sent";
        } else {
            return "Message not sent";
        } 
    }

    String randonKey(int size) {

        String[] arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "z", "e", "r", "t", "y", "u", "i", "o", "p",
                "q", "s", "d", "f", "g", "h", "j", "k", "l", "m", "w", "x", "c", "v", "b", "n" };
        int leftLimit = 48;
        int rightLimit = 122;
        Random randomGenerator = new Random();
        return randomGenerator.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(size).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
    String randonIntKey(int size) {

       int leftLimit = 48;
        int rightLimit = 57;
        Random randomGenerator = new Random();
        return randomGenerator.ints(leftLimit, rightLimit + 1)
                .limit(size).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }

    @Override
    public boolean isEnabledUser(String email) {
    return  userRepository.findByEmail(email).filter(e->e.isEnabled()).isPresent();
    }

    @Override
    public void sendMail(EmailModel emailModel) {
        for (int i = 0; i < emailModel.getItems().length; i++) {
            var user=userRepository.findById(emailModel.getItems()[i]).get();
              EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(user.getEmail());
        emailDetails.setSubject(emailModel.getSubject());
        emailDetails.setMsgBody(emailModel.getMessage());
        mailService.sendMail(emailDetails);
        }
      
    }

}
