package com.mstancl.ordermanagertool;

import com.mstancl.ordermanagertool.util.fxml.FXMLoaderManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLoaderManager.setFxmlLoader(new FXMLLoader(Main.class.getResource("mainScreen.fxml")));

        FXMLLoader fxmlLoader = FXMLoaderManager.getFxmLoader();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1020);

        stage.setTitle("Order manager");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}