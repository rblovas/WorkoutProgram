package program.dao;

import lombok.extern.slf4j.Slf4j;
import program.dao.api.UserDAO;
import program.model.UserEntity;
import program.utility.Manager;

import javax.persistence.*;


@Slf4j
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void persist(UserEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(UserEntity entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public UserEntity isLoggedIn(String name, String password) throws Exception{
        try {
            Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.name = :name AND u.password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);

            return (UserEntity) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            log.error("JPA lekérdezési hiba!");
            return null;
        }
    }

    public UserEntity isRegistered(String name) throws IllegalArgumentException, IllegalStateException{

        try {
            Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE LOWER(u.name) = LOWER(:name)");
            query.setParameter("name", name);

            return (UserEntity) query.getSingleResult();

        }catch (NoResultException e) {
            return null; // Ha a lekérdezés nem talált eredményt, akkor nullal térünk vissza, regelhet a felhasználó.
        }
    }
}

