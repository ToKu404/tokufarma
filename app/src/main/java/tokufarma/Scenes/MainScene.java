package tokufarma.Scenes;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class MainScene {
    private Stage stage;
    private VBox rightSide;

    public MainScene(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        HBox root = new HBox();
        Scene scene = new Scene(root, 640, 480);

        VBox leftSide = generateLeftSide(scene.getWidth() * .36, scene.getHeight());
        rightSide = generateRightSide(scene.getWidth() * .64, scene.getHeight());
        changeMenu(1);

        root.getChildren().addAll(leftSide, rightSide);

        scene.getStylesheets().add(getClass().getResource("/styles/main_style.css").toExternalForm());
        stage.setScene(scene);
    }

    private void showListView() {
        rightSide.getChildren().clear();

        //observab
        ObservableList<String> listFarms = FXCollections.observableArrayList();
        listFarms.addAll("ayu", "ardi", "L");

        //menampilkan list apoteker
        ListView <String> listViewFarms = new ListView<>();

        //
        TextField tfName = new TextField();
        Button btnAdd = new Button("tAMBAH");
        Button btnRemove = new Button("HAPUS");

        btnAdd.setOnAction(v -> {
            listFarms.add(tfName.getText());
        });

        btnAdd.setOnAction(v -> {
            int index = listViewFarms.getSelectionModel().getSelectedIndex();
            listFarms.remove(index);
        });

        //pasangkan
        listViewFarms.setItems(listFarms);

        //tambah list view ke Vbox
        rightSide.getChildren().addAll(listViewFarms,tfName,btnAdd, btnRemove);

    }

    private void showTableView() {
        rightSide.getChildren().clear();
        ObservableList<ObatModelApp> listObat = FXCollections.observableArrayList();
        listObat.add(new ObatModelApp("paracetamol", "21 january",3));
        listObat.add(new ObatModelApp("Promaag", "02 maret 2023",4));
        listObat.add(new ObatModelApp("amoxilin", "13 april 2025",9));

        // implementasi
        TableView<ObatModelApp> tabelObat = new TableView<>();

        TableColumn<ObatModelApp, String> colom1 = new TableColumn<>("nama");
        TableColumn<ObatModelApp, String> colom2 = new TableColumn<>("expireDate");
        TableColumn<ObatModelApp, Integer> colom3 = new TableColumn<>("stok");

       
        colom1.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colom2.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
        colom3.setCellValueFactory(new PropertyValueFactory<>("stok"));

        //tambah column ke tabel
        tabelObat.getColumns().addAll(colom1, colom2, colom3);

        //memberi nilai
        tabelObat.setItems(listObat);

        //tampilkan ke vbox
        rightSide.getChildren().add(tabelObat);

        
    }

    private void changeMenu(int indexMenu) {
        switch (indexMenu) {
            case 1:
                showListView();
                break;
            case 2:
                showTableView();
            default:
                break;
        }
    }

    private VBox generateRightSide(double width, double height) {
        VBox vBoxLayout = new VBox();
        vBoxLayout.setPrefSize(width, height);
        vBoxLayout.setMaxSize(width, height);
        vBoxLayout.setPadding(new Insets(24));
        return vBoxLayout;
    }

    private VBox generateLeftSide(double width, double height) {
        // Left Side (MENU)
        VBox vboxMenu = new VBox();
        vboxMenu.setPrefSize(width, height);
        vboxMenu.setMaxSize(width, height);
        vboxMenu.getStyleClass().add("vbox-menu");

        // Item Title
        ImageView ivLogo = new ImageView("/images/logo.png");
        ivLogo.setFitWidth(32);
        ivLogo.setFitHeight(32);
        Text tLeft = new Text("ToKu");
        tLeft.getStyleClass().add("title-text-left");
        Text tRight = new Text("Farma");
        tRight.getStyleClass().add("title-text-right");
        TextFlow tfTitle = new TextFlow(tLeft, tRight);
        HBox hboxTitle = new HBox(ivLogo, tfTitle);
        hboxTitle.setPadding(new Insets(20));
        hboxTitle.setSpacing(12);
        Region space = new Region();
        space.setPrefHeight(12);

        // Separator
        Region spaceBetween = new Region();
        VBox.setVgrow(spaceBetween, Priority.ALWAYS);

        // Button
        ImageView ivIcon = new ImageView("/images/icon_home.png");
        Label labelMenu = new Label("Back to Home");
        labelMenu.getStyleClass().add("label-menu");
        HBox hboxHome = new HBox(ivIcon, labelMenu);
        hboxHome.setPadding(new Insets(12, 20, 24, 20));
        hboxHome.setSpacing(8);
        hboxHome.setOnMouseClicked(v -> {
            HomeScene homeScene = new HomeScene(stage);
            homeScene.show();
        });

        vboxMenu.getChildren().addAll(hboxTitle, space);
        vboxMenu.getChildren().addAll(generateMenuItem());
        vboxMenu.getChildren().addAll(spaceBetween, hboxHome);

        return vboxMenu;
    }

    private HBox[] generateMenuItem() {
        String[] listImagePath = { "/images/icon_1.png", "/images/icon_2.png" };
        String[] listTitle = { "Daftar Apoteker", "Daftar Obat" };
        HBox[] listHboxMenu = new HBox[2];

        for (int i = 0; i < listHboxMenu.length; i++) {
            ImageView ivIcon = new ImageView(listImagePath[i]);
            Label labelMenu = new Label(listTitle[i]);
            labelMenu.getStyleClass().add("label-menu");
            listHboxMenu[i] = new HBox(ivIcon, labelMenu);
            listHboxMenu[i].setPadding(new Insets(12, 20, 12, 20));
            listHboxMenu[i].setSpacing(4);
            changeMenuStatus(listHboxMenu[i], i == 0 ? true : false);
        }

        for (int i = 0; i < listHboxMenu.length; i++) {
            int index = i;
            listHboxMenu[i].setOnMouseClicked(v -> {

                changeMenuStatus(listHboxMenu[index], true);
                changeMenu(index + 1);

                for (int j = 0; j < listHboxMenu.length; j++) {
                    if (index != j) {
                        changeMenuStatus(listHboxMenu[j], false);
                    }
                }
            });
        }
        return listHboxMenu;
    }

    private void changeMenuStatus(HBox menu, boolean isActive) {
        if (isActive) {
            menu.getStyleClass().add("menu-active");
        } else {
            menu.getStyleClass().clear();
        }
    }
}
