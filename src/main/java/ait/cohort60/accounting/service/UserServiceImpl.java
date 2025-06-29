package ait.cohort60.accounting.service;

import ait.cohort60.accounting.dto.NewUserDto;
import ait.cohort60.accounting.dto.UpdateUserDto;
import ait.cohort60.accounting.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService {
    @Override
    public UserDto register(NewUserDto newUserDto) {
        return null;
    }

    @Override
    public UserDto getUser(String login) {
        return null;
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        return null;
    }

    @Override
    public UserDto deleteUser(String login) {
        return null;
    }

    @Override
    public void changePassword(String login, String oldPassword, String newPassword) {

    }

    @Override
    public UserDto addRole(String login, String role) {
        return null;
    }

    @Override
    public UserDto removeRole(String login, String role) {
        return null;
    }
}
