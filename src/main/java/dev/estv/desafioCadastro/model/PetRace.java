package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetRace {

    private String race;

    public PetRace(String race) {
        try {
            validateRace(race);
            this.race = race;
        } catch (IllegalArgumentException e) {
            System.out.println("Informação inválida");
        }
    }

    public PetRace() { }

    public String getRace() { return race; }

    public void setRace(String race) { this.race = race; }

    private void validateRace(String race) {
        if (!race.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            System.out.println("Não pode conter caracteres especiais, somente letras de A-Z.");
        }
    }

    @Override
    public String toString() {
        return race;
    }
}
