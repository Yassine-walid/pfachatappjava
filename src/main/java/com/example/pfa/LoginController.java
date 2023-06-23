package com.example.pfa;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

import static com.example.pfa.UserAuth.login;
import static com.example.pfa.UserAuth.registerUser;

public class LoginController {
    @FXML
    private TextField txtUser,txtRegUser;

    @FXML
    private TextField txtPass,txtRegPass;
    private Parent root;
    private Scene scene;
    private Stage stage;


    public void onLogin(ActionEvent event) throws IOException {
        String username = txtUser.getText();
        String password = txtPass.getText();

        if(login(username,password) == true){
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void onRegister(ActionEvent event) throws IOException{
        String username = txtRegUser.getText();
        String password = txtRegPass.getText();

        System.out.println(username);

        if(registerUser(username,password) == true){
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
