package program.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

import javax.management.StringValueExp;
import javax.persistence.Column;
import java.util.Arrays;
import java.util.List;

public class mainpageController extends Conroller {

    public Label labelDay;
    public Label labelGluSho;
    public Label labelQuChe;
    public Label labelHamBi;
    public Label labelCalBa;
    public Label labelAbs;
    public Label cardioTime;
    public Label repsAmount1;
    public Label repsAmount2;
    public Label repsAmount3;
    public Label repsAmount4;
    public Label repsAmount5;
    public Label weightExercise1;
    public Label weightExercise2;
    public Label weightExercise4;
    public Label weightExercise3;
    public Label weightExercise5;
    public Label exerciseType1;
    public Label exerciseType2;
    public Label exerciseType3;
    public Label exerciseType4;
    public Label exerciseType5;
    public Hyperlink linkCardio;
    public Hyperlink linkGluSho;
    public Hyperlink linkQuChe;
    public Hyperlink linkHamBi;
    public Hyperlink linkCalBa;
    public Hyperlink linkAbs;
    public ChoiceBox feedback1;
    public ChoiceBox feedback2;
    public ChoiceBox feedback3;
    public ChoiceBox feedback4;
    public ChoiceBox feedback5;
    public Label labelExercise1;
    public Label labelExercise2;
    public Label labelExercise3;
    public Label labelExercise4;
    public Label labelExercise5;
    public Label labelExercise6;
    public Label labelNumberOfDay;
    public TextField textActualWeight;
    public Label labelPercent;


    private UserEntity userEntity;
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    private ExercisesService exercisesService = new ExercisesServiceImpl(new ExercisesDAOImpl(Manager.getInstance()));
    private Stage dialogStage = new Stage();


    @FXML
    private void initialize() {
        String name = UserServiceImpl.getUser();
        userEntity = userService.isRegistered(name);
        labelNumberOfDay.setText(userEntity.getDays() + ". day");

        levelup();
        whichday();

        List<Label> labelsExercises = Arrays.asList(labelExercise2, labelExercise3, labelExercise4, labelExercise5, labelExercise6);
        List<Label> exerciseTypes = Arrays.asList(exerciseType1, exerciseType2, exerciseType3, exerciseType4, exerciseType5);

        ExercisesEntity exercisesEntity;
        for(int record = 0; record < 5; record++){
            exercisesEntity = exercisesService.getEntityByName(labelsExercises.get(record).getText());
            exerciseTypes.get(record).setText(exercisesEntity.getType());
        }

        List<Label> repslabels = Arrays.asList(repsAmount1,repsAmount2,repsAmount3,repsAmount4,repsAmount5);
        List<Label> weightExercises = Arrays.asList(weightExercise1,weightExercise2,weightExercise3,weightExercise4,weightExercise5);
        if(userEntity.getType().equals("Weight Loss")){
            for(int record = 0; record < 5; record++) {
                if(exerciseTypes.get(record).getText().equals("amount")){
                    repslabels.get(record).setText("4 x " + weightExercises.get(record).getText());
                    weightExercises.get(record).setText("amount");
                    exerciseTypes.get(record).setText("");
                } else {

                    repslabels.get(record).setText("4 x 14");
                }
            }
        } else {
            for(int record = 0; record < 5; record++) {
                if(exerciseTypes.get(record).getText().equals("amount")){
                    repslabels.get(record).setText("3 x " + weightExercises.get(record).getText());
                    weightExercises.get(record).setText("amount");
                    exerciseTypes.get(record).setText("");
                } else {

                    repslabels.get(record).setText("3 x 10");
                }
            }
        }


        addListFeedback();
    }


