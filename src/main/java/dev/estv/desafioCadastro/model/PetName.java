package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetName {

    private String name;

    public PetName(String name) {
        try {
            validateName(name);
            this.name = name;
        } catch (IllegalArgumentException e) {
            System.out.println("Nome inválido!");
        }
    }

    public PetName() { }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    private void validateName(String name) {
        if (!name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            System.out.println("Este campo não pode conter caracteres especiais e não pode ficar em bracno.");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}