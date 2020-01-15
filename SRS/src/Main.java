import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
//    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.srs.jpa");
//
//    EquipmentEntity equipmentEntity = new EquipmentEntity();
//    equipmentEntity.setName("Krzesło obrotowe");
//
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    entityManager.getTransaction().begin();
//
//    entityManager.persist(equipmentEntity);
//    entityManager.getTransaction().commit();
//
//    entityManagerFactory.close();

//    EquipmentEntity equipmentEntity = new EquipmentEntity();
//    equipmentEntity.setName("Krzesło obrotowe");
//    equipmentEntity.setDescription(" ");
//
//        Configuration configuration = new Configuration().configure("hib.xml");
//        configuration.addClass(entities.EquipmentEntity.class);
//        //configuration.configure();
//
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
//            applySettings(configuration.getProperties()).build();
//
//        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
//
//        Session session = factory.openSession();
//
//        Transaction transaction = session.beginTransaction();
//
//        session.save(equipmentEntity);
//
//        transaction.commit();
//
//        session.close();
//        factory.close();
//
//        StandardServiceRegistryBuilder.destroy(serviceRegistry);

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    srs.SceneManager.setStage(primaryStage);
    srs.SceneManager.addScene("logowanie", "/gui/login.fxml");
    srs.SceneManager.addScene("menu", "/gui/menu.fxml");
    srs.SceneManager.addScene("managementPanel", "/gui/ManagementPanel.fxml");
//    srs.SceneManager.addScene("sala", "/srs/sala.fxml");
//    srs.SceneManager.addScene("reservation_history", "/srs/reservation_history.fxml");

    srs.SceneManager.renderScene("logowanie");
  }
}
