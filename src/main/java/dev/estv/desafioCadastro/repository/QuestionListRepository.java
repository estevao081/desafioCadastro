package dev.estv.desafioCadastro.repository;

import dev.estv.desafioCadastro.model.QuestionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionListRepository extends JpaRepository <QuestionList, Long> {
}
