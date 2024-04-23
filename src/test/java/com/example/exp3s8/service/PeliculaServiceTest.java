package com.example.exp3s8.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.example.exp3s8.model.Pelicula;
import com.example.exp3s8.repository.PeliculaRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {
    @InjectMocks
    private PeliculaServicelmpl peliculaServicio;

    @Mock
    private PeliculaRepository peliculaRepositoryMock;

    @Test
    public void crearPeliculaTest(){

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Batman");

        when(peliculaRepositoryMock.save(any())).thenReturn(pelicula);

        Pelicula resultado = peliculaServicio.createPelicula(pelicula);

        assertEquals("batman", resultado.getTitulo());

    }

    
}
