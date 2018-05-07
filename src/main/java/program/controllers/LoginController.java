package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import program.dao.UserDAOImpl;
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

            Node source = (Node) event.getSource();
            Stage newStage = (Stage) source.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader((getClass().getClassLoader().getResource("views/today.fxml")));
            Scene newScene = new Scene(loader.load());
            loader.<TodayController>getController().setName(name);

            newStage.setScene(newScene);
            newStage.show();


//            sceneSwitch(dialogStage,"mainScreen", event);
        }else{
            errorBox("Nem található ilyen felhasználó!","Hiba!", "");
        }

    }


    @FXML
    public void goToRegistration(ActionEvent actionEvent) {
        sceneSwitch(dialogStage, "registration", actionEvent);
    }

}
