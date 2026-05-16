package com.sivebo.ms_comunas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sivebo.ms_comunas.dto.ComunaRequestDTO;
import com.sivebo.ms_comunas.dto.ComunaResponseDTO;
import com.sivebo.ms_comunas.service.ComunaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/api/v1/comunas")
@RequiredArgsConstructor
public class ComunaController {

        private final ComunaService comunaService;

        @GetMapping
        public List<ComunaResponseDTO> getAll() {
                return comunaService.getAllComunas();
        }

        @GetMapping("{id}")
        public ResponseEntity<ComunaResponseDTO> getById(@PathVariable Long id) {
                return comunaService.getById(id)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/search")
        public ResponseEntity<?> getByAtribute(
                @RequestParam(required = false) Long idRegion,
                @RequestParam(required = false) String nombre){

                if (idRegion == null && nombre == null){
                        return ResponseEntity.badRequest().body("Debe proporcionar un atrinbuto de búsqueda valido");
                }else if (idRegion != null && nombre != null){
                        return ResponseEntity.badRequest().body("Solo se permite un atributo de búsqueda a la vez");
                }else if (nombre != null){
                        log.info(">>> Buscando comuna por nombre: {}", nombre);
                        return comunaService.getByNombre(nombre)
                                .map(ResponseEntity::ok)
                                .orElse(ResponseEntity.notFound().build());
                }else{
                        log.info(">>> Buscando comuna por idRegion: {}", idRegion);
                        return ResponseEntity.ok(comunaService.getByRegionId(idRegion));
                }
        }

        @PostMapping
        public ResponseEntity<ComunaResponseDTO> create(@Valid @RequestBody ComunaRequestDTO dto) {
                return ResponseEntity.status(HttpStatus.CREATED).body(comunaService.create(dto));
        }

        @PutMapping("/{id}")
        public ResponseEntity<ComunaResponseDTO> update(
                @PathVariable Long id,
                @Valid @RequestBody ComunaRequestDTO dto) {
                
                        return comunaService.update(id, dto)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> delete(@PathVariable Long id) {
                if (comunaService.delete(id)) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Comuna eliminada");
                } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comuna no encontrada o no se pudo eliminar");
                }
        }
}
