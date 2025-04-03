package dev.estv.desafioCadastro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class QuestionList {

    @Override
    public String toString() { return id + " - " + question; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    public QuestionList() {

    }

    public QuestionList(Long id, String question) {
        this.id = id;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question = question;
    }
}
