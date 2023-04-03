package com.example.gpaie.Controller;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gpaie.Model.JwtRequest;
import com.example.gpaie.Model.JwtResponse;
import com.example.gpaie.Model.UserModel;
import com.example.gpaie.Security.JWTUtil;
import com.example.gpaie.Security.JwtUserDetailsService;


@RestController
@CrossOrigin
public class AuthController {


    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;


	@PostMapping("/auth/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    System.out.println(authenticationRequest.getUsername());
/* 	  	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());  */

		final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse(token));
	}
	@PostMapping({ "/auth/reset_token" })
	public UserModel firstPage(@RequestBody UserModel user) {
       
		return user;
	}
/* 	@PostMapping("/token")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getUsername());

            return Collections.singletonMap("jwt-token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    } */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}