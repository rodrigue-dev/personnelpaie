package com.example.gpaie;

import com.example.gpaie.Entity.Departement;
import com.example.gpaie.Entity.Fonction;
import com.example.gpaie.Entity.Role;
import com.example.gpaie.Entity.User;
import com.example.gpaie.Repository.DepartementRepository;
import com.example.gpaie.Repository.FonctionRepository;
import com.example.gpaie.Repository.RoleRepository;
import com.example.gpaie.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@SpringBootApplication
public class GpaieApplication  implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private FonctionRepository fonctionRepository;
	public static void main(String[] args) {
		SpringApplication.run(GpaieApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role autority;
		if (roleRepository.findById("ROLE_ADMIN").isEmpty()){
			 autority =new Role();
			 autority.setAuthority("ROLE_ADMIN");
			roleRepository.save(autority);
		}else {
			 autority =roleRepository.findById("ROLE_ADMIN").get();
		}
		Departement departement ;
		if (departementRepository.findById(1L).isPresent()){
			 departement =departementRepository.findById(1L).get();
		}else {
			 departement = new Departement();
			departement.setNomDepartement("Administration");
			departementRepository.save(departement);
		}
		Fonction fonction;
		if (fonctionRepository.findById(1L).isPresent()){
			fonction =fonctionRepository.findById(1L).get();
		}else {
			fonction = new Fonction();
			fonction.setTypeFonction("Administarteur");
			fonction.getDepartements().add(departement);
			fonctionRepository.save(fonction);
		}
		if (!userRepository.findByEmail("nganmoue3@gmail.com").isPresent()){
			var user =new User();
			user.setActivated(true);
			user.setNom("nganmou");
			user.setPassword(passwordEncoder.encode("12345"));
			user.setMatricule("A-0001");
			user.setEmail("nganmoue3@gmail.com");
			user.setDepartement(departement);
			user.setAuthority(autority);
			user.setEnabled(true);
			user.setEtatCivil("celibataire");
			user.setGenre("M");
			user.setFonction(fonction);
			user.setCreatedAt(LocalDateTime.now());
			user.setTypeplaning(0);
			user.setPrenom("appo");
			user.setUsername("nganmoue3@gmail.com");
			user.setModifiedAt(LocalDateTime.now());
			userRepository.save(user);
		}
	}
}
