package com.arbrim.polydo.dto.request;

import com.arbrim.polydo.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ActivateUserRequest {

    @NotBlank(message = "username should not be empty.")
    private String username;

    @NotNull(message = "role should not be empty.")
    private UserRole role;
}
