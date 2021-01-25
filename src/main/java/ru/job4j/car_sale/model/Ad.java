package ru.job4j.car_sale.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Expose
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Expose
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
        ad.created = new Date(System.currentTimeMillis());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addUser(User user) {
        this.user = user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
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
