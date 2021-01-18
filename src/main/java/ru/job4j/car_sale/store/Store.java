package ru.job4j.car_sale.store;

import ru.job4j.car_sale.model.*;

import java.util.List;

public interface Store {
    void addAd(Ad ad);
    List<Ad> getAds();
    List<Make> getMakes();
    List<Model> getModelsByMakeName(String markName);
    List<Body> getBodiesByModelName(String modelId);
    List<Engine> getEnginesByModelName(String modelId);
    List<Transmission> getTransmissionsByModelName(String modelId);
    void addUser(User user);
    User getUser(String email);
    Car designCar(String makeName, String modelName, String bodyName, String engineName, String transmissionName);
}
