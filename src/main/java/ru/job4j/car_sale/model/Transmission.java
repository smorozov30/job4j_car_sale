package ru.job4j.car_sale.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transmissions")
public class Transmission {

    @Expose
    @Id
    private String name;

    public static Transmission of(String name) {
        Transmission transmission = new Transmission();
        transmission.name = name;
        return transmission;
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
        Transmission that = (Transmission) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Transmission{" +
                "name='" + name + '\'' +
                '}';
    }
}
