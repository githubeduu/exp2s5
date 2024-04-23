package com.example.exp3s8.service;

import java.util.List;
import java.util.Optional;

import com.example.exp3s8.model.Pelicula;

public interface PeliculaService {
    List<Pelicula> getAllPelicula();
    Optional<Pelicula> getPeliculaById(Long id);
    Pelicula createPelicula(Pelicula pelicula); 
    Pelicula updatePelicula(Long id, Pelicula pelicula);
    void deletePelicula(Long id);
	
    
}

