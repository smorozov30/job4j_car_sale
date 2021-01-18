package ru.job4j.car_sale.store;

import ru.job4j.car_sale.model.*;

import java.util.List;

public interface Store {
    List<Make> getMarks();
    List<Model> getModelsByMarkId(int markId);
    List<Body> getBodiesByModelId(int modelId);
    List<Engine> getEnginesByModelId(int modelId);
    List<Transmission> getTransmissionsByModelId(int modelId);
    void addUser(User user);
    User getUser(String email);
}
