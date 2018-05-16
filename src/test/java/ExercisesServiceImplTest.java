import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import program.dao.ExercisesDAOImpl;
import program.model.ExercisesEntity;
import program.service.ExercisesServiceImpl;
import program.utility.Manager;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ExercisesServiceImplTest {

    private ExercisesDAOImpl dao = new ExercisesDAOImpl(Manager.getInstance());
    private ExercisesServiceImpl exercisesService = new ExercisesServiceImpl(dao);


    private static ExercisesEntity createTestEntity(){
        ExercisesEntity exercisesEntity = new ExercisesEntity();
        exercisesEntity.setId(100L);
        exercisesEntity.setName("Teszt");
        exercisesEntity.setBodypart("calves");
        exercisesEntity.setDescription("https://www.bodybuilding.com/exercises/muscle/calves");
        exercisesEntity.setWomanWeightLoss("y");
        exercisesEntity.setManWeightLoss("y");
        exercisesEntity.setWomanCutting("y");
        exercisesEntity.setManCutting("y");
        exercisesEntity.setEasy("y");
        exercisesEntity.setTime(1);
        exercisesEntity.setType("kg");
        return exercisesEntity;
    }

    @BeforeClass
    public static void before(){
        ExercisesDAOImpl dao = new ExercisesDAOImpl(Manager.getInstance());
        ExercisesServiceImpl exercisesService = new ExercisesServiceImpl(dao);
        exercisesService.createExercise(createTestEntity());

    }

    private static ExercisesEntity exercisesEntity = createTestEntity();

    @Test
    public void getEntityByNameTest(){
        ExercisesEntity resultEntity = exercisesService.getEntityByName(exercisesEntity.getName());
        assertEquals(exercisesEntity, resultEntity);
    }

    @Test
    public void getWomanWeightLossExerciseNameTest(){
        List<ExercisesEntity> resultList = exercisesService.getWomanWeightLossExercisesName(exercisesEntity.getBodypart());
        assertEquals(exercisesEntity, resultList.get(resultList.size()-1));
    }

    @Test
    public void getManWeightLossExerciseNameTest(){
        List<ExercisesEntity> resultList = exercisesService.getManWeightLossExercisesName(exercisesEntity.getBodypart());
        assertEquals(exercisesEntity, resultList.get(resultList.size()-1));
    }

    @Test
    public void getWomanCuttingExerciseNameTest(){
        List<ExercisesEntity> resultList = exercisesService.getWomanCuttingExercisesName(exercisesEntity.getBodypart());
        assertEquals(exercisesEntity, resultList.get(resultList.size()-1));
    }

    @Test
    public void getManCuttingExerciseNameTest(){
        List<ExercisesEntity> resultList = exercisesService.getManCuttingExercisesName(exercisesEntity.getBodypart());
        assertEquals(exercisesEntity, resultList.get(resultList.size()-1));
    }

}


