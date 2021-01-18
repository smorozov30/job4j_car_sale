package ru.job4j.car_sale.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "makes")
public class Make {

    @Expose
    @Id
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Model> models = new ArrayList<>();

    public static Make of(String name) {
        Make mark = new Make();
        mark.name = name;
        return mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return (List<Model>) models;
    }

    public void addModel(Model model) {
        models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Make make = (Make) o;
        return Objects.equals(name, make.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Make{" +
                "name='" + name + '\'' +
                '}';
    }
}
