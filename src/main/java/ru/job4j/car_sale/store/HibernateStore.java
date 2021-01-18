package ru.job4j.car_sale.store;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.car_sale.model.*;

import java.util.List;
import java.util.function.Function;

public class HibernateStore implements Store {
    private final SessionFactory sf;

    private HibernateStore() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final Store INST = new HibernateStore();
    }

    public static Store instOf() {
        return HibernateStore.Lazy.INST;
    }

    @Override
    public List<Make> getMakes() {
        return execute(session -> session.createQuery("FROM Make").list());
    }

    @Override
    public List<Model> getModelsByMakeName(String markName) {
        return execute(session -> {
            Make mark = session.get(Make.class, markName);
            if (mark != null) {
                Hibernate.initialize(mark.getModels());
            }
            return mark.getModels();
        });
    }

    @Override
    public List<Body> getBodiesByModelName(String modelName) {
        return execute(session -> {
            Model model = session.get(Model.class, modelName);
            if (model != null) {
                Hibernate.initialize(model.getBodies());
            }
            return model.getBodies();
        });
    }

    @Override
    public List<Engine> getEnginesByModelName(String modelName) {
        return execute(session -> {
            Model model = session.get(Model.class, modelName);
            if (model != null) {
                Hibernate.initialize(model.getEngines());
            }
            return model.getEngines();
        });
    }

    @Override
    public List<Transmission> getTransmissionsByModelName(String modelName) {
        return execute(session -> {
            Model model = session.get(Model.class, modelName);
            if (model != null) {
                Hibernate.initialize(model.getTransmissions());
            }
            return model.getTransmissions();
        });
    }

    @Override
    public void addUser(User user) {
        execute(session -> session.save(user));
    }

    @Override
    public User getUser(String email) {
        return (User) execute(session -> session.createQuery("FROM User WHERE email = :email")
                .setParameter("email", email)
                .uniqueResult());
    }

    @Override
    public Car designCar(String makeName, String modelName, String bodyName, String engineName, String transmissionName) {
        Car car = execute(session -> {
            Make make = session.load(Make.class, makeName);
            Model model = session.load(Model.class, modelName);
            Body body = session.load(Body.class, bodyName);
            Engine engine = session.load(Engine.class, engineName);
            Transmission transmission = session.load(Transmission.class, transmissionName);
            return Car.of(make, model, body, engine, transmission);
        });
        execute(session -> session.save(car));
        return car;
    }

    @Override
    public void addAd(Ad ad) {
        execute(session -> session.save(ad));
    }

    @Override
    public List<Ad> getAds() {
        return execute(session -> {
            List<Ad> ads = session.createQuery("FROM Ad").list();
            if (ads != null) {
                ads.forEach(ad -> {
                    Hibernate.initialize(ad.getPhoto());
                    Hibernate.initialize(ad.getCar());
                });
            }
            return ads;
        });
    }

    private <T> T execute(final Function<Session, T> command) {
        T result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = command.apply(session);
            session.getTransaction().commit();
        }
        return result;
    }
}
