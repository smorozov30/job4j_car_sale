package ru.job4j.car_sale.store;

import ru.job4j.car_sale.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final AtomicInteger USER_ID = new AtomicInteger(1);
    private static final AtomicInteger AD_ID = new AtomicInteger(1);

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    private final Map<Make, List<Model>> makes_models = new ConcurrentHashMap<>();
    private final Map<Model, List<Body>> model_bodies = new ConcurrentHashMap<>();
    private final Map<Model, List<Engine>> model_engines = new ConcurrentHashMap<>();
    private final Map<Model, List<Transmission>> model_transmissions = new ConcurrentHashMap<>();

    private MemStore() {
        Make vw = Make.of("VW");
        Make audi = Make.of("AUDI");

        Model polo = Model.of("Polo");
        Model passat = Model.of("Passat");
        Model jetta = Model.of("Jetta");
        Model a6 = Model.of("A6");

        Body sedan = Body.of("Sedan");
        Body hatchback = Body.of("Hatchback");
        Body coupe = Body.of("Coupe");

        Engine petrol = Engine.of("Petrol");
        Engine diesel = Engine.of("Diesel");

        Transmission mechanical = Transmission.of("Mechanical");
        Transmission automatic = Transmission.of("Automatic");
        Transmission robotic = Transmission.of("Robotic");

        makes_models.put(vw, Arrays.asList(polo, jetta, passat));
        makes_models.put(audi, Arrays.asList(a6));

        model_bodies.put(polo, Arrays.asList(sedan, hatchback));
        model_bodies.put(jetta, Arrays.asList(sedan, hatchback));
        model_bodies.put(passat, Arrays.asList(sedan));
        model_bodies.put(a6, Arrays.asList(coupe));

        model_engines.put(polo, Arrays.asList(petrol, diesel));
        model_engines.put(jetta, Arrays.asList(petrol));
        model_engines.put(passat, Arrays.asList(diesel));
        model_engines.put(a6, Arrays.asList(petrol, diesel));

        model_transmissions.put(polo, Arrays.asList(mechanical, automatic));
        model_transmissions.put(jetta, Arrays.asList(automatic));
        model_transmissions.put(passat, Arrays.asList(mechanical, automatic));
        model_transmissions.put(a6, Arrays.asList(robotic));

        User sergey = User.of("Sergey", "email", "pass");
        users.put(1, sergey);

        Car car = Car.of(vw, polo, sedan, petrol, mechanical);
        Ad ad = Ad.of(1, "START DESCRIPTION", car, false);
        ad.addPhoto("red_car.jpg");
        sergey.addAd(ad);
        ad.setUser(sergey);
    }

    private static final class Lazy {
        private static final Store INST = new MemStore();
    }

    public static Store instOf() {
        return MemStore.Lazy.INST;
    }

    @Override
    public void addAd(Ad ad, User user) {
        ad.setId(AD_ID.incrementAndGet());
        ad.setUser(user);
        user.addAd(ad);
    }

    @Override
    public List<Ad> getAds() {
        List<Ad> list = new ArrayList<>();
        users.values().forEach(user -> list.addAll(user.getAds()));
        return list;
    }

    @Override
    public List<Ad> getAdsByUser(User user) {
        return user.getAds();
    }

    @Override
    public void setSoldById(int id) {
        getAds().forEach(ad -> {
            if (ad.getId() == id) {
                ad.setSold(!ad.isSold());
            }
        });
    }

    @Override
    public List<Make> getMakes() {
        return new ArrayList<>(makes_models.keySet());
    }

    @Override
    public List<Model> getModelsByMakeName(String markName) {
        return makes_models.get(Make.of(markName));
    }

    @Override
    public List<Body> getBodiesByModelName(String modelId) {
        return model_bodies.get(Model.of(modelId));
    }

    @Override
    public List<Engine> getEnginesByModelName(String modelId) {
        return model_engines.get(Model.of(modelId));
    }

    @Override
    public List<Transmission> getTransmissionsByModelName(String modelId) {
        return model_transmissions.get(Model.of(modelId));
    }

    @Override
    public void addUser(User user) {
        users.put(USER_ID.incrementAndGet(), user);
    }

    @Override
    public User getUser(String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email))
                                        .findFirst()
                                        .orElse(null);
    }

    @Override
    public Car designCar(String makeName, String modelName, String bodyName, String engineName, String transmissionName) {
        return Car.of(
                Make.of(makeName),
                Model.of(modelName),
                Body.of(bodyName),
                Engine.of(engineName),
                Transmission.of(transmissionName)
        );
    }
}
