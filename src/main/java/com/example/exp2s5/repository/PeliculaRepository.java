package com.example.exp2s5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exp2s5.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
}

