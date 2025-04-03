package dev.estv.desafioCadastro.repository;

import dev.estv.desafioCadastro.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository <Pet,Long> {
}
