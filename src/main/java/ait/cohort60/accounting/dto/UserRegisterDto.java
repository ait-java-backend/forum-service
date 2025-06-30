package ait.cohort60.accounting.dto;

import lombok.Getter;

@Getter
public class UserRegisterDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}