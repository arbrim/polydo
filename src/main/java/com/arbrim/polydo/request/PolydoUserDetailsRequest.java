package com.arbrim.polydo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolydoUserDetailsRequest {
    private Long id;
    private String username;
    private String password;

}
