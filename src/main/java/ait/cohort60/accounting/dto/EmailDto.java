package ait.cohort60.accounting.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class EmailDto {
    @NotBlank
    @Email
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;

}
