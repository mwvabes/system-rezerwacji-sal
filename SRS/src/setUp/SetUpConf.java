package setUp;

import entities.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class SetUpConf {

  Session session;
  SessionFactory factory;
  ServiceRegistry serviceRegistry;

  public SetUpConf() {
  }

  public Session setUpStart() {
    Configuration configuration = new Configuration().configure("hib.xml");
    configuration.addClass(entities.EquipmentEntity.class);
    configuration.addClass(entities.UsersEntity.class);
    configuration.addClass(entities.BuildingsEntity.class);
    configuration.addClass(entities.EquipmentAllocationEntity.class);
    configuration.addClass(entities.ReservationsEntity.class);
    configuration.addClass(entities.RoomsEntity.class);
    configuration.addClass(entities.RoomTypesEntity.class);
    configuration.addClass(entities.TableRoomsEntity.class);
    configuration.addClass(entities.ReservationsViewEntity.class);
    configuration.addClass(entities.EquipmentInfoEntity.class);

    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

    factory = configuration.buildSessionFactory(serviceRegistry);

    session = factory.openSession();
    return session;
  }

  public void setUpClose() {
    session.close();
    factory.close();
    StandardServiceRegistryBuilder.destroy(serviceRegistry);
  }

}
