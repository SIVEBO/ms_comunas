package com.sivebo.ms_comunas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sivebo.ms_comunas.model.Comuna;

public interface ComunaRepository extends JpaRepository<Comuna, Long> {

        List<Comuna> findByIdRegion(Long idRegion);

        Optional<Comuna> findByNombre(String nombre);
        
}
