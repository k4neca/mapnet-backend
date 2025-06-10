package com.mapnet.controller;

import com.mapnet.model.Avaliacao;
import com.mapnet.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository repository;

    @GetMapping
    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Avaliacao criar(@RequestBody Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }
}

