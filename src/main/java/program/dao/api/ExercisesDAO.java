package program.dao.api;

import program.model.ExercisesEntity;

import java.util.List;

public interface ExercisesDAO extends GenericDAO<ExercisesEntity, Long> {
    ExercisesEntity  getEntityByName(String name);
    List<ExercisesEntity> getWomanWeightLossExercisesName(String bodypart);
    List<ExercisesEntity> getManWeightLossExercisesName(String bodypart);
    List<ExercisesEntity> getWomanCuttingExercisesName(String bodypart);
    List<ExercisesEntity> getManCuttingExercisesName(String bodypart);
}
