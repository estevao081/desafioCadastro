package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetAge {

    private String age;

    public PetAge(String age) {
        try {
            validateAge(age);
            this.age = age;
        } catch (IllegalArgumentException e) {
            System.out.println("Idade inválida!");
        }
    }

    public PetAge() { }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    private void validateAge(String age) {
        if(!age.matches("^\\d,\\d{2}$")
                || !age.matches("^\\d+$")) {
            System.out.println("Idade inválida!");
        }
        if(age.contains(".")) {
            age = age.replace(".",",");
        }
        int ageF = Integer.parseInt(age);
        if(ageF <0 || ageF > 20) {
            System.out.println("Idade inválida!");
        }
    }

    @Override
    public String toString() {
        return age;
    }
}