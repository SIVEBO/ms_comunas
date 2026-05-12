package com.sivebo.ms_comunas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComunaResponseDTO {

        Long id;
        String nombre;
        Long idRegion;
}
