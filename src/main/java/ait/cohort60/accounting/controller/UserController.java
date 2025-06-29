package ait.cohort60.accounting.controller;

import ait.cohort60.accounting.dto.NewUserDto;
import ait.cohort60.accounting.dto.UpdateUserDto;
import ait.cohort60.accounting.dto.UserDto;
import ait.cohort60.accounting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserDto register(@RequestBody NewUserDto newUserDto) {
        return userService.register(newUserDto);
    }

    @GetMapping("/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userService.getUser(login);
    }

    @PutMapping("/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(login, updateUserDto);
    }

    @DeleteMapping("/{login}")
    public UserDto deleteUser(@PathVariable String login) {
        return userService.deleteUser(login);
    }

    @PutMapping("/{login}/password")
    public void changePassword(@PathVariable String login,
                               @RequestParam String oldPassword,
                               @RequestParam String newPassword) {
        userService.changePassword(login, oldPassword, newPassword);
    }

    @PostMapping("/{login}/roles")
    public UserDto addRole(@PathVariable String login, @RequestParam String role) {
        return userService.addRole(login, role);
    }

    @DeleteMapping("/{login}/roles")
    public UserDto removeRole(@PathVariable String login, @RequestParam String role) {
        return userService.removeRole(login, role);
    }
}
