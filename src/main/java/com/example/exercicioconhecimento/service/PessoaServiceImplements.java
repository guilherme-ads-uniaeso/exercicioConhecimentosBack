package com.example.exercicioconhecimento.service;


import com.example.exercicioconhecimento.models.Pessoa;
import com.example.exercicioconhecimento.repositories.PessoaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = false)
public class PessoaServiceImplements implements PessoaService{
    private static final Logger logger = LogManager.getLogger(PessoaServiceImplements.class.getName());

    @Autowired
    private PessoaRepository repository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return repository.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return repository.update(pessoa);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }

    @Override
    public Pessoa findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Pessoa findByName(String nome) {
        return repository.findByName(nome);
    }

    @Override
    public List<Pessoa> findAll() {
        return repository.findAll();
    }
}