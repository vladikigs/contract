package com.client.contract.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;

public class MainWindowsController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getResource("/sample.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/css/checkBoxStyle.css");
        primaryStage.setTitle("Contracts viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
