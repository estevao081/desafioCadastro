package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetWeight {

    private String weight;

    public PetWeight(String weight) {
        try {
            validateWeight(weight);
            this.weight = weight;
        } catch (IllegalArgumentException e) {
            System.out.println("Peso inválido!");
        }
    }

    public PetWeight() { }

    public String getPetWeight() { return weight; }

    public void setPetWeight(String petWeight) { weight = petWeight; }

    private void validateWeight(String weight) {
        if(!weight.matches("^\\d,\\d{2}$")) {
            System.out.println("Peso inválido!");
        }
        if(weight.contains(".")) {
            weight = weight.replace(".",",");
        }
        double weightF = Double.parseDouble(weight);
        if(weightF <0.5 || weightF > 60) {
            System.out.println("Peso inválido!");
        }
    }

    @Override
    public String toString() {
        return weight;
    }
}