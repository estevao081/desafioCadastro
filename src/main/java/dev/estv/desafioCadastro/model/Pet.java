package dev.estv.desafioCadastro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PetName name;
    private String type;
    private String gender;
    private PetAddres addres;
    private PetAge age;
    private PetWeight weight;
    private PetRace race;

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", gender=" + gender +
                ", addres='" + addres + '\'' +
                ", age=" + age.toString() +
                ", weight=" + weight.toString() +
                ", race='" + race + '\'' +
                '}';
    }

    public Pet(Long id, PetName name, String type, String gender, PetAddres addres, PetAge age, PetWeight weight, PetRace race) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.addres = addres;
        this.age = age;
        this.weight = weight;
        this.race = race;
    }

    public Pet() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public PetName getName() { return name; }

    public void setName(PetName name) { this.name = name; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public PetAddres getAddres() { return addres; }

    public void setAddres(PetAddres addres) { this.addres = addres; }

    public PetAge getAge() { return age; }

    public void setAge(PetAge age) { this.age = age; }

    public PetWeight getWeight() { return weight; }

    public void setWeight(PetWeight weight) { this.weight = weight; }

    public PetRace getRace() { return race; }

    public void setRace(PetRace race) { this.race = race; }

    public static final class PetBuilder {
        private Long id;
        private PetName name;
        private String type;
        private String gender;
        private PetAddres addres;
        private PetAge age;
        private PetWeight weight;
        private PetRace race;

        private PetBuilder() {
        }

        public static PetBuilder petBuilder() {
            return new PetBuilder();
        }

        public PetBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PetBuilder name(PetName name) {
            this.name = name;
            return this;
        }

        public PetBuilder type(String type) {
            this.type = type;
            return this;
        }

        public PetBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public PetBuilder addres(PetAddres addres) {
            this.addres = addres;
            return this;
        }

        public PetBuilder age(PetAge age) {
            this.age = age;
            return this;
        }

        public PetBuilder weight(PetWeight weight) {
            this.weight = weight;
            return this;
        }

        public PetBuilder race(PetRace race) {
            this.race = race;
            return this;
        }

        public Pet build() {
            Pet pet = new Pet();
            pet.setId(id);
            pet.setName(name);
            pet.setType(type);
            pet.setGender(gender);
            pet.setAddres(addres);
            pet.setAge(age);
            pet.setWeight(weight);
            pet.setRace(race);
            return pet;
        }
    }
}