package com.example.exp2s5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exp2s5.model.Pelicula;
import com.example.exp2s5.repository.PeliculaRepository;

@Service
public class PeliculaServicelmpl implements PeliculaService{
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPelicula(){
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id){
        return peliculaRepository.findById(id);
    }
    
}
