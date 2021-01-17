package ru.job4j.car_sale.model;

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

//            Body body1 = Body.of("Body 1");
//            Body body2 = Body.of("Body 2");
//            Body body3 = Body.of("Body 3");
//            session.save(body1);
//            session.save(body2);
//            session.save(body3);

//            Engine engine1 = Engine.of("Engine 1");
//            Engine engine2 = Engine.of("Engine 2");
//            Engine engine3 = Engine.of("Engine 3");
//            session.save(engine1);
//            session.save(engine2);
//            session.save(engine3);

//            Transmission transmission1 = Transmission.of("Transmission 1");
//            Transmission transmission2 = Transmission.of("Transmission 2");
//            Transmission transmission3 = Transmission.of("Transmission 3");
//            session.save(transmission1);
//            session.save(transmission2);
//            session.save(transmission3);

//            Body body1 = session.load(Body.class, 1);
//            Body body2 = session.load(Body.class, 2);
//            Body body3 = session.load(Body.class, 3);

//            Engine engine1 = session.load(Engine.class, 1);
//            Engine engine2 = session.load(Engine.class, 2);
//            Engine engine3 = session.load(Engine.class, 3);

//            Transmission transmission1 = session.load(Transmission.class, 1);
//            Transmission transmission2 = session.load(Transmission.class, 2);
//            Transmission transmission3 = session.load(Transmission.class, 3);

//            Model polo = Model.of("POLO");
//            polo.addBody(body1);
//            polo.addBody(body2);
//            polo.addEngine(engine1);
//            polo.addEngine(engine2);
//            polo.addTransmission(transmission1);
//            polo.addTransmission(transmission2);

//            Model passat = Model.of("PASSAT");
//            passat.addBody(body1);
//            passat.addEngine(engine2);
//            passat.addTransmission(transmission2);

//            Model jetta = Model.of("JETTA");
//            jetta.addBody(body2);
//            jetta.addBody(body3);
//            jetta.addEngine(engine2);
//            jetta.addEngine(engine3);
//            jetta.addTransmission(transmission2);
//            jetta.addTransmission(transmission3);

//            Model a6 = Model.of("A6");
//            a6.addBody(body1);
//            a6.addBody(body3);
//            a6.addEngine(engine1);
//            a6.addEngine(engine3);
//            a6.addTransmission(transmission1);
//            a6.addTransmission(transmission3);

//            session.save(polo);
//            session.save(passat);
//            session.save(jetta);
//            session.save(a6);

//            Model polo = session.load(Model.class, 1);
//            Model passat = session.load(Model.class, 2);
//            Model jetta = session.load(Model.class, 3);
//            Model a6 = session.load(Model.class, 4);
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

//            Make vw = session.load(Make.class, 1);
//            System.out.println(vw);
//            Make audi = session.load(Make.class, 2);
//            System.out.println(audi);

//            Model model = session.get(Model.class, 3);
//            for (Body body : model.getBodies()) {
//                System.out.println(body);
//            }
//            for (Engine engine : model.getEngines()) {
//                System.out.println(engine);
//            }
//            for (Transmission transmission : model.getTransmissions()) {
//                System.out.println(transmission);
//            }



//            session.getTransaction().commit();
//            session.close();
//        }  catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            StandardServiceRegistryBuilder.destroy(registry);
//        }
        StringBuilder sb = new StringBuilder();
        Store store = HibernateStore.instOf();

        List<Make> marks = store.getMarks();
        marks.forEach(mark -> sb.append(mark.getId()).append(" | ").append(mark.getName()).append("\n"));
        sb.append("***********" + "\n");

        List<Model> models = store.getModelsByMarkId(1);
        models.forEach(model -> sb.append(model.getId()).append(" | ").append(model.getName()).append("\n"));
        sb.append("***********" + "\n");

        List<Body> bodies = store.getBodiesByModelId(2);
        bodies.forEach(body -> sb.append(body.getId()).append(" | ").append(body.getName()).append("\n"));
        sb.append("***********" + "\n");

        List<Engine> engines = store.getEnginesByModelId(2);
        engines.forEach(engine -> sb.append(engine.getId()).append(" | ").append(engine.getName()).append("\n"));
        sb.append("***********" + "\n");

        List<Transmission> transmissions = store.getTransmissionsByModelId(2);
        transmissions.forEach(transmission -> sb.append(transmission.getId()).append(" | ").append(transmission.getName()).append("\n"));
        sb.append("***********" + "\n");

        System.out.println(sb.toString());
    }
}
