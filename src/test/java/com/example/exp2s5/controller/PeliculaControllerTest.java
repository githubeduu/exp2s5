package com.example.exp2s5.controller;

import java.util.List;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

import com.example.exp2s5.model.Pelicula;
import com.example.exp2s5.service.PeliculaServicelmpl;

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculaServicelmpl peliculaServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception{

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setTitulo("El padrino");
        pelicula1.setId(1L);
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Star wars iv");
        pelicula2.setId(2L);
        List<Pelicula> peliculas = Arrays.asList(pelicula1,pelicula2);
        when(peliculaServiceMock.getAllPelicula()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("El padrino")))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Star wars iv")));
    } 
    
}
