package program.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

/**
 * JPA adatbázis-kapcsolatot megvalósító osztály. Itt fejtjük ki többek között a Queryket is.
 * Az adatbáziskapcsolatot a Main-ben érdemes létrehozni. Startkor kapcsolódni a
 * db-hez, majd bezárás előtt lejönni róla.
 * (Megteheted, hogy minden adatbázis-műveletkor commentelsz, majd lejössz, de ez nyílván a program lassúságát okozza,
 * hiszen ez sok adatbázis-fogalmat generál.)
 *
 * Létrehozása mainben:
 *
 *public class Main { 
 *  private static final manager db = manager.getDbPeldany();
 *
 *  public static void main(String[] args) {
 *      db.connectDB();
 *
 *      ...main...
 *
 *      db.disconnectDB();
 *  }
 *}
 *
 * persistence.xml az src/main/META-INF alá kell, hogy kerüljön.
 *
 */
@Slf4j
public class Manager implements AutoCloseable{

    private static final String persistenceUnitName = "database";

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static EntityManager getInstance(){
        return entityManager;
    }

    @Override
    public void close() throws Exception {
        if(entityManagerFactory != null){
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
