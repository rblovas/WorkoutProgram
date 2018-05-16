package program.service;

import program.dao.ExercisesDAOImpl;
import program.dao.UserDAOImpl;
import program.model.ExercisesEntity;
import program.service.api.ExercisesService;

import javax.persistence.Column;
import java.util.List;

public class ExercisesServiceImpl implements ExercisesService{

    private ExercisesDAOImpl dao;

    public ExercisesServiceImpl(ExercisesDAOImpl dao) {
       this.dao = dao;
    }

    @Override
    public ExercisesEntity getEntityByName(String name) {
        return dao.getEntityByName(name);
    }

    @Override
    public List<ExercisesEntity> getWomanWeightLossExercisesName(String bodypart) {

        return dao.getWomanWeightLossExercisesName(bodypart);
    }

    @Override
    public List<ExercisesEntity> getManWeightLossExercisesName(String bodypart) {
        return dao.getManWeightLossExercisesName(bodypart);
    }

    @Override
    public List<ExercisesEntity> getWomanCuttingExercisesName(String bodypart) {
        return dao.getWomanCuttingExercisesName(bodypart);
    }

    @Override
    public List<ExercisesEntity> getManCuttingExercisesName(String bodypart) {
        return dao.getManCuttingExercisesName(bodypart);
    }

    @Override
    public void createExercise(ExercisesEntity entity){
        dao.persist(entity);
    }

    @Override
    public void deleteExercise(ExercisesEntity exercisesEntity) {
        dao.delete(exercisesEntity);
    }
}
