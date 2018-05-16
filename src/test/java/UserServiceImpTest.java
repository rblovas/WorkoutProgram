import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import program.dao.UserDAOImpl;
import program.model.UserEntity;
import program.service.UserServiceImpl;
import program.utility.Manager;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UserServiceImpTest {


    private UserDAOImpl dao = new UserDAOImpl(Manager.getInstance());
    private UserServiceImpl userService = new UserServiceImpl(dao);


    private static UserEntity createTestEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Teszt");
        userEntity.setPassword("asd");
        userEntity.setGender("Male");
        userEntity.setType("Weight Loss");
        userEntity.setStartWeight(90);
        userEntity.setGoalWeight(80);
        return userEntity;
    }

    @BeforeClass
    public static void asd(){
        UserDAOImpl dao = new UserDAOImpl(Manager.getInstance());
        UserServiceImpl userService = new UserServiceImpl(dao);
        userService.createUser(createTestEntity());

    }

    private UserEntity user = createTestEntity();

    @Test
    public void isLoggedInTest(){
        UserEntity resultEntity = null;
        try {
            resultEntity = userService.isLoggedIn(user.getName(), user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(user, resultEntity);
    }

    @Test
    public void isRegisteredTest(){
        UserEntity resultEntity = userService.isRegistered(user.getName());
        resultEntity.setId(user.getId());
        assertEquals(user, resultEntity);
    }


}