    private void levelup() {

        ExercisesEntity exercisesEntity;
        int time;
        int day = userEntity.getDays();

        exercisesEntity = exercisesService.getEntityByName(userEntity.getGlutes());
        time = exercisesEntity.getTime();
        if(day%time == 0) {
            userEntity.setGlutesw(userEntity.getGlutesw() + 1);
        }

        exercisesEntity = exercisesService.getEntityByName(userEntity.getQuads());
        time = exercisesEntity.getTime();
        if(day%time == 0) {
            userEntity.setQuadsw(userEntity.getQuadsw() + 1);
        }

        exercisesEntity = exercisesService.getEntityByName(userEntity.getHamstring());
        time = exercisesEntity.getTime();
        if(day%time == 0) {
            userEntity.setHamstringw(userEntity.getHamstringw() + 1);
        }

        exercisesEntity = exercisesService.getEntityByName(userEntity.getCalves());
        time = exercisesEntity.getTime();
        if(day%time == 0) {
            userEntity.setCalvesw(userEntity.getCalvesw() + 1);
        }

        exercisesEntity = exercisesService.getEntityByName(userEntity.getAbsLeg());
        time = exercisesEntity.getTime();
        if(day%time == 0) {
            userEntity.setAbsLegw(userEntity.getAbsLegw() + 1);
        }


        if(day > 1) {

            exercisesEntity = exercisesService.getEntityByName(userEntity.getShoulders());
            time = exercisesEntity.getTime();
            if (day % time == 0) {
                userEntity.setShouldersw(userEntity.getShouldersw() + 1);
            }

            exercisesEntity = exercisesService.getEntityByName(userEntity.getChest());
            time = exercisesEntity.getTime();
            if (day % time == 0) {
                userEntity.setChestw(userEntity.getChestw() + 1);
            }

            exercisesEntity = exercisesService.getEntityByName(userEntity.getBiceps());
            time = exercisesEntity.getTime();
            if (day % time == 0) {
                userEntity.setBicepsw(userEntity.getBicepsw() + 1);
            }

            exercisesEntity = exercisesService.getEntityByName(userEntity.getBack());
            time = exercisesEntity.getTime();
            if (day % time == 0) {
                userEntity.setBackw(userEntity.getBackw() + 1);
            }

            exercisesEntity = exercisesService.getEntityByName(userEntity.getAbsUp());
            time = exercisesEntity.getTime();
            if (day % time == 0) {
                userEntity.setAbsUpw(userEntity.getAbsUpw() + 1);
            }
        }
    }

    private void whichday() {
        cardioTime.setText("10 perc");

        List<Label> gyakorlatoklabel = Arrays.asList(labelGluSho, labelQuChe, labelHamBi, labelCalBa);
        List<String> gyakorlatok1 = Arrays.asList("Glutes", "Quads", "Hamstring", "Calves");
        List<String> gyakorlatok2 = Arrays.asList("Shoulders", "Chest", "Biceps","Glutes", "Back");

        if (userEntity.getDays() % 2 == 1) {
            labelDay.setText("LEG DAY");
            for(int labels = 0; labels < 4; labels++){
                gyakorlatoklabel.get(labels).setText(gyakorlatok1.get(labels));
            }
            labelExercise1.setText(userEntity.getCardioLeg());
            labelExercise2.setText(userEntity.getGlutes());
            labelExercise3.setText(userEntity.getQuads());
            labelExercise4.setText(userEntity.getHamstring());
            labelExercise5.setText(userEntity.getCalves());
            labelExercise6.setText(userEntity.getAbsLeg());

            weightExercise1.setText(String.valueOf(userEntity.getGlutesw()));
            weightExercise2.setText(String.valueOf(userEntity.getQuadsw()));
            weightExercise3.setText(String.valueOf(userEntity.getHamstringw()));
            weightExercise4.setText(String.valueOf(userEntity.getCalvesw()));
            weightExercise5.setText(String.valueOf(userEntity.getAbsLegw()));

        } else {
            labelDay.setText("UPPER BODY DAY");
            for(int labels = 0; labels < 4; labels++){
                gyakorlatoklabel.get(labels).setText(gyakorlatok2.get(labels));
            }

            labelExercise1.setText(userEntity.getCardioUp());
            labelExercise2.setText(userEntity.getShoulders());
            labelExercise3.setText(userEntity.getChest());
            labelExercise4.setText(userEntity.getBiceps());
            labelExercise5.setText(userEntity.getBack());
            labelExercise6.setText(userEntity.getAbsUp());

            weightExercise1.setText(String.valueOf(userEntity.getShouldersw()));
            weightExercise2.setText(String.valueOf(userEntity.getChestw()));
            weightExercise3.setText(String.valueOf(userEntity.getBicepsw()));
            weightExercise4.setText(String.valueOf(userEntity.getBackw()));
            weightExercise5.setText(String.valueOf(userEntity.getAbsUpw()));
        }



    }

    private void addListFeedback(){
        List<String> feedback = Arrays.asList("Too Easy", "Good", "Too Hard");
        List<ChoiceBox> choiceBoxes = Arrays.asList(feedback1, feedback2, feedback3, feedback4, feedback5);
        for(int list = 0; list < 5; list++){
            choiceBoxes.get(list).getItems().addAll(feedback);
            choiceBoxes.get(list).getSelectionModel().select(1);
        }
    }

