package com.sivebo.ms_comunas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sivebo.ms_comunas.dto.ComunaRequestDTO;
import com.sivebo.ms_comunas.dto.ComunaResponseDTO;
import com.sivebo.ms_comunas.model.Comuna;
import com.sivebo.ms_comunas.repository.ComunaRepository;
import com.sivebo.ms_comunas.utils.WebClientUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComunaService {

        private final ComunaRepository comunaRepository;

        private ComunaResponseDTO mapToDTO(Comuna comuna) {
                return new ComunaResponseDTO(
                        comuna.getId(), 
                        comuna.getNombre(), 
                        comuna.getIdRegion()
                );
        }

        public List<ComunaResponseDTO> getAllComunas() {
                return comunaRepository.findAll()
                        .stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        public Optional<ComunaResponseDTO> getById(Long id) {
                return comunaRepository.findById(id).map(this::mapToDTO);
        }

        public List<ComunaResponseDTO> getByRegionId(Long idRegion) {
                return comunaRepository.findByIdRegion(idRegion)
                        .stream()
                        .map(this::mapToDTO)
                        .collect(Collectors.toList());
        }

        public Optional<ComunaResponseDTO> getByNombre(String nombre) {
                return comunaRepository.findByNombre(nombre).map(this::mapToDTO);
        }

        public ComunaResponseDTO create(ComunaRequestDTO dto) {
                WebClientUtil.validateMicroService(dto.getIdRegion(), "region");
                return mapToDTO(comunaRepository.save(
                        new Comuna(
                                null,
                                dto.getNombre(), 
                                dto.getIdRegion()
                        )
                ));
        }

        public Optional<ComunaResponseDTO> update(Long id, ComunaRequestDTO dto) {
                return comunaRepository.findById(id).map(comuna -> {
                        WebClientUtil.validateMicroService(dto.getIdRegion(), "region");
                        comuna.setNombre(dto.getNombre());
                        comuna.setIdRegion(dto.getIdRegion());
                        return mapToDTO(comunaRepository.save(comuna));
                });
        }

        public boolean delete(Long id) {
		comunaRepository.deleteById(id);
                return !comunaRepository.existsById(id);
        }
        
}
