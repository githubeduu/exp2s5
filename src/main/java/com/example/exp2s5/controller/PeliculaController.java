package com.example.exp2s5.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.exp2s5.model.Pelicula;
import com.example.exp2s5.service.PeliculaService;

import java.util.stream.Collectors;
import java.util.Collections;


import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/pelicula")
public class PeliculaController {

    private static final Logger log = LoggerFactory.getLogger(PeliculaController.class);

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> getAllPeliculas() {
        List<Pelicula> peliculas = peliculaService.getAllPelicula();
        log.info("GET /peliculas");
        log.info("Retornando todas las peliculas");
        List<EntityModel<Pelicula>> peliculasResources = peliculas.stream()
                .map(pelicula -> EntityModel.of(pelicula,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculasResources,
                linkTo.withRel("Peliculas"));

        return resources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = peliculaService.getPeliculaById(id);
        if (pelicula.isPresent()) {
            EntityModel<Pelicula> resource = EntityModel.of(pelicula.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id))
                            .withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas())
                            .withRel("all-peliculas"));
            return ResponseEntity.ok(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pelicula no encontrada");
        }
    }

    @PostMapping
    public ResponseEntity<?> createPelicula(@Validated @RequestBody Pelicula pelicula) {
        try {
            Pelicula nuevaPelicula = peliculaService.createPelicula(pelicula);
            EntityModel<Pelicula> resource = EntityModel.of(nuevaPelicula,
                    WebMvcLinkBuilder
                            .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(nuevaPelicula.getId()))
                            .withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas())
                            .withRel("all-peliculas"));
            return ResponseEntity.status(HttpStatus.CREATED).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la pelicula");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);
        EntityModel<Pelicula> resource = EntityModel.of(updatedPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas())
                        .withRel("all-peliculas"));
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.ok().body(
                CollectionModel.of(Collections.emptyList(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPeliculas())
                                .withRel("all-peliculas")));
    }
}
