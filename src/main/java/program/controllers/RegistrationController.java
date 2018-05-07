package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import program.dao.UserDAOImpl;
import program.model.UserEntity;
import program.service.UserServiceImpl;
import program.service.api.UserService;
import program.utility.Manager;

@Slf4j
public class RegistrationController extends Conroller {
    @FXML
    public TextField textName;
    @FXML
    public PasswordField textPassword;
    @FXML
    public PasswordField textPasswordCheck;
    @FXML
    public Button buttonRegistration;
    @FXML
    public RadioButton radioWoman;
    @FXML
    public RadioButton radioMan;
    @FXML
    public RadioButton radioWeightLoss;
    @FXML
    public RadioButton radioCutting;

    private UserService userService;

    private UserEntity userEntity;
    private Stage dialogStage;

    @FXML
    private void initialize(){
        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    }

    @FXML
    public void registration(ActionEvent actionEvent) {

        UserEntity userEntity = new UserEntity();

        String name = textName.getText();
        String password = textPassword.getText();
        String passwordCheck = textPasswordCheck.getText();

        if (name.isEmpty()) {
            errorBox("Nem adtál meg felhasználónevet!", "Hiba", "Hiba történt!");
        } else if (password.isEmpty()) {
            errorBox("Nem adtál meg jelszót!", "Hiba", "Hiba történt!");
        } else if (passwordCheck.isEmpty()) {
            errorBox("Nem adtad meg másodjára a jelszót!", "Hiba", "Hiba történt!");
        } else if (!password.equals(passwordCheck)) {
            errorBox("A jelszavak nem egyeznek!", "Hiba", "Hiba történt!");
        }/* else if (!selected) {
            errorBox("Nem választottál pokémont!", "Hiba", "Hiba történt!");
        }
*/


        userEntity.setName(name);
        userEntity.setPassword(password);

        try {

            if(userService.isRegistered(name) == null){
                userService.createUser(userEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Entity mentési hiba!");
        }

        infoBox("Sikeres regisztráció!", "Juhé!", "Most már bejelentkezhetsz!");
        sceneSwitch(dialogStage,"login",actionEvent);
    }

    public void backToLogin(ActionEvent actionEvent) {
        sceneSwitch(dialogStage, "login", actionEvent);
    }
}
