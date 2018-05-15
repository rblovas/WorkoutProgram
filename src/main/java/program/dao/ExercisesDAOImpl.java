package program.dao;

import program.dao.api.ExercisesDAO;
import program.model.ExercisesEntity;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class ExercisesDAOImpl implements ExercisesDAO {

    private EntityManager entityManager;

    public ExercisesDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public void persist(ExercisesEntity entity) {

    }

    @Override
    public void update(ExercisesEntity entity) {

    }

    @Override
    public ExercisesEntity  getEntityByName(String name){
        Query query = entityManager.createQuery("select e from ExercisesEntity  e where name = :name");
        query.setParameter("name", name);
        return (ExercisesEntity) query.getSingleResult();
    }

    @Override
    public List<ExercisesEntity> getWomanWeightLossExercisesName(String bodypart){

        TypedQuery<ExercisesEntity> query = entityManager.createQuery("SELECT e FROM ExercisesEntity e WHERE bodypart = :bodypart AND womanWeightLoss = 'y' ",ExercisesEntity.class);
        query.setParameter("bodypart", bodypart);

        return query.getResultList();
    }

    @Override
    public List<ExercisesEntity> getManWeightLossExercisesName(String bodypart){

        TypedQuery<ExercisesEntity> query = entityManager.createQuery("SELECT e FROM ExercisesEntity e WHERE bodypart = :bodypart AND manWeightLoss = 'y' ",ExercisesEntity.class);
        query.setParameter("bodypart", bodypart);

        return query.getResultList();
    }

    @Override
    public List<ExercisesEntity> getWomanCuttingExercisesName(String bodypart){

        TypedQuery<ExercisesEntity> query = entityManager.createQuery("SELECT e FROM ExercisesEntity e WHERE bodypart = :bodypart AND womanCutting = 'y' ",ExercisesEntity.class);
        query.setParameter("bodypart", bodypart);

        return query.getResultList();
    }

    @Override
    public List<ExercisesEntity> getManCuttingExercisesName(String bodypart){

        TypedQuery<ExercisesEntity> query = entityManager.createQuery("SELECT e FROM ExercisesEntity e WHERE bodypart = :bodypart AND manCutting = 'y' ",ExercisesEntity.class);
        query.setParameter("bodypart", bodypart);

        return query.getResultList();
    }
}
