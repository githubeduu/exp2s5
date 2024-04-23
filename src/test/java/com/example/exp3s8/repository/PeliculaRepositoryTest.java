package com.example.exp3s8.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.exp3s8.model.Pelicula;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    public void crearPeliculaTest(){
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Rapido y furioso 1");

        Pelicula resultado = peliculaRepository.save(pelicula);

        assertNotNull(resultado.getId());
        assertEquals("Rapido y furioso 1", resultado.getTitulo());
    }
}
