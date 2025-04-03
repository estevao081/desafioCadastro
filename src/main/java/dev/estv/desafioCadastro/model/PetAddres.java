package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class PetAddres {


    private String addres;

    public PetAddres(String addres) { this.addres = addres; }

    public PetAddres() { }

    public String getAddres() { return addres; }

    public void setAddres(String addres) { this.addres = addres; }

    @Override
    public String toString() {
        return addres;
    }
}
