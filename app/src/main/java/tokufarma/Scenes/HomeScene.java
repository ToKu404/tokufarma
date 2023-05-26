package tokufarma.Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class HomeScene {
    private Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/home_style.css").toExternalForm());

        // Setting background
        ImageView ivBackground = new ImageView("/images/bg.png");
        ivBackground.setFitWidth(scene.getWidth());
        ivBackground.setFitHeight(scene.getHeight());
        spLayout.getChildren().add(ivBackground);

        // Text Title
        Text tLeft = new Text("ToKu");
        tLeft.getStyleClass().add("title-text-left");
        Text tRight = new Text("Farma");
        tRight.getStyleClass().add("title-text-right");
        TextFlow tfTitle = new TextFlow(tLeft, tRight);

        // Top Logo
        ImageView ivLogo = new ImageView("/images/logo.png");

        // Text Desc
        Label lblDesc = new Label(
                "Dapatkan obat berkualitas dengan layanan terbaik hanya di toko obat kami! Temukan beragam pilihan obat yang lengkap dan tersedia dengan harga kompetitif.");
        lblDesc.getStyleClass().add("desc-text");
        lblDesc.setWrapText(true);
        lblDesc.setMaxWidth(355);

        // Button Explore
        Region space = new Region();
        space.setPrefHeight(12);
        Button btnExplore = new Button("Explore");
        btnExplore.getStyleClass().add("btn-explore");

        // VBOX layout
        VBox vLayout = new VBox(ivLogo, tfTitle, lblDesc, space, btnExplore);
        vLayout.setSpacing(8);
        spLayout.getChildren().add(vLayout);
        vLayout.setPadding(new Insets(53));
        vLayout.setAlignment(Pos.CENTER_LEFT);

        // actions
        btnExplore.setOnAction(v -> {
            MainScene mainScene = new MainScene(stage);
            mainScene.show();
        });

        stage.setScene(scene);
    }
}
