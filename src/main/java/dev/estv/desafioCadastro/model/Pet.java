package dev.estv.desafioCadastro.model;

import jakarta.persistence.*;

import java.util.Map;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PetName name;
    private PetType type;
    private PetSex sex;
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
                ", sex=" + sex +
                ", addres='" + addres + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", race='" + race + '\'' +
                '}';
    }

    public Pet(Long id, PetName name, PetType type, PetSex sex, PetAddres addres, PetAge age, PetWeight weight, PetRace race) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sex = sex;
        this.addres = addres;
        this.age = age;
        this.weight = weight;
        this.race = race;
    }

    public Pet() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name.getName(); }

    public void setName(PetName name) { this.name = name; }

    public PetType getType() { return type; }

    public void setType(PetType type) { this.type = type; }

    public PetSex getSex() { return sex; }

    public void setSex(PetSex sex) { this.sex = sex; }

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
        private PetType type;
        private PetSex sex;
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

        public PetBuilder type(PetType type) {
            this.type = type;
            return this;
        }

        public PetBuilder sex(PetSex sex) {
            this.sex = sex;
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
            pet.setSex(sex);
            pet.setAddres(addres);
            pet.setAge(age);
            pet.setWeight(weight);
            pet.setRace(race);
            return pet;
        }
    }
}