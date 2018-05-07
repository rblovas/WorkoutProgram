package program.service;

import program.dao.UserDAOImpl;
import program.model.UserEntity;
import program.service.api.UserService;

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
    public UserEntity isLoggedIn(String name, String password) throws Exception {
        return dao.isLoggedIn(name, password);
    }

    @Override
    public UserEntity isRegistered(String name) {
        return dao.isRegistered(name);
    }
}
