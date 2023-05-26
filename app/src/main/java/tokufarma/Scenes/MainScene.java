package tokufarma.Scenes;

import java.sql.SQLException;
import java.util.List;

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
import tokufarma.dao.ObatDao;
import tokufarma.models.Obat;

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

        //observable list
        ObservableList<String> listPharmas = FXCollections.observableArrayList();
        listPharmas.addAll("Tom Hanks", "G-Dragon", "Uzumaki Naruto", "Xin Jinping");

        //menampilkan list apoteker
        ListView<String> listViewPharmas = new ListView<>();
        //pasangkan
        listViewPharmas.setItems(listPharmas);

        //input
        TextField tfName = new TextField();
        Button btnAdd = new Button("Tambah");
        Button btnRemove = new Button("Hapus");

        //tambah apoteker
        btnAdd.setOnAction(v -> {
            listPharmas.add(tfName.getText());
        });

        //hapus apoteker
        btnRemove.setOnAction(v -> {
            int index = listViewPharmas.getSelectionModel().getSelectedIndex();
            listPharmas.remove(index);
        });

        //tambah listview ke vbox
        rightSide.getChildren().addAll(listViewPharmas, tfName, btnAdd, btnRemove);
    }

    private void showTableView() {
        rightSide.getChildren().clear();
        //buat observable list
        ObservableList<Obat> listObat = FXCollections.observableArrayList();

        try {
            ObatDao obatDao = new ObatDao();
            listObat.addAll(obatDao.getAll());
        } catch (SQLException e) {

            e.printStackTrace();
        }

        listObat.add(new Obat("Paracetamol", "2 Januari 2003", 3));
        listObat.add(new Obat("Bisolvon", "12 Maret 2007", 2));
        listObat.add(new Obat("Morphin", "25 Desember 2005", 30));

        TableView<Obat> tableObat = new TableView<>();
        TableColumn<Obat, String> column1 = new TableColumn<>("Nama");
        TableColumn<Obat, String> column2 = new TableColumn<>("Tanggal Kadaluarsa");
        TableColumn<Obat, String> column3 = new TableColumn<>("Stock");

        //pasangkan
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
        column3.setCellValueFactory(new PropertyValueFactory<>("stock"));


        column1.setPrefWidth(rightSide.getWidth() - 60 / 3);
        column2.setPrefWidth(rightSide.getWidth() - 60 / 3 + 10);
        column3.setPrefWidth(rightSide.getWidth() - 60 / 3);

        //tambah kolom ke table
        tableObat.getColumns().addAll(column1, column2, column3);

        //kasi nilai
        tableObat.setItems(listObat);

        TextField tfName = new TextField();
        tfName.setPromptText("Nama Obat");
        TextField tfExpiredDate = new TextField();
        tfExpiredDate.setPromptText("Tanggal Kadaluarsa");
        TextField tfStock = new TextField();
        tfStock.setPromptText("Stok");
        HBox hbox = new HBox(tfName, tfExpiredDate, tfStock);

        Button btnAdd = new Button("Tambah");
        btnAdd.setOnAction(v -> {
            listObat.add(new Obat(tfName.getText(), tfExpiredDate.getText(), Integer.parseInt(tfStock.getText())));
        });

        // Tampilkan di VBOX
        rightSide.getChildren().addAll(tableObat, hbox, btnAdd);

        rightSide.getChildren().add(tableObat);
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
