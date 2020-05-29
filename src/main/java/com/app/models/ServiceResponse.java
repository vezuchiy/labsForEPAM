package com.app.models;


import javax.persistence.*;

@Entity
@Table(name = "service_responses_table")
public class ServiceResponse {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "result_number")
    private Double resultNumber;

    @Column(name = "final_comment")
    private String  finalComment;

    public ServiceResponse() {}

    public ServiceResponse(Double value) {
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
}
