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
    public List<Make> getMarks() {
        return execute(session -> session.createQuery("FROM ru.job4j.car_sale.model.Make").list());
    }

    @Override
    public List<Model> getModelsByMarkId(int markId) {
        return execute(session -> {
            Make mark = session.get(Make.class, markId);
            if (mark != null) {
                Hibernate.initialize(mark.getModels());
            }
            return (List<Model>) mark.getModels();
        });
    }

    @Override
    public List<Body> getBodiesByModelId(int modelId) {
        return execute(session -> {
            Model model = session.get(Model.class, modelId);
            if (model != null) {
                Hibernate.initialize(model.getBodies());
            }
            return (List<Body>) model.getBodies();
        });
    }

    @Override
    public List<Engine> getEnginesByModelId(int modelId) {
        return execute(session -> {
            Model model = session.get(Model.class, modelId);
            if (model != null) {
                Hibernate.initialize(model.getEngines());
            }
            return (List<Engine>) model.getEngines();
        });
    }

    @Override
    public List<Transmission> getTransmissionsByModelId(int modelId) {
        return execute(session -> {
            Model model = session.get(Model.class, modelId);
            if (model != null) {
                Hibernate.initialize(model.getTransmissions());
            }
            return (List<Transmission>) model.getTransmissions();
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
