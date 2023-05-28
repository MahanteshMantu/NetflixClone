package com.example.NetflixClone.accessor.model;

import lombok.Builder;
import lombok.Getter;


@Builder /*Helps while creating large objects*/
@Getter
public class UserDTO {
    private String userId;
    private String  name;
    private String email;
    private String password;
    private String phoneNo;
    private UserState state;
    private UserRole role;
}
