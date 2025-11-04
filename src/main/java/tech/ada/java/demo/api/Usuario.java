package tech.ada.java.demo.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID; // é uma classe que gera ids únicos

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {
    private UUID uuid;
    @NotBlank
    private String nome;
    @Email
    private String email;
    @Past
    private LocalDate dob;
}




