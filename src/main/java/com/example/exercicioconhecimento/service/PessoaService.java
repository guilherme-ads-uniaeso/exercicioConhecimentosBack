package com.example.exercicioconhecimento.service;

import com.example.exercicioconhecimento.models.Pessoa;

import java.util.List;
import java.util.UUID;

public interface PessoaService {

    Pessoa save(Pessoa pessoa);

    Pessoa update(Pessoa pessoa);

    void delete(UUID id);

    Pessoa findById(UUID id);

    List<Pessoa> findAll();

    Pessoa findByName(String nome);
}