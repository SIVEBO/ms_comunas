package com.sivebo.ms_comunas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comunas")
public class Comuna {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        Long id;

        @Column(name = "nombre_comuna", nullable=false, length=30)
        String nombre;

        @Column(name = "id_region", nullable=false)
        Long id_region;
}
