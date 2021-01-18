package ru.job4j.car_sale.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ads")
public class Ad {

    @Expose
    @Id
    private int id;

    @Expose
    private String description;

    @Expose
    private boolean sold;

    @Expose
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Car car;

    @Expose
    @ElementCollection
    @CollectionTable(name = "photo", joinColumns = @JoinColumn(name = "ads_id"))
    @Column(name = "name")
    private Set<String> photo = new HashSet<>();

    public static Ad of(int id, String description, Car car, boolean sold) {
        Ad ad = new Ad();
        ad.id = id;
        ad.description = description;
        ad.car = car;
        ad.sold = sold;
        return ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<String> getPhoto() {
        return photo;
    }

    public void setPhoto(Set<String> photo) {
        this.photo = photo;
    }

    public void addPhoto(String name) {
        photo.add(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id == ad.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", sold=" + sold +
                ", car=" + car +
                ", photo=" + photo +
                '}';
    }
}
