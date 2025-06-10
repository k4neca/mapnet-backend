package com.mapnet.controller;

import com.mapnet.model.Denuncia;
import com.mapnet.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/denuncias")
@CrossOrigin(origins = "*")
public class DenunciaController {

    @Autowired
    private DenunciaRepository repository;

    @GetMapping
    public List<Denuncia> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Denuncia criar(@RequestBody Denuncia denuncia) {
        return repository.save(denuncia);
    }
}

