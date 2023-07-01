package View;

import Client.*;
import Controller.LoginMenuController;
import Model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LobbyMenu extends Application {
    int index = -1;
    private ObservableList<String> availableGames;
    private ObservableList<String> usersGame;
    private Stage stage;
    private ArrayList<String> names;

    public LobbyMenu(ObservableList<String> availableGames, ObservableList<String> usersGame) {
        this.availableGames = availableGames;
        this.usersGame = usersGame;
        names = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        availableGames = FXCollections.observableArrayList();
        ListView<String> gameList = new ListView<>(availableGames);
        gameList.setPrefHeight(700);
        Button joinButton = new Button("Join");
        Button createButton = new Button("Create");
        Button refreshButton = new Button("Refresh");
        Button backButton = new Button("back");
        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-background-color: #00468B;");
        buttonBox.getChildren().addAll(joinButton, createButton, refreshButton, backButton);
        joinButton.setTranslateX(250);
        createButton.setTranslateX(550);
        refreshButton.setTranslateX(850);
        backButton.setTranslateX(1150);
        VBox lobbyBox = new VBox();
        lobbyBox.setStyle("-fx-background-color: #00468B;");
        lobbyBox.setSpacing(20);
        lobbyBox.setPadding(new Insets(10));
        Label titleLabel = new Label("Lobby");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        lobbyBox.getChildren().addAll(titleLabel, gameList, buttonBox);
        joinButton.setOnAction(event -> {
            String selectedGame = gameList.getSelectionModel().getSelectedItem();
            if (selectedGame != null) {
                try {
                    joinGame(names.get(index));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        final int[] selectedIndex = new int[1];
        gameList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedIndex[0] = gameList.getSelectionModel().getSelectedIndex();
                index = selectedIndex[0];
            }
        });
        backButton.setOnAction(event -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        createButton.setOnAction(event -> {
            Pane pane = new Pane();
            Stage stage = new Stage();
            HBox hBox = new HBox();
            VBox vBox = new VBox();
            TextField username = new TextField();
            username.setPromptText("enter username");
            TextField number = new TextField();
            number.setPromptText("enter number");
            Button createPrivetGame = new Button("create private game");
            Button createPublicGame = new Button("create public game");
            hBox.setStyle("-fx-background-color: #013220;");
            vBox.setStyle("-fx-background-color: #013220;");
            createPrivetGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        if (username.getText().equals("")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error create");
                            alert.setContentText("game name field is empty");
                            alert.show();
                        } else if (!number.getText().matches("\\d+")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error create");
                            alert.setContentText("invalid number!");
                            alert.show();
                        } else {
                            availableGames.add("Game : " + username.getText() + " Number : " + number.getText() + "  private game");
                            createNewGame(username.getText(), Integer.parseInt(number.getText()), true, stage);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            createPublicGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        if (username.getText().equals("")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error create");
                            alert.setContentText("game name field is empty");
                            alert.show();
                        } else if (!number.getText().matches("\\d+")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error create");
                            alert.setContentText("invalid number!");
                            alert.show();
                        } else {
                            availableGames.add("Game : " + username.getText() + " Number : " + number.getText() + "  private game");
                            createNewGame(username.getText(), Integer.parseInt(number.getText()), false, stage);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            vBox.setSpacing(20);
            vBox.setPadding(new Insets(10));
            hBox.getChildren().addAll(createPublicGame, createPrivetGame);
            vBox.getChildren().addAll(username, number, hBox);
            Scene scene = new Scene(vBox);
            stage.setScene(scene);
            stage.show();
        });
        refreshButton.setOnAction(event -> {
            gameList.getItems().clear();
            names.clear();
            for (int i = 0; i < Database.lobbyModels.size(); i++) {
                names.add(Database.lobbyModels.get(i).getLobbyName());
                String show = "";
                if (Database.lobbyModels.get(i).isStarted()) {
                    show += "****  ";
                }
                if (Database.lobbyModels.get(i).isPrivate()) {
                    show += "(private) ";
                } else {
                    show += "(public) ";
                }
                show += "gameName->" + Database.lobbyModels.get(i).getLobbyName();
                show += " capacity->" + Database.lobbyModels.get(i).getCapacity();
                show += "   users->";
                for (int k = 0; k < Database.lobbyModels.get(i).getUsernames().size(); k++) {
                    try {
                        show += " \"" + User.getUserByUsername(Database.lobbyModels.get(i).getUsernames().get(k)).getNickname() + "\"";
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                gameList.getItems().add(show);
            }
        });
        BorderPane root = new BorderPane();
        root.setCenter(lobbyBox);
        Scene scene = new Scene(root, 1590, 795);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private void gameLobby() {
        ListView<String> gameList = new ListView<>(usersGame);
        gameList.setPrefHeight(700);
        Button leftButton = new Button("Left");
        Button startGameButton = new Button("Start Game");
        Button refreshButton = new Button("Refresh");
        Button backButton = new Button("back");
        Button changeToPublic = new Button("change to public");
        Button changeToPrivate= new Button("change to private");
        HBox buttonBox = new HBox();
        buttonBox.setStyle("-fx-background-color: #013220;");
        buttonBox.getChildren().addAll(leftButton, startGameButton, refreshButton, backButton, changeToPublic, changeToPrivate);
        leftButton.setTranslateX(150);
        startGameButton.setTranslateX(350);
        refreshButton.setTranslateX(550);
        changeToPublic.setTranslateX(750);
        changeToPrivate.setTranslateX(950);
        backButton.setTranslateX(1150);
        VBox lobbyBox = new VBox();
        lobbyBox.setStyle("-fx-background-color: #013220;");
        lobbyBox.setSpacing(20);
        lobbyBox.setPadding(new Insets(10));
        changeToPrivate.setOnAction(event -> {
            try {
                VBox vBox = new VBox();
                TextField textField = new TextField();
                textField.setPromptText("enter password for game");
                Button button = new Button("enter");
                vBox.getChildren().addAll(textField, button);
                Scene scene = new Scene(vBox);
                Stage stage1 = new Stage();
                stage1.setScene(scene);
                stage1.show();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        changeToPublic.setOnAction(event -> {
            try {
                changePrivate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        backButton.setOnAction(event -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        leftButton.setOnAction(event -> {
            try {
                leftTheGame();
                //stage.close();
                new LobbyMenu(this.availableGames, this.usersGame).start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        startGameButton.setOnAction(event -> {
            try {
                startTheGame();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        refreshButton.setOnAction(event -> {
            gameList.getItems().clear();
            int index = giveIndex();
            for (int i = 0; i < Database.lobbyModels.get(index).getUsernames().size(); i++) {
                gameList.getItems().add(Database.lobbyModels.get(index).getUsernames().get(i));
            }
        });
        Label titleLabel = new Label("Game");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        lobbyBox.getChildren().addAll(titleLabel, gameList, buttonBox);
        Scene scene = new Scene(lobbyBox, 1590, 795);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    private int giveIndex() {
        int index = -1;
        for (int i = 0; i < Database.lobbyModels.size(); i++) {
            if (Database.lobbyModels.get(i).getUsernames().contains(LoginMenuController.getLoggedInUser().getUsername())) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void createNewGame(String gameName, int capacity, boolean isPrivate, Stage stage) throws Exception {
        if (capacity <= 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error create");
            alert.setContentText("number should be more than 1");
            alert.show();
            return;
        }
        for (int i = 0; i < Database.lobbyModels.size(); i++) {
            if (Database.lobbyModels.get(i).getLobbyName().equals(gameName)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error create");
                alert.setContentText("lobby with this name already exists");
                alert.show();
                return;
            }
        }
        if (!isPrivate) {
            ArrayList<String> user = new ArrayList<>();
            user.add(LoginMenuController.getLoggedInUser().getUsername());
            Database.lobbyModels.add(new LobbyModel(gameName, capacity, user, LoginMenuController.getLoggedInUser().getUsername(), isPrivate));
            Chat room = new Chat(user, ChatType.ROOM);
            room.setName(gameName);
            Database.chats.add(room);
            stage.close();
            gameLobby();
            Connection.handelClient("submit changes");
            Connection.handelClient("submit lobby");
        } else {
            VBox vBox = new VBox();
            TextField textField = new TextField();
            textField.setPromptText("enter password for game");
            Button button = new Button("enter");
            vBox.getChildren().addAll(textField, button);
            Scene scene = new Scene(vBox);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            button.setOnAction(event -> {
                if (textField.getText().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error create");
                    alert.setContentText("field is empty");
                    alert.show();
                } else {
                    ArrayList<String> user = new ArrayList<>();
                    user.add(LoginMenuController.getLoggedInUser().getUsername());
                    Database.lobbyModels.add(new LobbyModel(gameName, capacity, user, LoginMenuController.getLoggedInUser().getUsername(), true, textField.getText()));
                    Chat room = new Chat(user, ChatType.ROOM);
                    room.setName(gameName);
                    Database.chats.add(room);
                    stage1.close();
                    stage.close();
                    gameLobby();
                    try {
                        Connection.handelClient("submit changes");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        Connection.handelClient("submit lobby");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            stage1.show();
        }
    }

    private void joinGame(String gameName) throws Exception {
        for (int i = 0; i < Database.lobbyModels.size(); i++) {
            if (Database.lobbyModels.get(i).getLobbyName().equals(gameName)) {
                if (Database.lobbyModels.get(i).isStarted()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error join");
                    alert.setContentText("this game has already started");
                    alert.show();
                } else if (Database.lobbyModels.get(i).getCapacity() <= Database.lobbyModels.get(i).getUsernames().size()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error join");
                    alert.setContentText("no free capacity");
                    alert.show();
                } else if (Database.lobbyModels.get(i).isPrivate()) {
                    VBox vBox = new VBox();
                    TextField textField = new TextField();
                    textField.setPromptText("enter password for game");
                    Button button = new Button("enter");
                    vBox.getChildren().addAll(textField, button);
                    Scene scene = new Scene(vBox);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    int finalI = i;
                    int finalI1 = i;
                    button.setOnAction(event -> {
                        if (textField.getText().equals("")) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error join");
                            alert.setContentText("field is empty");
                            alert.show();
                        } else if (!Database.lobbyModels.get(finalI).getPassword().equals(textField.getText())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Error join");
                            alert.setContentText("password is not correct");
                            alert.show();
                        } else {
                            Database.lobbyModels.get(finalI1).getUsernames().add(LoginMenuController.getLoggedInUser().getUsername());
                            if (Database.lobbyModels.get(finalI1).getCapacity() == Database.lobbyModels.get(finalI1).getUsernames().size()) {
                                Database.lobbyModels.get(finalI1).setStarted(true);
                            }
                            for (int k = 0; k < Database.chats.size(); k++) {
                                if (Database.chats.get(k).chatType.equals(ChatType.ROOM) && Database.chats.get(k).getName().equals(gameName)) {
                                    Database.chats.get(k).usernames.add(LoginMenuController.getLoggedInUser().getUsername());
                                    stage.close();
                                    gameLobby();
                                    try {
                                        Connection.handelClient("submit changes");
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    try {
                                        Connection.handelClient("submit lobby");
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    break;
                                }
                            }
                        }
                    });
                    stage1.show();
                } else {
                    Database.lobbyModels.get(i).getUsernames().add(LoginMenuController.getLoggedInUser().getUsername());
                    if (Database.lobbyModels.get(i).getCapacity() == Database.lobbyModels.get(i).getUsernames().size()) {
                        Database.lobbyModels.get(i).setStarted(true);
                    }
                    for (int k = 0; k < Database.chats.size(); k++) {
                        if (Database.chats.get(k).chatType.equals(ChatType.ROOM) && Database.chats.get(k).getName().equals(gameName)) {
                            Database.chats.get(k).usernames.add(LoginMenuController.getLoggedInUser().getUsername());
                            stage.close();
                            gameLobby();
                            Connection.handelClient("submit changes");
                            Connection.handelClient("submit lobby");
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    private void leftTheGame() throws Exception {
        int index = -1;
        for (int i = 0; i < Database.lobbyModels.size(); i++) {
            if (Database.lobbyModels.get(i).getUsernames().contains(LoginMenuController.getLoggedInUser().getUsername())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        String gameName = Database.lobbyModels.get(index).getLobbyName();
        if (Database.lobbyModels.get(index).getUsernames().size() == 1) {
            Database.lobbyModels.remove(index);
            for (int k = 0; k < Database.chats.size(); k++) {
                if (Database.chats.get(k).chatType.equals(ChatType.ROOM) && Database.chats.get(k).getName().equals(gameName)) {
                    Database.chats.remove(k);
                    break;
                }
            }
        } else {
            if (Database.lobbyModels.get(index).getAdminUsername().equals(LoginMenuController.getLoggedInUser().getUsername())) {
                Database.lobbyModels.get(index).setAdminUsername(Database.lobbyModels.get(index).getUsernames().get(1));
            }
            Database.lobbyModels.get(index).getUsernames().remove(LoginMenuController.getLoggedInUser().getUsername());
            for (int k = 0; k < Database.chats.size(); k++) {
                if (Database.chats.get(k).chatType.equals(ChatType.ROOM) && Database.chats.get(k).getName().equals(gameName)) {
                    Database.chats.get(k).getUsernames().remove(LoginMenuController.getLoggedInUser().getUsername());
                    break;
                }
            }
        }
        Connection.handelClient("submit changes");
        Connection.handelClient("submit lobby");
    }

    private void startTheGame() throws Exception {
        int index = -1;
        for (int i = 0; i < Database.lobbyModels.size(); i++) {
            if (Database.lobbyModels.get(i).getUsernames().contains(LoginMenuController.getLoggedInUser().getUsername())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        Alert alert;
        if (!Database.lobbyModels.get(index).getAdminUsername().equals(LoginMenuController.getLoggedInUser().getUsername())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error start");
            alert.setContentText("you are not the admin of this game");
        } else if (Database.lobbyModels.get(index).getUsernames().size() == 1) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("few members!");
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirm");
            alert.setContentText("game started successfully");
            Database.lobbyModels.get(index).setStarted(true);
        }
        alert.show();
        Connection.handelClient("submit lobby");
    }

    private void changePrivate() throws Exception {
        int index = -1;
        for (int i = 0; i < Database.lobbyModels.size(); i++) {
            if (Database.lobbyModels.get(i).getUsernames().contains(LoginMenuController.getLoggedInUser().getUsername())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        Alert alert;
        if (!Database.lobbyModels.get(index).getAdminUsername().equals(LoginMenuController.getLoggedInUser().getUsername())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error change");
            alert.setContentText("you are not the admin of this game");
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirm");
            alert.setContentText("game changed to public successfully");
            Database.lobbyModels.get(index).setPrivate(false);
        }
        alert.show();
        Connection.handelClient("submit lobby");
    }

    private void makePublic() {
    }
}
