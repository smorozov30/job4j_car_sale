package ru.job4j.car_sale.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bodies")
public class Body {

    @Expose
    @Id
    private String name;

    public static Body of(String name) {
        Body body = new Body();
        body.name = name;
        return body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body body = (Body) o;
        return Objects.equals(name, body.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Body{" +
                "name='" + name + '\'' +
                '}';
    }
}
