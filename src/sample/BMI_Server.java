package sample;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BMI_Server extends Stage {


    AnchorPane pane;
    Socket socket;
    boolean pressed = false;

    public void create() {
        pane = new AnchorPane();

        Thread thread = new Thread(() -> {
            try {
                ServerSocket ss = new ServerSocket(6666);
                while (true) {
                    if (!pressed) {
                        socket = ss.accept();
                    }
                    pressed = true;

                    Scanner input = new Scanner(socket.getInputStream());
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                    String line = input.nextLine();
                    String[] args = line.split("\\s+");
                    System.out.println(args[0]);

                    BMI person = new BMI(args[0], Double.parseDouble(args[1]), Double.parseDouble(args[2]));
                    output.println(person.getStatus());


                }
            } catch (Throwable e) {
                e.printStackTrace();
            }

        });
        thread.start();


        this.setTitle("Server Side");
        this.setScene(new Scene(pane, 700, 700));
        this.show();
    }
}
