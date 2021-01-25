package ru.job4j.car_sale.model;


import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "makes_id")
    private Make make;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "models_id")
    private Model model;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bodies_id")
    private Body body;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmissions_id")
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
