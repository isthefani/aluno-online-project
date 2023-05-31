package com.alunoonline.api.controller;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.MatriculaAluno;
import com.alunoonline.api.model.dtos.UpdateGradesRequestDto;
import com.alunoonline.api.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatriculaAluno> create(@RequestBody MatriculaAluno matriculaAluno, Aluno aluno) {
        MatriculaAluno matriculaAlunoCreated = service.create(matriculaAluno, aluno);

        return ResponseEntity.status(201).body(matriculaAlunoCreated);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void patch(@RequestBody UpdateGradesRequestDto gradesOnlyDto, @PathVariable Long id) {
        service.updateGrades(gradesOnlyDto, id);
    }
}
