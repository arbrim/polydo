package com.arbrim.polydo.controller;

import com.arbrim.polydo.dto.PolydoUserDetailsDTO;
import com.arbrim.polydo.dto.request.ActivateUserRequest;
import com.arbrim.polydo.service.PolydoUserDetailsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/admin")
public class AdminController {

    private PolydoUserDetailsService polydoUserDetailsService;

    public AdminController(PolydoUserDetailsService polydoUserDetailsService) {
        this.polydoUserDetailsService = polydoUserDetailsService;
    }

    @PutMapping("/activate")
    public PolydoUserDetailsDTO activateUserByUsername(@NotNull @Valid @RequestBody ActivateUserRequest activateUserRequest) throws Exception {
        return polydoUserDetailsService.activateUser(activateUserRequest);

    }
}
