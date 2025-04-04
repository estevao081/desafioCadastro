package dev.estv.desafioCadastro.service;

import dev.estv.desafioCadastro.model.PetGender;
import dev.estv.desafioCadastro.model.PetType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnumFormat {

    public static String typeFormat(PetType petType) {
        String type = petType.toString();
        PetType[] petTypeList = PetType.values();
        List<String> typeFound = new ArrayList<>();
        if (Arrays.toString(petTypeList).contains(type)) {
            typeFound.add(type);
        }
        return typeFound.toString().replace("[", "").replace("]", "");
    }

    public static String genderFormat(PetGender petGender) {
        String gender = petGender.toString();
        PetGender[] petGenderList = PetGender.values();
        List<String> genderFound = new ArrayList<>();
        if (Arrays.toString(petGenderList).contains(gender)) {
            genderFound.add(gender);
        }
        return genderFound.toString().replace("[", "").replace("]", "");
    }
}