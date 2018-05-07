package program.controllers;
import javafx.fxml.FXML;
import program.dao.UserDAOImpl;
import program.model.UserEntity;
import program.service.UserServiceImpl;
import program.service.api.UserService;
import program.utility.Manager;

public class TodayController {
    private UserEntity userEntity;
    private UserService userService;

    @FXML
    private void initialize(){
        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    }

    public void setName(String name){

        userEntity = userService.isRegistered(name);
        System.out.println(userEntity.getName());
    }

}
