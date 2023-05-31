package com.alunoonline.api.service;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.MatriculaAluno;
import com.alunoonline.api.model.dtos.UpdateGradesRequestDto;
import com.alunoonline.api.repository.MatriculaAlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatriculaAlunoService {

    static final Double GRADEAVGTOAPPROVE = 7.0; // Constante para designar a nota mínima de aprovação do aluno

    @Autowired
    MatriculaAlunoRepository repository;

    public MatriculaAluno create(MatriculaAluno matriculaAluno, Aluno aluno) {
        return repository.save(matriculaAluno);
    }

    public void updateGrades(UpdateGradesRequestDto updateGradesRequestDto, Long matriculaAlunoId) {
        Optional<MatriculaAluno> matriculaAlunoToUpdate = repository.findById(matriculaAlunoId); // Pegar a matrícula do aluno e suas respectivas informações

        boolean needUpdate = false; // Variável para filtrar a necessidade de atualização das notas

        // Condições para ver se há um valor registrado em Nota 1 e em Nota 2
        if (updateGradesRequestDto.getNota1() != null) {
            matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setNota1(updateGradesRequestDto.getNota1()));
            needUpdate = true;
        }

        if (updateGradesRequestDto.getNota2() != null) {
            matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setNota2(updateGradesRequestDto.getNota2()));
            needUpdate = true;
        }

        // Condições para gerar a média e setar o novo status do aluno
        if (needUpdate) {
            if (matriculaAlunoToUpdate.get().getNota1() != null && matriculaAlunoToUpdate.get().getNota2() != null) {
                if ((matriculaAlunoToUpdate.get().getNota1() + matriculaAlunoToUpdate.get().getNota2()) / 2 >= GRADEAVGTOAPPROVE) {
                    matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setStatus("APROVADO"));
                } else {
                    matriculaAlunoToUpdate.ifPresent(matriculaAluno -> matriculaAluno.setStatus("REPROVADO"));
                }
            }

            repository.save(matriculaAlunoToUpdate.get());
        }
    }
}
