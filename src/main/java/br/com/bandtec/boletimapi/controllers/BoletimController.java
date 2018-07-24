package br.com.bandtec.boletimapi.controllers;

import br.com.bandtec.boletimapi.entity.Boletim;
import br.com.bandtec.boletimapi.repository.BoletimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author José Yoshiriro
 */
@RestController
@RequestMapping({"/boletinsbom", "/boletins"})
public class BoletimController {

    @Autowired
    private BoletimRepository repository;

    @GetMapping
    public ResponseEntity getVarios(
            @RequestParam(value = "aluno", required = false) String aluno,
            @RequestHeader("token") String token) {
        if (aluno == null) {
            if (repository.count() > 0) {
                return ResponseEntity.ok(repository.findByTokenOrderByAluno(token));
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            List<Boletim> resultado = repository.
                    findByAlunoLikeAndTokenOrderByAluno("%"+aluno+"%", token);
            if (!resultado.isEmpty()) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUm(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        Boletim boletim = repository.findByIdAndToken(id, token);

        if (boletim != null) {
            return ResponseEntity.ok(boletim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    public ResponseEntity criar(@RequestBody Boletim boletim, @RequestHeader("token") String token) {
        boletim.setToken(token);
        repository.save(boletim);
        return ResponseEntity.status(HttpStatus.CREATED).body(boletim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id, @RequestHeader("token") String token) {
        if (repository.existsByIdAndToken(id, token)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Boletim " + id + " não encontrado ou é de outro dono");
        }
    }
}
