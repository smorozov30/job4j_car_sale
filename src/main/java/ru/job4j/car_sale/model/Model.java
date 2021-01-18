package ru.job4j.car_sale.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "models")
public class Model {
    @Expose
    @Id
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Body> bodies = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Engine> engines = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Transmission> transmissions = new HashSet<>();

    public static Model of(String name) {
        Model model = new Model();
        model.name = name;
        return model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Body> getBodies() {
        return (List<Body>) bodies;
    }

    public void addBody(Body body) {
        bodies.add(body);
    }

    public List<Engine> getEngines() {
        return (List<Engine>) engines;
    }

    public void addEngine(Engine engine) {
        engines.add(engine);
    }

    public List<Transmission> getTransmissions() {
        return (List<Transmission>) transmissions;
    }

    public void addTransmission(Transmission transmission) {
        transmissions.add(transmission);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(name, model.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                '}';
    }
}
