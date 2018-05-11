package program.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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


    private UserEntity userEntity;
    private UserService userService = new UserServiceImpl(new UserDAOImpl(Manager.getInstance()));
    private ExercisesService exercisesService = new ExercisesServiceImpl(new ExercisesDAOImpl(Manager.getInstance()));
    private Stage dialogStage = new Stage();


    @FXML
    private void initialize() {
        String name = UserServiceImpl.getUser();
        userEntity = userService.isRegistered(name);
        labelNumberOfDay.setText(userEntity.getDays() + ". day");

        whichday();
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
            weightExercise2.setText(String.valueOf(userEntity.getCalvesw()));
            weightExercise3.setText(String.valueOf(userEntity.getBicepsw()));
            weightExercise4.setText(String.valueOf(userEntity.getBackw()));
            weightExercise5.setText(String.valueOf(userEntity.getAbsUpw()));
        }

       /* ExercisesEntity exercisesEntity = exercisesService.getEntityByName(String.valueOf(labelExercise1));
        exerciseType1.setText(exercisesEntity.getType());*/

        List<Label> repslabel = Arrays.asList(repsAmount1,repsAmount2,repsAmount3,repsAmount4,repsAmount5);

        if(userEntity.getType().equals("Weight Loss")){
            for(int labels = 0; labels < 5; labels++) {
                repslabel.get(labels).setText("4 x 14");
            }
        } else {
            for(int labels = 0; labels < 5; labels++) {
                repslabel.get(labels).setText("3 x 10");
            }
        }
    }

    public void actionNextDay(ActionEvent actionEvent) {
        userEntity.setDays(userEntity.getDays() + 1);
        sceneSwitch(dialogStage, "mainpage", actionEvent);
    }

    public void actionSetDay(ActionEvent actionEvent) {
        sceneSwitch(dialogStage, "setmainpage", actionEvent);
    }
}

/**
 * A MAINPAGE:
 * <p>
 * Arra gondoltam, hogy hagyom a francba azt a 4 osztályt a model typesban mert nem tudom hogy hogy lehetne értelmesen szétosztani
 * a dolgokat. Jelenleg ezt szeretném megcsinálni itt:
 * - wichdaybe belépve frissítse az egész oldalt:
 * - ha láb nap:
 * állítsa be hogy láb nap van (labelDay)
 * állítsa be a bodypartok labeljeit(glutes,..) és a szetteket (4x14, 3x10)
 * - ha férfi és weightLoss:
 * állítsa be a gyakorlatok listáját (choiceboxok) (ExercisesService táblám, ahol van négy oszlop ami szabályozza ezt + jó lenne ha a nehézzel jelöltek pirosak lennének)
 * ha ezt beálította, ez alapján állítsa be, hogy mekkora legyen a súly(weightExercise) és az milyen típusú (pld: kg)
 * - ha letelt xy idő (Exercise tábla timeja) akkor növelje a súlyokat ha szükséges (Exercise tábla stepjei)
 * - ha férfi és cutting:...
 * - ha nő és weightLoss:...
 * - ha nő és cutting:...
 * - ha felsőtest nap:
 * ...
 * <p>
 * <p>
 * <p>
 * Na hát igen..ez így nagyon ronda, szóval kérnék pár ötletet, hogy ezt mégis, hogy? Hogyan legyenek osztályaim, stb?
 * Továbbá:
 * - hogy az fenébe rakjak a choiceboxba cuccokat? nem csinálja meg, akárhogy próbálkozom
 * <p>
 * - Majd az achivementsbe szeretnék rakni egy kis cuki csíkot ami jelzi hogy haladok a fogyással (már bekérem őket ),
 * de nincsenek szabályozva, mert egyelőre nem azok a legfontosabbak, de érdekelne, hogy arra milyen megoldást ajánlanál?
 * Nekem legegyszerűbbnek az tűnik, ha lemásolom a pokémonos életerőcsíkod. Vagy tudsz szebb, de egyszerű dolgot rá?
 * <p>
 * - hogyan állítgassam a gyakorlat kezdősúlyát szerinted? arra gondoltam lehetne egy új page-t csinálni ami bármikor
 * elérhető egy gombbal a mainpagen és kiválaszthatod a gyakorlatot amit szeretnél csinálni és beállíthatod hozzá a súlyt is.
 * Kb lemásolnánk ezt úgy ahogy van, csak kicserélném a labeleket textfieldre. Így a rendes mainpagen meg már csak
 * labelek lennének meg a könnyű-nehéz-pontjó beállítás. (így ha hülye értéket dobna is át tudod magadnak írni az előbb említett pagen)
 * Nekem jó ötletnek tűnik, csak kicsit sok meló..de így logikusabbnak tűnik nekem, mint mindent egy helyre nyomni
 * TEHÁT:
 * újpage ugyanez lenne mint a mostani, csak:
 * - alapértelmezetten az a day lesz, ami a mostanin is, nem lesz csak mégse és mentés gombja
 * - nem lesznek: nehézséget megadó choicebox, szett beállítás (4x14)
 * - a súly pedig textfield lesz
 * (itt lesz tehát az egész ifes szar amit írtam fent, az első belépésnél pedig automatikusan ide ugraszt a program)
 * a mostnai pedig így alakul:
 * - a gyakorlatok kiválasztása már nem lesz lehetséges, csak sima label lesz
 * a táblákban így fog alakulni:
 * - csinálni kell a usernek oszlopokat: az összes bodypart (gyakorlat mentése), az összes bodyparthoz súly
 * (súly mentése, majd későbbi változtatása steppel)
 * <p>
 * Undorító lesz a user táblám, de így -1 tábla
 * Mit gondolsz? Rossz ötlet?
 * <p>
 * <p>
 * Egyelőre nem jut eszembe több
 */