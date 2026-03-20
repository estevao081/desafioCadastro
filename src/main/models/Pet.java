package main.models;

import main.exceptions.*;

import java.util.*;

public class Pet {

    private static final String NA = "NÃO INFORMADO";

    private String name;
    private PetType type;
    private PetGender gender;
    private String address;
    private String age;
    private String weight;
    private String race;
    private String registrationDate;
    private final Map<String, String> atributosExtras;

    public Pet() {
        this.atributosExtras = new LinkedHashMap<>();
    }

    public Pet(String name, PetType type, PetGender gender, String address, String age, String weight, String race) {

        this();
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.race = race;
    }

    public void setName(String name) {
        if (!name.matches("^$|^[A-Za-zÀ-ÿ]+ [A-Za-zÀ-ÿ]+$")) {
            throw new InvalidNameException();
        }
        this.name = normalize(name).toUpperCase();
    }

    public void setType(String input) {
        this.type = PetType.fromString(input);
    }

    public void setGender(String input) {
        this.gender = PetGender.fromString(input);
    }

    public void setAddress(String address) {
        if (!address.matches("^(NÃO INFORMADO|[A-Za-zÀ-ÿ0-9\\s]+,\\s*[A-Za-zÀ-ÿ0-9\\s]+,\\s*[A-Za-zÀ-ÿ0-9\\s]+)?$")) {
            throw new InvalidAddressException();
        }
        this.address = normalize(address).toUpperCase();
    }

    public void setAge(String age) {
        this.age = validateNumber(age, 0.1, 20, new InvalidAgeException());
    }

    public void setWeight(String weight) {
        this.weight = validateNumber(weight, 0.5, 60, new InvalidWeightException());
    }

    public void setRace(String race) {
        if (!race.matches("^$|^[A-Za-zÀ-ÿ\\s'-]+$")) {
            throw new InvalidRaceException();
        }
        this.race = normalize(race).toUpperCase();
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public PetGender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getRace() {
        return race;
    }

    public String getRegistrationDate() {
        return registrationDate;
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

    public List<String> toLinhas() {
        List<String> linhas = new ArrayList<>();

        linhas.add(name);
        linhas.add(String.valueOf(type));
        linhas.add(String.valueOf(gender));
        linhas.add(address);
        linhas.add(age + " anos");
        linhas.add(weight + "kg");
        linhas.add(race);

        linhas.addAll(atributosExtras.values());

        return linhas;
    }

    private String normalize(String value) {
        return value == null || value.isBlank() ? NA : value.trim();
    }

    private String validateNumber(String input, double min, double max, RuntimeException ex) {
        if (input == null || input.isBlank()) {
            return NA;
        }

        input = input.replace(",", ".");

        double value = Double.parseDouble(input);
        if (value < min || value > max) {
            throw ex;
        }

        return input;
    }

    public enum PetType {
        CAO, GATO;

        public static PetType fromString(String input) {
            if (input == null) {
                throw new IllegalArgumentException("Valor não pode ser nulo");
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
                throw new IllegalArgumentException("Valor não pode ser nulo");
            }

            return switch (input.trim().toLowerCase()) {
                case "m", "masculino", "macho" -> M;
                case "f", "feminino", "fêmea", "femea" -> F;
                default -> throw new InvalidGenderException();
            };
        }
    }
}