    public void actionNextDay(ActionEvent actionEvent) {

        if(userEntity.getDays()%2 == 1) {
            if (feedback1.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setGlutesw(userEntity.getGlutesw() - 1);
                System.out.println("ASDASDASD  " + userEntity.getGlutesw());
            } else if (feedback1.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setGlutesw(userEntity.getGlutesw() + 1);
            }

            if (feedback2.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setQuadsw(userEntity.getQuadsw() - 1);
            } else if (feedback2.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setQuadsw(userEntity.getQuadsw() + 1);
            }

            if (feedback3.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setHamstringw(userEntity.getHamstringw() - 1);
            } else if (feedback3.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setHamstringw(userEntity.getHamstringw() + 1);
            }

            if (feedback4.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setCalvesw(userEntity.getCalvesw() - 1);
            } else if (feedback4.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setCalvesw(userEntity.getCalvesw() + 1);
            }
            if (feedback5.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setAbsLegw(userEntity.getAbsLegw() - 1);
            } else if (feedback5.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setAbsLegw(userEntity.getAbsLegw() + 1);
            }

        } else {
            if (feedback1.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setShouldersw(userEntity.getShouldersw() - 1);
            } else if (feedback1.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setShouldersw(userEntity.getShouldersw() + 1);
            }

            if (feedback2.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setChestw(userEntity.getChestw() - 1);
            } else if (feedback2.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setChestw(userEntity.getChestw() + 1);
            }

            if (feedback3.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setBicepsw(userEntity.getBicepsw() - 1);
            } else if (feedback3.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setBicepsw(userEntity.getBicepsw() + 1);
            }

            if (feedback4.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setBackw(userEntity.getBackw() - 1);
            } else if (feedback4.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setBackw(userEntity.getBackw() + 1);
            }
            if (feedback5.getSelectionModel().getSelectedItem().toString().equals("Too Hard")) {
                userEntity.setAbsUpw(userEntity.getAbsUpw() - 1);
            } else if (feedback5.getSelectionModel().getSelectedItem().toString().equals("Too Easy")) {
                userEntity.setAbsUpw(userEntity.getAbsUpw() + 1);
            }
        }

        userEntity.setDays(userEntity.getDays() + 1);
        userService.updateUser(userEntity);

        if(userEntity.getDays() < 3){
            sceneSwitch(dialogStage, "setmainpage", actionEvent);
        } else {
            sceneSwitch(dialogStage, "mainpage", actionEvent);
        }
    }

    public void actionSetDay(ActionEvent actionEvent) {
        sceneSwitch(dialogStage, "setmainpage", actionEvent);
    }

    public void acitonMoreInfoCardio(ActionEvent actionEvent) {
        ExercisesEntity exercisesEntity = exercisesService.getEntityByName(labelExercise1.getText());
        getHostServices().showDocument(exercisesEntity.getDescription());
    }

    public void acitonMoreInfoGluSho(ActionEvent actionEvent) {
        ExercisesEntity exercisesEntity = exercisesService.getEntityByName(labelExercise2.getText());
        getHostServices().showDocument(exercisesEntity.getDescription());
    }

    public void acitonMoreInfoQuChe(ActionEvent actionEvent) {
        ExercisesEntity exercisesEntity = exercisesService.getEntityByName(labelExercise3.getText());
        getHostServices().showDocument(exercisesEntity.getDescription());
    }

    public void acitonMoreInfoHamBi(ActionEvent actionEvent) {
        ExercisesEntity exercisesEntity = exercisesService.getEntityByName(labelExercise4.getText());
        getHostServices().showDocument(exercisesEntity.getDescription());
    }

    public void acitonMoreInfoCalBa(ActionEvent actionEvent) {
        ExercisesEntity exercisesEntity = exercisesService.getEntityByName(labelExercise5.getText());
        getHostServices().showDocument(exercisesEntity.getDescription());
    }

    public void acitonMoreInfoAbs(ActionEvent actionEvent) {
        ExercisesEntity exercisesEntity = exercisesService.getEntityByName(labelExercise6.getText());
        getHostServices().showDocument(exercisesEntity.getDescription());
    }

    public void actionCounter(ActionEvent actionEvent) {
        float weight = Integer.parseInt(textActualWeight.getText());
        float percent = (weight - userEntity.getStartWeight()) / (userEntity.getGoalWeight() - userEntity.getStartWeight());
        labelPercent.setText(String.valueOf(percent + "% -nál tartasz célod elérésében."));
    }
}
