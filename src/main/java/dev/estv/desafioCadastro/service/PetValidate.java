package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.PetGender;
import dev.estv.desafioCadastro.model.PetType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PetValidate {

    final String NA = "NÃO INFORMADO";

    public String name(String name) {
        if (!name.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new IllegalArgumentException("ERRO: O campo NOME não pode conter caracteres especiais e não pode ficar em branco.\n");
        }
        return name;
    }

    public String type(String type) {
        PetType[] petTypeList = PetType.values();
        if (!Arrays.toString(petTypeList).contains(type)) {
            throw new IllegalArgumentException("ERRO: O campo TIPO deve ser preenchido com um dos tipos informados.\n");
        }
        return type;
    }

    public String gender(String gender) {
        PetGender[] petGenderList = PetGender.values();
        if (!Arrays.toString(petGenderList).contains(gender)) {
            throw new IllegalArgumentException("ERRO: O campo GÊNERO deve ser preenchido com um dos tipos informados.\n");
        }
        return gender;
    }

    public String addres(String addres) {
        if (!addres.matches("^$|^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s,.-]+$")) {
            throw new IllegalArgumentException("ERRO: O campo ENDEREÇO não pode conter caracteres especiais.\n");
        }
        if (addres.isEmpty()) {
            addres = NA;
        }
        return addres;
    }

    public String age(String age) {
        if (age.isEmpty()) {
            age = NA;
        }
        if (!age.equals(NA)) {
            if (!age.matches("^[0-9]{1,2}([.,][0-9]{1,2})?$")) {
                throw new IllegalArgumentException("ERRO: O campo IDADE deve conter apenas números válidos.\n");
            }
            if (age.contains(",")) {
                age = age.replace(",", ".");
            }
            if (Double.parseDouble(age) > 20 || Double.parseDouble(age) < 0) {
                throw new IllegalArgumentException("ERRO: Informe uma idade válida.\n");
            }
        }
        return age;
    }

    public String weight(String weight) {
        if (weight.isEmpty()) {
            weight = NA;
        }
        if (!weight.equals(NA)) {
            if (!weight.matches("^[0-9]{1,2}([.,][0-9]{1,2})?$")) {
                throw new IllegalArgumentException("ERRO: O campo PESO deve conter apenas números válidos.\n");
            }
            if (weight.contains(",")) {
                weight = weight.replace(",", ".");
            }
            if (Double.parseDouble(weight) > 60 || Double.parseDouble(weight) < 0) {
                throw new IllegalArgumentException("ERRO: Informe um peso válid0.\n");
            }
        }
        return weight;
    }

    public String race(String race) {
        if (!race.matches("^$|^[A-Za-zÀ-ÖØ-öø-ÿ\\\\s]+$")) {
            throw new IllegalArgumentException("ERRO: O campo RAÇA não pode conter caracteres especiais, somente letras de A-Z.\n");
        }
        if (race.isEmpty()) {
            race = NA;
        }
        return race;
    }
}