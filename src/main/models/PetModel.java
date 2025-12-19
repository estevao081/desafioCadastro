package main.models;

public class PetModel {

    private String name;
    private PetType type;
    private PetGender gender;
    private String address;
    private String age;
    private String weight;
    private String race;

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

    public PetModel() {
    }

    public PetModel(String name, PetType type, PetGender gender, String address, String age, String weight, String race) {
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
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetGender getGender() {
        return gender;
    }

    public void setGender(PetGender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public enum PetType {
        CAO, GATO
    }

    public enum PetGender {
        M, F
    }
}
