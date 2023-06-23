package com.example.pfa;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    ComboBox<String> serverCombo = new ComboBox<>();
    @FXML
    Circle circleCol;

    public void initialize() {
        serverCombo.getItems().add("General");
        serverCombo.getItems().add("Games");

    }



    @FXML
    private ListView<String> listMsgs;
    PrintWriter pw;
    @FXML
    private TextField txtMessage;
    @FXML
    protected void connectToServer () throws Exception {
        String host = null;
        int port = 0;
        String choix = serverCombo.getValue();
        System.out.println(choix);
        if(choix.equals("General")){
            host = "localhost";
            port =1234;
        } else if (choix.equals("Games")) {
            host = "localhost";
            port =1212;
        }
        Socket s = new Socket(host,port);
        InputStream is = s.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        OutputStream os = s.getOutputStream();
        String Ip = s.getRemoteSocketAddress().toString();
        pw = new PrintWriter(os,true);
        circleCol.setFill(Color.GREEN);

        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true){
                    try {
                        String reponse  = br.readLine();
                        Platform.runLater(()->{
                            listMsgs.getItems().add(reponse);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }

    @FXML
    public void onSubmit(){
        String  message = txtMessage.getText();
        pw.println(message);
    }
}

