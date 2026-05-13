package com.sivebo.ms_comunas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sivebo.ms_comunas.repository.ComunaRepository;
import com.sivebo.ms_comunas.model.Comuna;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{
        
        private final ComunaRepository comunaRepository;

        @Override
        public void run(String... args){
                if(comunaRepository.count() > 0) {
                        log.info(">>> comunas ya cargadas. Se omite inicialización.");
                        return;
                }
                log.info(">>> Cargando comunas iniciales...");
                comunaRepository.save(new Comuna(
                        null,
                        "Santiago",
                        1L
                ));
                comunaRepository.save(new Comuna(
                        null,
                        "Providencia",
                        2L
                ));
                log.info(">>> Comunas iniciales cargadas exitosamente.");
        }
}
