package View;

import Controller.AvatarMenuController;
import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Model.User;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AvatarMenu extends Application {
    AvatarMenuController avatarMenuController;

    public AvatarMenu() {
        this.avatarMenuController = new AvatarMenuController(this);
    }

    private ImageView one;
    private ImageView two;
    private ImageView three;
    private ImageView four;
    private Stage stage;
    private Button button;
    private TextField username;
    private Label error;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setResizable(false);
        this.stage= stage;
        username= new TextField();
        username.setPromptText("Enter Username");
        username.setTranslateX(125);
        username.setTranslateY(280);
        username.setStyle("-fx-background-color: #ffcccb;");
        Button enter= new Button("enter");
        enter.setTranslateX(375);
        enter.setLayoutY(280);
        enter.setStyle("-fx-background-color: #ffcccb;");
        error= new Label();
        error.setTextFill(Color.RED);
        error.setLayoutX(150);
        error.setLayoutY(350);
        button= new Button("select from file");
        button.setTranslateX(50);
        button.setTranslateY(50);
        button.setStyle("-fx-background-color: #ffcccb;");
        one= new ImageView(LoginMenu.class.getResource("/Image/Avatar/1.png").toString());
        two= new ImageView(LoginMenu.class.getResource("/Image/Avatar/2.png").toString());
        three= new ImageView(LoginMenu.class.getResource("/Image/Avatar/3.png").toString());
        four= new ImageView(LoginMenu.class.getResource("/Image/Avatar/4.png").toString());
        one.setTranslateX(80);
        one.setTranslateY(160);
        one.setFitHeight(61);
        one.setFitWidth(66);
        two.setTranslateX(218);
        two.setTranslateY(160);
        two.setFitHeight(61);
        two.setFitWidth(66);
        three.setTranslateX(344);
        three.setTranslateY(160);
        three.setFitHeight(61);
        three.setFitWidth(66);
        four.setTranslateX(463);
        four.setTranslateY(160);
        four.setFitHeight(61);
        four.setFitWidth(66);
        error.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    enter();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    fromFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        one.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    chooseAvatar1();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        two.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    chooseAvatar2();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        three.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    chooseAvatar3();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        four.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    chooseAvatar4();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
//        Image[] avatars= {one.getImage(), two.getImage(), three.getImage(), four.getImage()};
//        Data.setAvatars(avatars);
        Pane pane= new Pane();
        BackgroundFill backgroundFill= new BackgroundFill(Color.BLACK, new CornerRadii(10), new Insets(10));
        pane.setBackground(new Background(backgroundFill));
        pane.setPrefHeight(400);
        pane.setPrefWidth(600);
        pane.getChildren().addAll(one, two, three, four, button, username, enter, error);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void chooseAvatar1() throws Exception {
        avatarMenuController.chooseAvatar("/Image/Avatar/1.png");
    }

    public void chooseAvatar2() throws Exception {
        avatarMenuController.chooseAvatar("/Image/Avatar/2.png");
    }

    public void chooseAvatar3() throws Exception {
        avatarMenuController.chooseAvatar("/Image/Avatar/3.png");
    }

    public void chooseAvatar4() throws Exception {
        avatarMenuController.chooseAvatar("/Image/Avatar/4.png");
    }

    public void fromFile() throws Exception {
        avatarMenuController.fromFile(LoginMenu.stage);
    }

    public Stage getStage() {
        return stage;
    }

    public void enter() throws Exception {
        User user= User.getUserByUsername(username.getText());
        if(user == null)  error.setText("enter valid username");
        else{
            LoginMenuController.getLoggedInUser().setImage(user.getImage());
        }
    }
}
