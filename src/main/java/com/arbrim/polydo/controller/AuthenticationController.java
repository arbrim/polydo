package com.arbrim.polydo.controller;

import com.arbrim.polydo.dto.PolydoUserDetailsDTO;
import com.arbrim.polydo.request.AuthenticationRequest;
import com.arbrim.polydo.request.AuthenticationResponse;
import com.arbrim.polydo.request.PolydoUserDetailsRequest;
import com.arbrim.polydo.service.PolydoUserDetailsService;
import com.arbrim.polydo.util.JwtUtil;
import com.github.dozermapper.core.Mapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private Mapper mapper;
    private JwtUtil jwtTokenUtil;
    private AuthenticationManager authenticationManager;
    private PolydoUserDetailsService polydoUserDetailsService;


    public AuthenticationController(Mapper mapper, JwtUtil jwtTokenUtil, AuthenticationManager authenticationManager, PolydoUserDetailsService polydoUserDetailsService) {
        this.mapper = mapper;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.polydoUserDetailsService = polydoUserDetailsService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> retrieveAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        HttpHeaders responseHeaders = new HttpHeaders();

        Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                authenticationRequest.getPassword()));

        final PolydoUserDetailsDTO polydoUserDetails = (PolydoUserDetailsDTO) polydoUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(polydoUserDetails);
        AuthenticationResponse response = new AuthenticationResponse(jwt);

        response.setId(polydoUserDetails.getId());
        response.setUsername(polydoUserDetails.getUsername());
        List<String> roles = new ArrayList<String>();
        polydoUserDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
        response.setRoles(roles);

        return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);

    }

    @PostMapping(value = "/register")
    public PolydoUserDetailsDTO register(@NotNull @Valid @RequestBody PolydoUserDetailsRequest polydoUserDetailsRequest) throws Exception {
        PolydoUserDetailsDTO polydoUserDetailsDTO = mapper.map(polydoUserDetailsRequest, PolydoUserDetailsDTO.class);
        polydoUserDetailsService.create(polydoUserDetailsDTO);

        return polydoUserDetailsDTO;
        
    }

}
