package program.service;

import program.dao.UserDAOImpl;
import program.model.ExercisesEntity;
import program.model.UserEntity;
import program.service.api.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAOImpl dao;

    public UserServiceImpl(UserDAOImpl dao) {
        this.dao = dao;
    }

    @Override
    public void createUser(UserEntity userEntity) {
        dao.persist(userEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {dao.delete(userEntity);}

    @Override
    public void updateUser(UserEntity userEntity) {
        dao.update(userEntity);
    }

    @Override
    public UserEntity isLoggedIn(String name, String password) throws Exception {
        return dao.isLoggedIn(name, password);
    }

    @Override
    public UserEntity isRegistered(String name) {
        return dao.isRegistered(name);
    }

    private static String name;

    public static void setUser(String name) {
        UserServiceImpl.name = name;
    }

    public static String getUser() {
        return UserServiceImpl.name;
    }

}
