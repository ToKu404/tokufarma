package tokufarma;

import javafx.application.Application;
import javafx.stage.Stage;
import tokufarma.Scenes.HomeScene;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        HomeScene homeScene = new HomeScene(stage);
        homeScene.show();
        stage.setTitle("ToKuFarma");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}