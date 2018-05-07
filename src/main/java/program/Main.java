package program;

import program.utility.Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init(){
        Manager.getInstance();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        primaryStage.setTitle("EdzoProgram");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {

        super.stop();
        Manager.getInstance().close();

    }


    public static void main(String[] args) {
            launch(args);
    }
}
