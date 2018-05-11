package program.service.api;

import program.model.ExercisesEntity;
import java.util.List;

public interface ExercisesService {

    ExercisesEntity getEntityByName(String name);
   List<ExercisesEntity> getWomanWeightLossExercisesName(String bodypart);
   List<ExercisesEntity> getManWeightLossExercisesName(String bodypart);
   List<ExercisesEntity> getWomanCuttingExercisesName(String bodypart);
   List<ExercisesEntity> getManCuttingExercisesName(String bodypart);
}
