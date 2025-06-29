package ait.cohort60.accounting.service;

import ait.cohort60.accounting.dto.NewUserDto;
import ait.cohort60.accounting.dto.UpdateUserDto;
import ait.cohort60.accounting.dto.UserDto;

public interface UserService {
    UserDto register(NewUserDto newUserDto);
    UserDto getUser(String login);
    UserDto updateUser(String login, UpdateUserDto updateUserDto);
    UserDto deleteUser(String login);
    void changePassword(String login, String oldPassword, String newPassword);
    UserDto addRole(String login, String role);
    UserDto removeRole(String login, String role);
}
