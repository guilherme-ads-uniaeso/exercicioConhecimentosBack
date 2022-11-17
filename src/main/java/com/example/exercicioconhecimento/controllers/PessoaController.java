package com.example.exercicioconhecimento.controllers;


import com.example.exercicioconhecimento.models.Pessoa;
import com.example.exercicioconhecimento.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/exercicioConhecimentos/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody Pessoa pessoa) {
        Map<HttpStatus, String> message = new HashMap<>();
        Pessoa base = null;
        base = service.findByName(pessoa.getNome());
        if(base != null) {
            if(base.getNome().equals(pessoa.getNome())) {
                message.put(HttpStatus.CONFLICT, "Nome de pessoa já existente");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            }
        }

        service.save(pessoa);
        message.put(HttpStatus.CREATED, "Pessoa cadastrada com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Pessoa pessoa) {
        Map<HttpStatus, String> message = new HashMap<>();
        Pessoa base = service.findById(pessoa.getIdPessoa());
        if(base == null) {
            message.put(HttpStatus.NOT_FOUND, "Pessoa não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        if(base.getNome().equals(pessoa.getNome())) {
            message.put(HttpStatus.CONFLICT, "Nomes iguais! Alteração não aconteceu.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        service.update(pessoa);
        message.put(HttpStatus.ACCEPTED, "Pessoa alterada com sucesso");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") UUID idPessoa) {
        Map<HttpStatus, String> message = new HashMap<>();
        Pessoa base = service.findById(idPessoa);
        if(base == null) {
            message.put(HttpStatus.CONFLICT, "Pessoa não encontrada");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }

        service.delete(idPessoa);
        message.put(HttpStatus.ACCEPTED, "Pessoa removida com sucesso");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        Map<HttpStatus, String> message = new HashMap<>();
        Pessoa base = service.findById(id);
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Pessoa não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping("/find/nome/{nome}")
    public ResponseEntity<Object> findByName(@PathVariable(value = "nome") String nome) {
        Map<HttpStatus, String> message = new HashMap<>();
        if (nome.isEmpty()) {
            message.put(HttpStatus.CONFLICT, "Deve informar o nome da pessoa para realizar a pesquisa!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        Pessoa base = null;
        if (base == null) {
            message.put(HttpStatus.NOT_FOUND, "Pessoa não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.OK).body(base);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<Pessoa>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
}

