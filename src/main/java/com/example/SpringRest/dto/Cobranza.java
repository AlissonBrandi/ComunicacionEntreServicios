package com.example.SpringRest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cobranza {

    private int numero;
    private String fecha;
    private ClienteDTO cliente;
    private Inmueble inmueble;


}
