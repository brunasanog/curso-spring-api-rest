package tech.ada.java.demo.api;

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
    private String nome;
    private String email;
    private LocalDate dob;
}




