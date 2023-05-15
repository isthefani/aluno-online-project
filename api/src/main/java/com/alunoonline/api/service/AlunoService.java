package com.alunoonline.api.service;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository repository;

    public Aluno create(Aluno aluno) {
        return repository.save(aluno);
    }

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Optional<Aluno> findById(Long id) {
        return repository.findById(id);
    }

    public Aluno update(Long id, Aluno alunoUpdated) {
        Optional<Aluno> optionalAluno = repository.findById(id);
        Aluno aluno = optionalAluno.get();
        aluno.setNome(alunoUpdated.getNome());
        aluno.setEmail(alunoUpdated.getEmail());
        aluno.setCurso(alunoUpdated.getCurso());
        Aluno alunoSaved = repository.save(aluno);
        return alunoSaved;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

