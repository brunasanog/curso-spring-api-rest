package tech.ada.java.demo.api;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Postagem {

    private UUID uuid;
    private String titulo;
    private String corpo;
    private LocalDate dataCriacao;
    private Usuario autor;
}
