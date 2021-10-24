package com.arbrim.polydo.service;

import com.arbrim.polydo.dto.PolydoUserDetailsDTO;
import com.arbrim.polydo.model.User;
import com.arbrim.polydo.repository.UserRepository;

import com.arbrim.polydo.dto.request.ActivateUserRequest;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolydoUserDetailsService implements UserDetailsService {


    @Autowired
    private Mapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("User by username [%s] not found!")));
        return user.map(PolydoUserDetailsDTO::new).get();
    }

    public PolydoUserDetailsDTO create(PolydoUserDetailsDTO polydoUserDetailsDTO) throws Exception {
        validatePolydoUserDetails(polydoUserDetailsDTO);

        User user = mapper.map(polydoUserDetailsDTO, User.class);
        encryptUserPassword(user);
        userRepository.save(user);

        return polydoUserDetailsDTO;
    }

    private void encryptUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void validatePolydoUserDetails(PolydoUserDetailsDTO polydoUserDetailsDTO) throws Exception {
        if (userByUsernameExists(polydoUserDetailsDTO.getUsername()))
            throw new Exception(String.format("User by username [%s] already exists", polydoUserDetailsDTO.getUsername()));
    }

    private boolean userByUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public PolydoUserDetailsDTO activateUser(ActivateUserRequest activateUserRequest) throws Exception {
        validateActivateUserRequest(activateUserRequest);

        User userInDb = userRepository.findByUsername(activateUserRequest.getUsername()).get();
        userInDb.setActive(true);
        userInDb.setRoles(activateUserRequest.getRole().toString());
        userRepository.save(userInDb);

        return mapper.map(userInDb, PolydoUserDetailsDTO.class);
    }

    private void validateActivateUserRequest(ActivateUserRequest activateUserRequest) throws Exception {
        if (!userByUsernameExists(activateUserRequest.getUsername()))
            throw new Exception(String.format("User by username %s doesn't exist thus can't be activated.", activateUserRequest.getUsername()));
    }
}
