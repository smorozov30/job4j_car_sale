package ru.job4j.car_sale.model;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    private int id;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "makes_id", nullable = false, updatable = false)
    private Make make;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "models_id", nullable = false, updatable = false)
    private Model model;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bodies_id", nullable = false, updatable = false)
    private Body body;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "engine_id", nullable = false)
    private Engine engine;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transmissions_id", nullable = false)
    private Transmission transmission;

    public static Car of(Make make, Model model, Body body, Engine engine, Transmission transmission) {
        Car car = new Car();
        car.make = make;
        car.model = model;
        car.body = body;
        car.engine = engine;
        car.transmission = transmission;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make=" + make +
                ", model=" + model +
                ", body=" + body +
                ", engine=" + engine +
                ", transmission=" + transmission +
                '}';
    }
}
