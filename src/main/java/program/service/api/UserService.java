package program.service.api;

import program.model.UserEntity;

public interface UserService {

    void createUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
    UserEntity isLoggedIn(String name, String password) throws Exception;
    UserEntity isRegistered(String name);

}
