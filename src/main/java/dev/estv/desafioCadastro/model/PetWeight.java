package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetWeight {

    private Double weight;

    public PetWeight(Double weight) {
        try {
            validateWeight(weight);
            this.weight = weight;
        } catch (IllegalArgumentException e) {
            System.out.println("Peso inválido!");
        }
    }

    public PetWeight() { }

    public Double getPetWeight() { return weight; }

    public void setPetWeight(Double petWeight) { weight = petWeight; }

    private void validateWeight(Double weight) {
        if(!String.valueOf(weight).contains(",")) {
            this.weight = Double.parseDouble(String.valueOf(weight).replace(",", "."));
        }
        if(weight <0.5 || weight > 60) {
            System.out.println("Peso inválido!");
        }
    }

    @Override
    public String toString() {
        return "PetWeight{" +
                "weight=" + weight +
                '}';
    }
}