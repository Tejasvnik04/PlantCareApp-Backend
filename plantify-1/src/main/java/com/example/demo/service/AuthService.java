package com.example.demo.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.PlantEntity;
import com.example.demo.model.User;
import com.example.demo.model.enumerate.Role;
import com.example.demo.repository.PlantRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.AuthenticationRequest;
import com.example.demo.request.RegisterRequest;
import com.example.demo.response.AuthenticationResponse;
import com.example.demo.util.JwtUtil;

//import PlantsCareAssistant.entities.User;
//import PlantsCareAssistant.entities.enumerate.Role;
//import PlantsCareAssistant.repositories.UserRepository;
//import PlantsCareAssistant.request.AuthenticationRequest;
//import PlantsCareAssistant.request.RegisterRequest;
//import PlantsCareAssistant.response.AuthenticationResponse;
//import PlantsCareAssistant.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;


@Service
@Transactional
@Builder
@RequiredArgsConstructor
public class AuthService  
{
	
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PlantService plantService;
    private final JwtUtil jwtUtil;

    public boolean userRegistration(RegisterRequest request) {
        Optional<User> isUserExists = userRepository.findByEmail(request.getEmail());
        if (!isUserExists.isPresent()) 
        {
            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .isEnabled(true)
                    .role(Role.valueOf(request.getRole().toUpperCase()))
                    .build();
            userRepository.save(user);
            return true;
        } 
        else 
        {
            return false;
        }
    }

    public AuthenticationResponse userAuthentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var token = jwtUtil.generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .uid(user.getUid())
                .role(user.getRole())
                .build();
    }
    
    public ArrayList<Object> getd() {
    	ArrayList<Object> newUsers = new ArrayList<>();
    	List<User> users = userRepository.findAll();
    	
    	for (User user : users) {
    		if (!user.getRole().equals("ADMIN")) {  			
    			LinkedHashMap<String, Object> newUser = new LinkedHashMap<>();
    			List<PlantEntity> plants = plantService.getByUserId(user.getUid());
    			
    			newUser.put("uid", user.getUid());
    			newUser.put("name", user.getName());
    			newUser.put("Password", user.getPassword());
    			newUser.put("Email", user.getUsername());
    			newUser.put("isEnabled", user.getIsEnabled());
    			newUser.put("role", user.getRole());
    			newUser.put("createdAt", user.getCreatedAt());
    			newUser.put("updatedAt", user.getUpdatedAt());
    			newUser.put("plants", plants);
    			newUsers.add(newUser);
    		}
    	}
    	
    	return newUsers;
    }
}