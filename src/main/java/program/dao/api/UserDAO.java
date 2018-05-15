package program.dao.api;

import program.model.UserEntity;

public interface UserDAO extends GenericDAO<UserEntity, Long> {

    UserEntity isLoggedIn(String name, String password)  throws Exception;
    UserEntity isRegistered(String name) throws IllegalArgumentException, IllegalStateException;
}
