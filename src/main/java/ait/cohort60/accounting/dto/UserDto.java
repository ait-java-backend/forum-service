package ait.cohort60.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private String login;
    private String firstName;
    private String lastName;
    private Set<String> roles;
}
