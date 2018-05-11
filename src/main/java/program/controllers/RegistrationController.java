package program.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public RadioButton radioFemale;
    @FXML
    public RadioButton radioMale;
    @FXML
    public RadioButton radioWeightLoss;
    @FXML
    public RadioButton radioCutting;
    public ImageView imageGender;
    public TextField textYourWeight;
    public TextField textGoalWeight;


    private UserService userService;

    private UserEntity userEntity;
    private Stage dialogStage;

    private final ToggleGroup gender = new ToggleGroup();
    private final ToggleGroup type = new ToggleGroup();

    @FXML
    private void initialize(){
        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));

        Image image = new Image("/IMG/genderShadow.jpg");
        imageGender.setImage(image);
        radioMale.setText("Male");
        radioMale.setToggleGroup(gender);
        radioFemale.setText("Female");
        radioFemale.setToggleGroup(gender);

        radioWeightLoss.setText("Weight Loss");
        radioWeightLoss.setToggleGroup(type);
        radioCutting.setText("Cutting");
        radioCutting.setToggleGroup(type);

    }

    @FXML
    public void registration(ActionEvent actionEvent) {

        UserEntity userEntity = new UserEntity();

        String name = textName.getText();
        String password = textPassword.getText();
        String passwordCheck = textPasswordCheck.getText();

        Integer startweight = Integer.valueOf(textYourWeight.getText());
        Integer goalweight = Integer.valueOf(textGoalWeight.getText());

        if (name.isEmpty()) {
            errorBox("Nem adtál meg felhasználónevet!", "Hiba", "Hiba történt!");
        } else if (password.isEmpty()) {
            errorBox("Nem adtál meg jelszót!", "Hiba", "Hiba történt!");
        } else if (passwordCheck.isEmpty()) {
            errorBox("Nem adtad meg másodjára a jelszót!", "Hiba", "Hiba történt!");
        } else if (!password.equals(passwordCheck)) {
            errorBox("A jelszavak nem egyeznek!", "Hiba", "Hiba történt!");
        } else if (gender.getSelectedToggle() == null) {
            errorBox("Nem választottál nemet!", "Hiba", "Hiba történt!");
        } else if (type.getSelectedToggle() == null) {
            errorBox("Nem választottál edzéstípust!", "Hiba", "Hiba történt!");
        } else if (textYourWeight.getText().isEmpty()) {
            errorBox("Nem adtál meg kezdősúlyt", "Hiba", "Hiba történt!");
        } else if (textGoalWeight.getText().isEmpty()) {
            errorBox("Nem adtál meg célsúlyt", "Hiba", "Hiba történt!");
        } else {

            userEntity.setName(name);
            userEntity.setPassword(password);
            userEntity.setStartWeight(startweight);
            userEntity.setGoalWeight(goalweight);
            userEntity.setDays(1);


            if(radioMale.isSelected())
                userEntity.setGender(radioMale.getText());
            else
                userEntity.setGender(radioFemale.getText());

            if(radioWeightLoss.isSelected())
                userEntity.setType(radioWeightLoss.getText());
            else
                userEntity.setType(radioCutting.getText());


            try {


                if (userService.isRegistered(name) == null) {
                    userService.createUser(userEntity);
                }

            } catch (Exception e) {
                e.printStackTrace();
                log.error("Entity mentési hiba!");
            }

            infoBox("Sikeres regisztráció!", "Juhé!", "Most már bejelentkezhetsz!");
            sceneSwitch(dialogStage, "login", actionEvent);
        }
    }

    public void backToLogin(ActionEvent actionEvent) {
        sceneSwitch(dialogStage, "login", actionEvent);
    }
}