package com.arbrim.polydo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolydoUserDetailsRequest {
    private Long id;
    private String username;
    private String password;

}
