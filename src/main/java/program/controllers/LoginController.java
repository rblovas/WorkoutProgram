package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import program.dao.UserDAOImpl;
import program.model.UserEntity;
import program.service.UserServiceImpl;
import program.service.api.UserService;
import program.utility.Manager;

/**
 * LoginController:
 *
 * login.fxml lelke
 */

public class LoginController extends Conroller {

    @FXML
    public TextField textUsername;
    @FXML
    public PasswordField textPassword;
    private Stage dialogStage = new Stage();
    private Scene scene;
    private UserService userService;

    @FXML
    private void initialize(){
        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    }

    @FXML
    public void login(ActionEvent event) throws IllegalStateException, IllegalArgumentException, Exception {


        String name = textUsername.getText();
        String password = textPassword.getText();

        if (name.isEmpty()) {
            errorBox("Nem adtál meg felhasználónevet!", "Hiba", "Hiba történt!");
        } else if (password.isEmpty()) {
            errorBox("Nem adtál meg jelszót!", "Hiba", "Hiba történt!");
        }else if(userService.isLoggedIn(name, password) != null) {
            UserServiceImpl.setUser(name);

            if(userService.isRegistered(name).getDays() < 3) {
                sceneSwitch(dialogStage, "setmainpage", event);
            } else {
                sceneSwitch(dialogStage, "mainpage", event);
            }

        }else{
            errorBox("Nem található ilyen felhasználó!","Hiba!", "");
        }

    }


    @FXML
    public void goToRegistration(ActionEvent actionEvent) {
        sceneSwitch(dialogStage, "registration", actionEvent);
    }

}
