package View;

import Client.Connection;
import Controller.LoginMenuController;
import Controller.ProfileMenuController;
import Model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class ProfileMenu extends Application {
    public static ImageView avatar;
    private ProfileMenuController profileMenuController;
    @FXML
    private TextField username;
    private PasswordField password;
    @FXML
    private TextField nickname;
    @FXML
    private TextField email;
    @FXML
    private TextField slogan;
    @FXML
    private Label error;
    @FXML
    private CheckBox removeSlogan;
    private String answerUsername;
    private String answerNickname;
    private String answerEmail;
    private String answerSlogan;
    private int numberInScoreboard;
    private ObservableList<String> friendRequests = FXCollections.observableArrayList();
    private ListView<String> requestListView;
    private ObservableList<String> friends = FXCollections.observableArrayList();

    public ProfileMenu() {
        this.profileMenuController = new ProfileMenuController(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMaximized(true);
        Pane pane = FXMLLoader.load(new URL(SignupMenu.class.getResource("/FXML/ProfileMenu.fxml").toExternalForm()));
        Paint paint = new ImagePattern(new Image(LoginMenu.class.getResource("/Image/LoginMenu.PNG").openStream()));
        BackgroundFill backgroundFill = new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY);
        pane.setBackground(new Background(backgroundFill));
        stage.getScene().setRoot(pane);
        stage.show();
    }

    @FXML
    public void initialize() throws Exception {
        username.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerUsername = profileMenuController.username();
                error.setText(answerUsername);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        nickname.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerNickname = profileMenuController.nickname();
                error.setText(answerNickname);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        email.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerEmail = profileMenuController.email();
                error.setText(answerEmail);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        slogan.textProperty().addListener((observable, oldText, newText) -> {
            try {
                answerSlogan = profileMenuController.slogan();
                error.setText(answerSlogan);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        removeSlogan.selectedProperty().addListener((observable, oldText, newText) -> {
            try {
                error.setText(profileMenuController.removeSlogan());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

//    public void scoreboard() throws Exception {
//        error.setText(profileMenuController.scoreboard());
//    }

    public TextField getNickname() {
        return nickname;
    }

    public TextField getEmail() {
        return email;
    }

    public TextField getSlogan() {
        return slogan;
    }

    public void back() throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public String getAnswerUsername() {
        return answerUsername;
    }

    public String getAnswerNickname() {
        return answerNickname;
    }

    public String getAnswerEmail() {
        return answerEmail;
    }

    public String getAnswerSlogan() {
        return answerSlogan;
    }

    public void changeUsername(MouseEvent mouseEvent) throws Exception {
        String username = profileMenuController.changeUsername();
        if (username.equals("Success")) {
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        } else error.setText(username);
    }

    public void changeNickname(MouseEvent mouseEvent) throws Exception {
        String nickname = profileMenuController.changeNickname();
        if (nickname.equals("Success")) {
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        } else error.setText(nickname);
    }

    public void changeEmail(MouseEvent mouseEvent) throws Exception {
        String email = profileMenuController.changeEmail();
        if (email.equals("Success")) {
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        } else error.setText(email);
    }

    public void changeSlogan(MouseEvent mouseEvent) throws Exception {
        String slogan = profileMenuController.changeSlogan();
        if (slogan.equals("Success")) {
            Label label = new Label("Success");
            label.setTextFill(Color.GREEN);
            label.setFont(new Font(20));
            Popup popup = new Popup();
            popup.getContent().add(label);
            popup.show(LoginMenu.stage);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                popup.hide();
            }));
            timeline.setCycleCount(1);
            timeline.play();
        } else error.setText(slogan);
    }

    public void changePassword() throws Exception {
        ChangePassword changePassword = new ChangePassword();
        changePassword.start(new Stage());
    }

    public void scoreboard() throws Exception {
        Pane pane = new Pane();
        Stage stage = new Stage();
        ListView<String> listView = new ListView<>();
        VBox vbox = new VBox(listView);
        ObservableList<String> items = FXCollections.observableArrayList(profileMenuController.scoreboard());
        listView.setItems(items);
        final int[] selectedIndex = new int[1];
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    selectedIndex[0] = listView.getSelectionModel().getSelectedIndex();
                    Pane pane = new Pane();
                    Stage stage = new Stage();
                    ImageView imageView = new ImageView(new Image(LoginMenu.class.getResource(LoginMenuController.getLoggedInUser().getImage()).toExternalForm()));
                    Button friend = new Button("add to friend");
                    friend.setTranslateX(60);
                    friend.setTranslateY(180);
                    friend.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            try {

                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    pane.getChildren().addAll(imageView, friend);
                    Scene scene = new Scene(pane, 200, 200);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Scene scene = new Scene(vbox, 300, 230);
        stage.setScene(scene);
        stage.show();
    }

    public void showUsername() {
        Label label = new Label(profileMenuController.showProfileInfo());
        Pane pane = new Pane(label);
        pane.setPrefHeight(150);
        pane.setPrefWidth(400);
        avatar = new ImageView();
        avatar.setX(350);
        avatar.setY(10);
        avatar.setFitHeight(44);
        avatar.setFitWidth(47);
        String string = LoginMenuController.getLoggedInUser().getImage();
        avatar.setImage(new Image(LoginMenu.class.getResource(string).toExternalForm()));
        pane.getChildren().add(avatar);
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void friends() throws Exception {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Friendship App");
        System.out.println(LoginMenuController.getLoggedInUser().friendRequests.size() + "//");
        friendRequests.setAll(User.getUserByUsername(LoginMenuController.getLoggedInUser().getUsername()).friendRequests);
        System.out.println(LoginMenuController.getLoggedInUser().getUsername() + "###");
        requestListView = new ListView<>(friendRequests);
        requestListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button acceptButton = new Button("Accept");
        acceptButton.setOnAction(e -> {
            try {
                acceptRequest();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Button denyButton = new Button("Deny");
        denyButton.setOnAction(e -> {
            try {
                denyRequest();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        Button friendsList = new Button("list of friendsOfUser");
        friendsList.setOnAction(e -> {
            try {
                friendsList(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        HBox buttonsBox = new HBox(10);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.getChildren().addAll(acceptButton, denyButton, friendsList);
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(10));
        mainBox.getChildren().addAll(requestListView, buttonsBox);
        BorderPane layout = new BorderPane();
        layout.setCenter(mainBox);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void acceptRequest() throws Exception {
        String selectedRequest = requestListView.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            if (friends.size() == 100) return;
            friendRequests.remove(selectedRequest);
            ProfileMenuController.removeFriendRequest(selectedRequest);
            Connection.handelClient("submit users");
            ProfileMenuController.addFriend(selectedRequest);
            Connection.handelClient("submit users");
            ProfileMenuController.addLoggedInUserToSelectedUsersFriendList(selectedRequest);
            Connection.handelClient("submit users");
            friends.add(selectedRequest);
        }
    }

    private void denyRequest() throws Exception {
        String selectedRequest = requestListView.getSelectionModel().getSelectedItem();
        if (selectedRequest != null) {
            friendRequests.remove(selectedRequest);
            ProfileMenuController.removeFriendRequest(selectedRequest);
            Connection.handelClient("submit users");
        }
    }

    private void friendsList(Stage stage) throws Exception {
        friends.setAll(User.getUserByUsername(LoginMenuController.getLoggedInUser().getUsername()).friendsOfUser);
        ListView<String> listView = new ListView<>(friends);
        Button addButton = new Button("back");
        addButton.setOnAction(e -> {
            try {
                backToFriendMenu(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(listView, addButton);
        BorderPane layout = new BorderPane();
        layout.setCenter(vbox);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void backToFriendMenu(Stage stage) throws Exception {
        stage.close();
        friends();
    }

    public void changeAvatar() throws Exception {
        new AvatarMenu().start(new Stage());
    }
}