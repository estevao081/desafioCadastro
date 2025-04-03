package dev.estv.desafioCadastro.model;

import jakarta.persistence.Embeddable;

import java.util.Map;

@Embeddable
public class PetOthers {

    private Map<String, String> others;

    public PetOthers(Map<String, String> others) { this.others = others; }

    public void petOthers() { }

    public Map<String, String> getOthers() { return others; }

    public void setOthers(Map<String, String> others) { this.others = others; }
}
