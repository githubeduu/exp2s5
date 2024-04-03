package com.example.exp2s5.service;

import java.util.List;
import java.util.Optional;

import com.example.exp2s5.model.Pelicula;

public interface PeliculaService {
    List<Pelicula> getAllPelicula();
    Optional<Pelicula> getPeliculaById(Long id);
    
}
