package br.com.bandtec.boletimapi.repository;

import br.com.bandtec.boletimapi.entity.Boletim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author José Yoshiriro
 */
public interface BoletimRepository extends JpaRepository<Boletim, Long> {

    boolean existsByIdAndToken(Long id, String token);

    List<Boletim> findByTokenOrderByAluno(String token);

    Boletim findByIdAndToken(Long id, String token);

    List<Boletim> findByAlunoLikeAndTokenOrderByAluno(String aluno, String token);

    void deleteByIdAndToken(Long id, String token);
}
