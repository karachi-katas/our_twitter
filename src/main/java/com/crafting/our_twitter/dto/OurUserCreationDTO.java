package com.crafting.our_twitter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OurUserCreationDTO {
    private String userName;
    private String password;
    private String country;
    private String gender;
    private String phonenumber;
}
