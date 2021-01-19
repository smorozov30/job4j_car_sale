package ru.job4j.car_sale.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.car_sale.store.HibernateStore;
import ru.job4j.car_sale.store.Store;

import java.util.List;

public class HbmRun {
    public static void main(String[] args) {
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure().build();
//        try {
//            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            Session session = sf.openSession();
//            session.beginTransaction();

//            Body sedan = Body.of("Sedan");
//            Body hatchback = Body.of("Hatchback");
//            Body coupe = Body.of("Coupe");
//            session.save(sedan);
//            session.save(hatchback);
//            session.save(coupe);
//
//            Engine petrol = Engine.of("Petrol");
//            Engine diesel = Engine.of("Diesel");
//            session.save(petrol);
//            session.save(diesel);
//
//            Transmission mechanical = Transmission.of("Mechanical");
//            Transmission automatic = Transmission.of("Automatic");
//            Transmission robotic = Transmission.of("Robotic");
//            session.save(mechanical);
//            session.save(automatic);
//            session.save(robotic);

//            Body sedan = session.load(Body.class, "Sedan");
//            Body hatchback = session.load(Body.class, "Hatchback");
//            Body coupe = session.load(Body.class, "Coupe");
//
//            Engine petrol = session.load(Engine.class, "Petrol");
//            Engine diesel = session.load(Engine.class, "Diesel");
//
//            Transmission mechanical = session.load(Transmission.class, "Mechanical");
//            Transmission automatic = session.load(Transmission.class, "Automatic");
//            Transmission robotic = session.load(Transmission.class, "Robotic");
//
//
//            Model polo = Model.of("Polo");
//            polo.addBody(sedan);
//            polo.addBody(hatchback);
//            polo.addEngine(petrol);
//            polo.addTransmission(mechanical);
//            polo.addTransmission(automatic);
//
//            Model passat = Model.of("Passat");
//            passat.addBody(sedan);
//            passat.addEngine(petrol);
//            passat.addTransmission(mechanical);
//            passat.addTransmission(automatic);
//
//            Model jetta = Model.of("Jetta");
//            jetta.addBody(sedan);
//            jetta.addBody(coupe);
//            jetta.addEngine(petrol);
//            jetta.addEngine(diesel);
//            jetta.addTransmission(mechanical);
//            jetta.addTransmission(robotic);
//
//            Model a6 = Model.of("A6");
//            a6.addBody(sedan);
//            a6.addBody(hatchback);
//            a6.addBody(coupe);
//            a6.addEngine(petrol);
//            a6.addEngine(diesel);
//            a6.addTransmission(mechanical);
//            a6.addTransmission(automatic);
//            a6.addTransmission(robotic);
//
//            session.save(polo);
//            session.save(passat);
//            session.save(jetta);
//            session.save(a6);

//            Model polo = session.load(Model.class, "Polo");
//            Model passat = session.load(Model.class, "Passat");
//            Model jetta = session.load(Model.class, "Jetta");
//            Model a6 = session.load(Model.class, "A6");
//
//            Make vw = Make.of("VW");
//            vw.addModel(polo);
//            vw.addModel(passat);
//            vw.addModel(jetta);
//
//            Make audi = Make.of("AUDI");
//            audi.addModel(a6);
//
//            session.save(vw);
//            session.save(audi);

//            Make vw = session.load(Make.class, "VW");
//            System.out.println(vw);
//            Make audi = session.load(Make.class, "AUDI");
//            System.out.println(audi);
//
//            Model model = session.get(Model.class, "POLO");
//            for (Body body : model.getBodies()) {
//                System.out.println(body);
//            }
//            for (Engine engine : model.getEngines()) {
//                System.out.println(engine);
//            }
//            for (Transmission transmission : model.getTransmissions()) {
//                System.out.println(transmission);
//            }
//
//
//
//            session.getTransaction().commit();
//            session.close();
//        }  catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }


//        StringBuilder sb = new StringBuilder();
//        Store store = HibernateStore.instOf();
//
//        List<Make> marks = store.getMarks();
//        marks.forEach(mark -> sb.append(mark.getId()).append(" | ").append(mark.getName()).append("\n"));
//        sb.append("***********" + "\n");
//
//        List<Model> models = store.getModelsByMarkId(1);
//        models.forEach(model -> sb.append(model.getId()).append(" | ").append(model.getName()).append("\n"));
//        sb.append("***********" + "\n");
//
//        List<Body> bodies = store.getBodiesByModelId(2);
//        bodies.forEach(body -> sb.append(body.getId()).append(" | ").append(body.getName()).append("\n"));
//        sb.append("***********" + "\n");
//
//        List<Engine> engines = store.getEnginesByModelId(2);
//        engines.forEach(engine -> sb.append(engine.getId()).append(" | ").append(engine.getName()).append("\n"));
//        sb.append("***********" + "\n");
//
//        List<Transmission> transmissions = store.getTransmissionsByModelId(2);
//        transmissions.forEach(transmission -> sb.append(transmission.getId()).append(" | ").append(transmission.getName()).append("\n"));
//        sb.append("***********" + "\n");
//
//        System.out.println(sb.toString());
    }
}
