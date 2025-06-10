package com.mapnet.controller;

import com.mapnet.model.Dica;
import com.mapnet.repository.DicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dicas")
@CrossOrigin(origins = "*")
public class DicaController {

    @Autowired
    private DicaRepository repository;

    @GetMapping
    public List<Dica> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Dica criar(@RequestBody Dica dica) {
        return repository.save(dica);
    }
}

