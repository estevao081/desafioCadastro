package main.models;

import java.util.*;

public class PetFiltro {

    private static final String NA = "NÃO INFORMADO";

    private String nome;
    private String tipo;
    private String genero;
    private String endereco;
    private String idade;
    private String peso;
    private String raca;
    private String dataDeCadastro;
    private final Map<String, String> atributosExtras;

    public PetFiltro() {
        this.atributosExtras = new LinkedHashMap<>();
    }

    public void addAtributoExtra(String valor) {
        if (valor == null || valor.isBlank()) {
            atributosExtras.put(UUID.randomUUID().toString(), NA);
        } else {
            atributosExtras.put(UUID.randomUUID().toString(), valor.trim());
        }
    }

    public Collection<String> getAtributosExtras() {
        return Collections.unmodifiableCollection(atributosExtras.values());
    }

    @Override
    public String toString() {
        return nome + ", " +
                tipo + ", " +
                genero + ", " +
                endereco + ", " +
                idade + ", " +
                peso + ", " +
                raca +
                getAtributosExtras().toString().replace("[", ", ").replace("]", "");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(String dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }
}

