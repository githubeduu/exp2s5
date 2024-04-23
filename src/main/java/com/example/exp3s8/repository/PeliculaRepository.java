package com.example.exp3s8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exp3s8.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
}

