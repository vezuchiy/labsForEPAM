package com.app.entities;


import javax.persistence.*;

@Entity
@Table(name = "service_responses_table")
public class ServiceResponseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "result_number")
    private Double resultNumber;

    @Column(name = "final_comment")
    private String  finalComment;

    public ServiceResponseEntity() {}

    public ServiceResponseEntity(Double value) {
        this.resultNumber = value;
    }

    public Double getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(Double resultNumber) {
        this.resultNumber = resultNumber;
    }

    public void setComment(String comment) {
        this.finalComment = comment;
    }

    public String getComment() {
        return this.finalComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
