package com.app.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_requests_table_final")
public class UserRequestEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private Double number;

    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    public UserRequestEntity() {}

    public UserRequestEntity(Double number, String source, String dest) {
        this.number = number;
        this.source = source;
        this.destination = dest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, source, destination);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserRequestEntity request = (UserRequestEntity) obj;
        return Objects.equals(number, request.number) &&
                Objects.equals(source, request.source) &&
                Objects.equals(destination, request.destination);
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
