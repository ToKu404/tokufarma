package tokufarma.Scenes;

import java.sql.SQLException;

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
import tokufarma.Dao.ObatDao;


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
        
        //observasi list menampung
        ObservableList<String> listPharmacist = FXCollections.observableArrayList();
        listPharmacist.addAll("armin", "chigiri", "akaashi", "nanami");

        //Menampilkan List Apoteker
        ListView<String> listViewPharmacist = new ListView<>();
        //pasangkan 
        listViewPharmacist.setItems(listPharmacist);

        TextField tfName = new TextField();
        Button btnAdd = new Button("Tambah");
        Button btnRemove = new Button("Hapus");

        btnAdd.setOnAction(v -> {
            listPharmacist.add(tfName.getText());
        });
    
        btnAdd.setOnAction(v -> {
            int index = listViewPharmacist.getSelectionModel().getSelectedIndex();
            listPharmacist.remove(index);
        });


        //tambah list view vbox
        rightSide.getChildren().addAll(listViewPharmacist, tfName, btnAdd, btnRemove);
    }

    private void showTableView() {
        rightSide.getChildren().clear();
        
        //observasi list menampung
        ObservableList<ObatModel> listObat = FXCollections.observableArrayList();

        ObatDao obatDao = new ObatDao();
        try {
            listObat.addAll(obatDao.getAll());
        } catch (SQLException e) {

            e.printStackTrace();
        }
        listObat.add(new ObatModel ("Clobazam", "12 juni 2024", 9));
        listObat.add(new ObatModel ("Amoxilin", "18 agustus 2025", 100));
        listObat.add(new ObatModel ("Ibuprofen", "6 juni 2023", 50));


        //Menampilkan tabel Obat
        TableView<ObatModel> tabelObat = new TableView<>();

    
        TableColumn<ObatModel, String> colom1 = new TableColumn<>("Nama");
        TableColumn<ObatModel, String> colom2 = new TableColumn<>("Expired Date");
        TableColumn<ObatModel, Integer> colom3 = new TableColumn<>("Stock");

        colom1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colom2.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
        colom3.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //tambah kolom ke table
        tabelObat.getColumns().addAll(colom1, colom2, colom3);

        //beri nilai
        tabelObat.setItems(listObat);

        TextField tfName = new TextField();
        tfName.setPromptText("Nama Obat");
        TextField tfExpiredDate = new TextField();
        tfExpiredDate.setPromptText("Tanggal Kadaluarsa");
        TextField tfStock = new TextField();
        tfStock.setPromptText("Stok");
        HBox hbox = new HBox(tfName, tfExpiredDate, tfStock);

        Button btnAdd = new Button("Tambah");
        btnAdd.setOnAction(v -> {
            listObat.add(new ObatModel(tfName.getText(), tfExpiredDate.getText(), Integer.parseInt(tfStock.getText())));
        });

        // Tampilkan di VBOX
        rightSide.getChildren().addAll(tabelObat, hbox, btnAdd);

        //tambahkan ke vbox
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
