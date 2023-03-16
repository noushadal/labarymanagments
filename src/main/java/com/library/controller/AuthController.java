package com.library.controller;

import java.util.HashSet;
import java.util.Set;

/*import com.Demosecurity.Exception.AppException;
import com.Demosecurity.repository.RoleRepository;
import com.Demosecurity.repository.UserRepository;
import com.Demosecurity.request.LoginRequest;
import com.Demosecurity.request.SignUpRequest;
import com.Demosecurity.responce.ApiResponse;
import com.Demosecurity.responce.JwtAuthenticationResponse;
import com.Demosecurity.security.JwtTokenProvider;
import com.Demosecurity.table.Role;
import com.Demosecurity.table.RoleName;
import com.Demosecurity.table.User;*/
//import com.nt.model.ERole;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.Entity.Role;
import com.library.Entity.Roles;
import com.library.Entity.User;
import com.library.Request.LoginRequest;
import com.library.Request.SignUpRequest;
import com.library.Response.ApiResponse;
import com.library.Response.JwtAuthenticationResponse;
import com.library.security.cfg.jwt.JwtTokenProvider;

//import com.library.Model.Role;
//import com.library.Model.Roles;
//import com.library.Model.User;
//import com.library.Repository.RoleRepository;
//import com.library.Repository.UserRepository;
//import com.library.request.LoginRequest;
//import com.library.request.SignUpRequest;
//import com.library.response.ApiResponse;
//import com.library.response.JwtAuthenticationResponse;
//import com.library.security.config.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    com.library.repository.UserRepository userRepository;

    @Autowired
    com.library.repository.RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) 
    {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) 
        {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail()))
        {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        //setting user role dynamicaly by role table
        
        Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(Roles.STUDENT)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
		//Role userRole2 = roleRepository.findByName(RoleName.MANAGER)
			//	.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		//roles.add(userRole2);
		user.setRole(roles);
		userRepository.save(user);

//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));
//
//        User result = userRepository.save(user);

       

        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }
}
