package com.example.exercicioconhecimento.repositories;

import com.example.exercicioconhecimento.models.Pessoa;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class PessoaRepositoryImplements extends AbstractRepository<Pessoa, Long> implements PessoaRepository{

    @Override
    public Pessoa findByName(String nome) {
        try {
            return getEntityManager().createQuery("select d from Pessoa d where d.nome = '" + nome + "'", Pessoa.class).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}