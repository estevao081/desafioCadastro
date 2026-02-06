package main.models;

import main.exceptions.*;

import java.util.List;

public class Pet {

    private String name;
    private PetType type;
    private PetGender gender;
    private String address;
    private String age;
    private String weight;
    private String race;
    final String NA = "NÃO INFORMADO";

    @Override
    public String toString() {
        return "PetModel{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", race='" + race + '\'' +
                '}';
    }

    public Pet() {
    }

    public Pet(String name, PetType type, PetGender gender, String address, String age, String weight, String race) {
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!name.matches("^$|^[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+$")) {
            throw new InvalidNameException();
        }
        if (name.isEmpty()) {
            name = NA;
        }
        this.name = name.toUpperCase();
    }

    public PetType getType() {
        return type;
    }

    public void setType(String input) {
        this.type = PetType.fromString(input);
    }

    public PetGender getGender() {
        return gender;
    }

    public void setGender(String input) {
        this.gender = PetGender.fromString(input);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!address.matches("^$|^[A-Za-zÀ-ÿ0-9\\s\\.]+,\\s*\\d+[A-Za-z0-9\\s\\-]*,\\s*[A-Za-zÀ-ÿ\\s]+$")) {
            throw new InvalidAddressException();
        }
        if (address.isEmpty()) {
            address = NA;
        }

        this.address = address.toUpperCase();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        if (age.contains(",")) {
            age = age.replace(",", ".");
        }

        if (!age.isEmpty()) {
            if (Double.parseDouble(age) > 20 || Double.parseDouble(age) < 0.1) {
                throw new InvalidAgeException();
            }
        }

        if (age.isEmpty()) {
            age = NA;
        }
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        if (weight.contains(",")) {
            weight = weight.replace(",", ".");
        }

        if (!weight.isEmpty()) {
            if (Double.parseDouble(weight) > 60 || Double.parseDouble(weight) < 0.5) {
                throw new InvalidWeightException();
            }
        }

        if (weight.isEmpty()) {
            weight = NA;
        }
        this.weight = weight;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        if (!race.matches("^$|^[A-Za-zÀ-ÿ\\s'-]+$")) {
            throw new InvalidRaceException();
        }

        if (race.isEmpty()) {
            race = NA;
        }
        this.race = race.toUpperCase();
    }

    public enum PetType {
        CAO, GATO;

        public static PetType fromString(String input) {
            if (input == null) {
                throw new IllegalArgumentException("ERRO: Valor não pode ser nulo");
            }

            return switch (input.trim().toLowerCase()) {
                case "cao", "cão", "cachorro" -> CAO;
                case "gato" -> GATO;
                default -> throw new InvalidTypeException();
            };
        }
    }

    public enum PetGender {
        M, F;

        public static PetGender fromString(String input) {
            if (input == null) {
                throw new IllegalArgumentException("ERRO: Valor não pode ser nulo");
            }

            return switch (input.trim().toLowerCase()) {
                case "m", "masculino", "macho" -> M;
                case "f", "feminino", "fêmea", "femea" -> F;
                default -> throw new InvalidGenderException();
            };
        }
    }

    public List<String> toLinhas() {
        return List.of(
                name,
                String.valueOf(type),
                String.valueOf(gender),
                address,
                age,
                weight,
                race
        );
    }

}
