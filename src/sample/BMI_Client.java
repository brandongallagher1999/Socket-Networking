package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BMI_Client extends Stage {
    Socket socket;
    Button sendBMI;
    TextField field;
    TextArea textArea;


    public void create() {
        textArea = new TextArea();
        textArea.setLayoutX(100);
        textArea.setLayoutY(400);
        field = new TextField();
        field.setLayoutX(100);
        field.setLayoutY(100);
        sendBMI = new Button("Send BMI");
        sendBMI.setLayoutX(200);
        sendBMI.setLayoutY(200);

        Thread thread = new Thread(() -> {
            try {
                socket = new Socket("localhost", 6666);
                sendBMI.setOnAction(e ->
                {
                    System.out.println("pressed");
                    try {
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        writer.println(field.getText());
                        Scanner scanner = new Scanner(socket.getInputStream());
                        String line = scanner.nextLine();
                        textArea.appendText(line);


                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                });
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
        thread.start();

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(sendBMI, field, textArea);

        this.setTitle("Client Side");
        this.setScene(new Scene(pane, 700, 700));
        this.show();


    }

}
