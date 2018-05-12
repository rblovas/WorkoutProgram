package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import program.dao.ExercisesDAOImpl;
import program.dao.UserDAOImpl;
import program.model.ExercisesEntity;
import program.model.UserEntity;
import program.service.ExercisesServiceImpl;
import program.service.UserServiceImpl;
import program.service.api.ExercisesService;
import program.service.api.UserService;
import program.utility.Manager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class setmainpageController extends Conroller {
    public ChoiceBox choiceGluSho;
    public Label labelDay;
    public Label labelGluSho;
    public ChoiceBox choiceCardio;
    public Label labelQuChe;
    public Label labelHamBi;
    public Label labelCalBa;
    public Label labelAbs;
    public ChoiceBox choiceHamBi;
    public ChoiceBox choiceCalBa;
    public ChoiceBox choiceQuChe;
    public ChoiceBox choiceAbs;
    public Label cardioTime;
    public Label repsAmount1;
    public Label repsAmount2;
    public Label repsAmount3;
    public Label repsAmount4;
    public Label repsAmount5;
    public TextField textGluSho;
    public TextField textQuChe;
    public TextField textHamBi;
    public TextField textCalBa;
    public TextField textAbs;

    private  UserService userService;
    private UserEntity userEntity;
    private ExercisesService exercisesService = new ExercisesServiceImpl(new ExercisesDAOImpl(Manager.getInstance()));
    private Stage dialogStage = new Stage();

    @FXML
    private void initialize() {
        String name = UserServiceImpl.getUser();

        userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
        userEntity = userService.isRegistered(name);

        whichday();
    }

    private void whichday() {
        cardioTime.setText("10 perc");

        List<Label> gyakorlatoklabel = Arrays.asList(labelGluSho, labelQuChe, labelHamBi, labelCalBa);
        List<String> gyakorlatok1 = Arrays.asList("Glutes", "Quads", "Hamstrings", "Calves");
        List<String> gyakorlatok2 = Arrays.asList("Shoulders", "Chest", "Biceps", "Back");

        if (userEntity.getDays() % 2 == 1) {
            labelDay.setText("SET LEG DAY");
            for (int labels = 0; labels < 4; labels++) {
                gyakorlatoklabel.get(labels).setText(gyakorlatok1.get(labels));
            }

            List<String> choiceBoxesLeg = Arrays.asList("cardio", "glutes", "quads", "hamstrings", "calves", "abs");
            addlist(choiceBoxesLeg);

        } else {
            labelDay.setText("SET UPPER BODY DAY");
            for (int labels = 0; labels < 4; labels++) {
                gyakorlatoklabel.get(labels).setText(gyakorlatok2.get(labels));
            }

            List<String> choiceBoxesUp = Arrays.asList("cardio", "shoulders", "chest", "biceps", "back", "abs");
            addlist(choiceBoxesUp);
        }

        List<Label> repslabel = Arrays.asList(repsAmount1, repsAmount2, repsAmount3, repsAmount4, repsAmount5);
        if (userEntity.getType().equals("Weight Loss")) {
            for (int labels = 0; labels < 5; labels++) {
                repslabel.get(labels).setText("4 x 14");
            }
        } else {
            for (int labels = 0; labels < 5; labels++) {
                repslabel.get(labels).setText("3 x 10");
            }
        }

    }


    private void addlist(List<String> list){
        List<ChoiceBox> choiceBoxes = Arrays.asList(choiceCardio, choiceGluSho, choiceQuChe, choiceHamBi, choiceCalBa, choiceAbs);
        List<ExercisesEntity> entities;
        List<String> exerciseNames;

        if (userEntity.getType().equals("Weight Loss") && userEntity.getGender().equals("Female")) {
            for (int choices = 0; choices < 6; choices++) {
                entities = exercisesService.getWomanWeightLossExercisesName(list.get(choices));
                exerciseNames = entities.stream()
                        .map(m -> m.getName())
                        .collect(Collectors.toList());
                choiceBoxes.get(choices).getItems().addAll(exerciseNames);
                choiceBoxes.get(choices).getSelectionModel().selectFirst();
            }
        } else if (userEntity.getType().equals("Weight Loss") && userEntity.getGender().equals("Male")) {
            for (int choices = 0; choices < 6; choices++) {
                entities = exercisesService.getManWeightLossExercisesName(list.get(choices));
                exerciseNames = entities.stream()
                        .map(m -> m.getName())
                        .collect(Collectors.toList());
                choiceBoxes.get(choices).getItems().addAll(exerciseNames);
                choiceBoxes.get(choices).getSelectionModel().selectFirst();
            }

        } else if (userEntity.getType().equals("Cutting") && userEntity.getGender().equals("Female")) {
            for (int choices = 0; choices < 6; choices++) {
                entities = exercisesService.getWomanCuttingExercisesName(list.get(choices));
                exerciseNames = entities.stream()
                        .map(m -> m.getName())
                        .collect(Collectors.toList());
                choiceBoxes.get(choices).getItems().addAll(exerciseNames);
                choiceBoxes.get(choices).getSelectionModel().selectFirst();
            }

        } else if (userEntity.getType().equals("Cutting") && userEntity.getGender().equals("Male")) {
            for (int choices = 0; choices < 6; choices++) {
                entities = exercisesService.getManCuttingExercisesName(list.get(choices));
                exerciseNames = entities.stream()
                        .map(m -> m.getName())
                        .collect(Collectors.toList());
                choiceBoxes.get(choices).getItems().addAll(exerciseNames);
                choiceBoxes.get(choices).getSelectionModel().selectFirst();
            }
        }
    }


    public void actionCancel(ActionEvent actionEvent) {
        if(userEntity.getDays() < 3){
            errorBox("Az elso es a masodik napon muszaj elmentened a kezdo adataidat.", "Hiba", "Hiba történt!");
        }
        sceneSwitch(dialogStage, "mainpage", actionEvent);
    }

    public void actionSave(ActionEvent actionEvent) {
        if (userEntity.getDays() % 2 == 1) {

            userEntity.setCardioLeg(choiceCardio.getSelectionModel().getSelectedItem().toString());
            userEntity.setCardioLegw(10);
            userEntity.setGlutes(choiceGluSho.getSelectionModel().getSelectedItem().toString());
            userEntity.setGlutesw(Integer.parseInt(textGluSho.getText()));
            userEntity.setQuads(choiceQuChe.getSelectionModel().getSelectedItem().toString());
            userEntity.setQuadsw(Integer.parseInt(textQuChe.getText()));
            userEntity.setHamstring(choiceHamBi.getSelectionModel().getSelectedItem().toString());
            userEntity.setHamstringw(Integer.parseInt(textHamBi.getText()));
            userEntity.setCalves(choiceCalBa.getSelectionModel().getSelectedItem().toString());
            userEntity.setCalvesw(Integer.parseInt(textCalBa.getText()));
            userEntity.setAbsLeg(choiceAbs.getSelectionModel().getSelectedItem().toString());
            userEntity.setAbsLegw(Integer.parseInt(textAbs.getText()));

        } else {
            userEntity.setCardioUp(choiceCardio.getSelectionModel().getSelectedItem().toString());
            userEntity.setCardioUpw(10);
            userEntity.setShoulders(choiceGluSho.getSelectionModel().getSelectedItem().toString());
            userEntity.setShouldersw(Integer.parseInt(textGluSho.getText()));
            userEntity.setChest(choiceQuChe.getSelectionModel().getSelectedItem().toString());
            userEntity.setChestw(Integer.parseInt(textQuChe.getText()));
            userEntity.setBiceps(choiceHamBi.getSelectionModel().getSelectedItem().toString());
            userEntity.setBicepsw(Integer.parseInt(textHamBi.getText()));
            userEntity.setBack(choiceCalBa.getSelectionModel().getSelectedItem().toString());
            userEntity.setBackw(Integer.parseInt(textCalBa.getText()));
            userEntity.setAbsUp(choiceAbs.getSelectionModel().getSelectedItem().toString());
            userEntity.setAbsUpw(Integer.parseInt(textAbs.getText()));
        }


        sceneSwitch(dialogStage, "mainpage", actionEvent);
    }


}
