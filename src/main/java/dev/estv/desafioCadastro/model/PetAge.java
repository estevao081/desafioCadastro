package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetAge {

    private Integer age;

    public PetAge(Integer age) {
        try {
            validateAge(age);
            this.age = age;
        } catch (IllegalArgumentException e) {
            System.out.println("Idade inválida!");
        }
    }

    public PetAge() { }

    public Integer getAge() { return age; }

    public void setAge(Integer age) { this.age = age; }

    private void validateAge(Integer age) {
        if(String.valueOf(age).matches("^\\d,\\d$")) {
            this.age = (int) Double.parseDouble(String.valueOf(age));
        }
        if(age <0 || age > 20) {
            System.out.println("Idade inválida!");
        }
    }

    @Override
    public String toString() {
        return "PetAge{" +
                "age=" + age;
    }
}