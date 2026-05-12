package com.sivebo.ms_comunas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComunaRequestDTO {
        
	@NotBlank(message="El nombre de la comuna no puede estar vacío.")
	String nombre;

	@NotNull(message="El id de la región no puede ser nulo.")
	Long idRegion;

}
