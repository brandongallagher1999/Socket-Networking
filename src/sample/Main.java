package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{


    BMI_Server server = new BMI_Server();
    BMI_Client client = new BMI_Client();
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        server.create();
        client.create();
        /*

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        */

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
