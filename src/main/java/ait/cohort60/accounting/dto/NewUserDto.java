package ait.cohort60.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
}
