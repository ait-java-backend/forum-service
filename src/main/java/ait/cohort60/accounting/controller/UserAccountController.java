package ait.cohort60.accounting.controller;

import ait.cohort60.accounting.dto.*;
import ait.cohort60.accounting.service.UserAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Validated
public class UserAccountController {
    private final UserAccountService userAccountService;

    @PostMapping("/register")
    public UserDto register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        return userAccountService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public UserDto login(Principal principal) {
        return userAccountService.getUser(principal.getName());
    }

    @DeleteMapping("/user/{login}")
    public UserDto removeUser(@PathVariable String login) {
        return userAccountService.removeUser(login);
    }

    @PatchMapping("/user/{login}")
    public UserDto updateUser(@PathVariable String login, @RequestBody @Valid UserEditDto userEditDto) {
        return userAccountService.updateUser(login, userEditDto);
    }

    @PatchMapping("/user/{login}/role/{role}")
    public RolesDto addRole(@PathVariable @NotBlank(message = "Login must not be blank") String login,
                            @PathVariable @NotBlank(message = "Role must not be blank") String role) {
        return userAccountService.changeRolesList(login, role, true);
    }

    @DeleteMapping("/user/{login}/role/{role}")
    public RolesDto deleteRole(@PathVariable @NotBlank(message = "Login must not be blank") String login,
                               @PathVariable @NotBlank(message = "Role must not be blank") String role) {
        return userAccountService.changeRolesList(login, role, false);
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
        userAccountService.changePassword(principal.getName(), newPassword);
    }

    @GetMapping("/user/{login}")
    public UserDto getUser(@PathVariable String login) {
        return userAccountService.getUser(login);
    }

    @PostMapping("/email")
    public void sendEmail(@RequestBody @Valid EmailDto emailDto) {
        userAccountService.sendEmail(emailDto);
    }
}