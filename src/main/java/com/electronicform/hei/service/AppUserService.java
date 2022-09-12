package com.electronicform.hei.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.electronicform.hei.model.AppUser;
import com.electronicform.hei.repository.AppUserRepository;
import com.electronicform.hei.security.jwt.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService {

    private PasswordEncoder encoder;
    private JwtService jwt;
    private final AppUserRepository appUserRepository;

    // Function to Create new User
    public ResponseEntity<AppUser> createUser(AppUser appUser) throws ResponseStatusException {
        AppUser _user = new AppUser(appUser.getUsername(), appUser.getEmail(),
                this.encoder.encode(appUser.getPassword()));
        boolean find = appUserRepository.existsByEmail(appUser.getEmail());
        if (!find) {
            appUserRepository.save(_user);
            return ResponseEntity.ok(_user);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Already taken");
    }

    // Function to Login User
    public String login(String username, String password) {
        boolean find = appUserRepository.existsByUsername(username);
        if (!find) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else {
            AppUser user = appUserRepository.findByUsername(username).get();
            if (this.encoder.matches(password, user.getPassword()))
                return jwt.sign(user);
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Wrong Password");
        }
    }

    // Find me whoani
    public AppUser findOne(Long id) throws ResponseStatusException {
        boolean find = appUserRepository.existsById(id);
        if (!find) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return appUserRepository.findById(id).get();
    }
}
