package com.kad.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDto {
    private String userName;
    private String password;
}
