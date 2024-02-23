package com.example.apirestspringboot;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rutasautobus")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String origen;
    private String destino;
    private Integer numParadas;
    private Integer inconveniente;

}
