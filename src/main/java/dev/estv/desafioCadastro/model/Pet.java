package dev.estv.desafioCadastro.model;

import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String gender;
    private String addres;
    private String age;
    private String weight;
    private String race;

    @Override
    public String toString() {
        return "id = " + id +
                ", Nome = " + name +
                ", Tipo = " + type +
                ", Gênero = " + gender +
                ", Endereço = " + addres +
                ", Idade = " + age +
                ", Peso = " + weight +
                ", Raça = " + race;
    }

    public Pet(Long id, String name, String type, String gender, String addres, String age, String weight, String race) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.addres = addres;
        this.age = age;
        this.weight = weight;
        this.race = race;
    }

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
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


    public static final class PetBuilder {
        private Long id;
        private String name;
        private String type;
        private String gender;
        private String addres;
        private String age;
        private String weight;
        private String race;

        private PetBuilder() {
        }

        public static PetBuilder petBuilder() {
            return new PetBuilder();
        }

        public PetBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PetBuilder name(String name) {
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

        public PetBuilder addres(String addres) {
            this.addres = addres;
            return this;
        }

        public PetBuilder age(String age) {
            this.age = age;
            return this;
        }

        public PetBuilder weight(String weight) {
            this.weight = weight;
            return this;
        }

        public PetBuilder race(String race) {
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