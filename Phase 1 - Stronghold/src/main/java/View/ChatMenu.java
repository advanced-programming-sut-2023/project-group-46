package View;

import Client.*;
import Controller.LoginMenuController;
import Model.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ChatMenu extends Application {
    static ListView<String> chatArea;
    static ListView<String> chatMenu;
    static ArrayList<Integer> indexes;
    static ObservableList<String> avatars = FXCollections.observableArrayList();
    static ListView<String> avatarListView;
    TextField messageField;
    int selectChatId = -1;
    int selectedMessageId = -1;

    public static void refresh() throws IOException, ClassNotFoundException {
        // Connection.handelClient("refresh");
        chatMenu.getItems().clear();
        indexes.clear();
        for (int i = 0; i < Database.chats.size(); i++) {
            if (Database.chats.get(i).usernames.contains(LoginMenuController.getLoggedInUser().getUsername())) {
                indexes.add(i);
                if (Database.chats.get(i).chatType.equals(ChatType.PRIVATE_CHAT)) {
                    if (Database.chats.get(i).usernames.get(0).equals(LoginMenuController.getLoggedInUser().getUsername())) {
                        chatMenu.getItems().add(Database.chats.get(i).usernames.get(1));
                    } else {
                        chatMenu.getItems().add(Database.chats.get(i).usernames.get(0));
                    }
                } else if (Database.chats.get(i).chatType.equals(ChatType.PUBLIC_CHAT)) {
                    chatMenu.getItems().add("Global chat");
                } else {
                    chatMenu.getItems().add(Database.chats.get(i).getName());//TODO add after merging!
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        indexes = new ArrayList<>();
        BorderPane root = new BorderPane();
        primaryStage.setMaximized(true);
        root.setPrefSize(800, 600);

        // Top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setStyle("-fx-background-color: #0088cc;");
        topBar.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Chat Menu");
        titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        topBar.getChildren().add(titleLabel);
        root.setTop(topBar);

        // Chat menu
        chatMenu = new ListView<>();
        root.setLeft(chatMenu);
        final int[] selectedIndex1 = new int[1];
        chatMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedIndex1[0] = chatMenu.getSelectionModel().getSelectedIndex();
                selectChatId = indexes.get(selectedIndex1[0]);
                chatArea.getItems().clear();
                avatarListView.getItems().clear();
                try {
                    refresh();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0; i < Database.chats.get(selectChatId).messages.size(); i++) {
                    if (!Database.chats.get(selectChatId).messages.get(i).seenUsernames.contains(LoginMenuController.getLoggedInUser().getUsername())) {
                        Database.chats.get(selectChatId).messages.get(i).seenUsernames.add(LoginMenuController.getLoggedInUser().getUsername());
                    }
                    chatArea.getItems().add("* " + Database.chats.get(selectChatId).messages.get(i).senderUsername + " : " + Database.chats.get(selectChatId).messages.get(i).payam + "(" + Database.chats.get(selectChatId).messages.get(i).time + ")");
                    try {
                        System.out.println("**" + User.getUserByUsername(Database.chats.get(selectChatId).messages.get(i).senderUsername).getImage());
                        avatarListView.getItems().add(User.getUserByUsername(Database.chats.get(selectChatId).messages.get(i).senderUsername).getImage());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    submit();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });
        // Chat area
        avatarListView = new ListView<>(avatars);
        avatarListView.setCellFactory(param -> new AvatarListCell());
        avatarListView.getItems().clear();
        chatArea = new ListView<>();
        chatArea.setStyle("-fx-background-color: #f5f5f5;");
        // Add chat messages or components here
        root.setRight(avatarListView);
        root.setCenter(chatArea);
        final int[] selectedIndex = new int[1];
        chatArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedIndex[0] = chatArea.getSelectionModel().getSelectedIndex();
                selectedMessageId = selectedIndex[0];
                if (selectedIndex[0] > -1) {
                    try {
                        readyForEditMessage(selectedIndex[0]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        //System.out.println(selectedIndex[0]);
        // Bottom input area
        HBox inputArea = new HBox();
        inputArea.setPadding(new Insets(10));
        inputArea.setSpacing(10);
        inputArea.setStyle("-fx-background-color: #0088cc;");
        messageField = new TextField();
        messageField.setTranslateX(250);
        messageField.setPromptText("Type your message...");
        Button refresh = new Button("refresh");
        Button submit = new Button("submit");
        refresh.setTranslateX(850);
        submit.setTranslateX(900);
        Button enter = new Button("enter");
        enter.setTranslateX(800);
        Button newPrivateChat = new Button("new private chat");
        Button newRoom = new Button("new room");
        newRoom.setTranslateX(-70);
        newPrivateChat.setTranslateX(-300);
        inputArea.getChildren().addAll(messageField, enter, newRoom, newPrivateChat, refresh, submit);
        root.setBottom(inputArea);

        enter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    enter();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    submit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    refresh();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        newPrivateChat.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    newPrivateChat();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        newRoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    newRoom();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scene");
        primaryStage.show();
    }

    private void readyForEditMessage(int messageIndex) throws Exception {
        Pane pane = new Pane();
        Stage stage = new Stage();
        Button button1 = new Button(":)");
        button1.setTranslateX(50);
        button1.setTranslateY(50);
        Button button2 = new Button(":(");
        button2.setTranslateX(100);
        button2.setTranslateY(50);
        Button button3 = new Button(":*");
        button3.setTranslateX(150);
        button3.setTranslateY(50);
        Button button4 = new Button(":|");
        button4.setTranslateX(200);
        button4.setTranslateY(50);
        TextField textField = new TextField();
        textField.setPromptText("type your edited message here");
        textField.setTranslateX(50);
        textField.setTranslateY(100);
        Button button = new Button("enter");
        button.setTranslateX(200);
        button.setTranslateY(100);
        Button remove = new Button("remove");
        remove.setTranslateX(150);
        remove.setTranslateY(20);
        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (!Database.chats.get(selectChatId).messages.get(selectedMessageId).senderUsername.equals(LoginMenuController.getLoggedInUser().getUsername())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error access");
                        alert.setContentText("you are not the sender of this message");
                        alert.show();
                    } else {
                        Database.chats.get(selectChatId).messages.get(selectedMessageId).setDeleted(true);
                        submit();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Database.chats.get(selectChatId).messages.get(selectedMessageId).reaction.add(new Reaction(ReactionType.LAUGH, LoginMenuController.getLoggedInUser().getUsername()));
                    submit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Database.chats.get(selectChatId).messages.get(selectedMessageId).reaction.add(new Reaction(ReactionType.SAD, LoginMenuController.getLoggedInUser().getUsername()));
                    submit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Database.chats.get(selectChatId).messages.get(selectedMessageId).reaction.add(new Reaction(ReactionType.KISS, LoginMenuController.getLoggedInUser().getUsername()));
                    submit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Database.chats.get(selectChatId).messages.get(selectedMessageId).reaction.add(new Reaction(ReactionType.POKER, LoginMenuController.getLoggedInUser().getUsername()));
                    submit();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (!Database.chats.get(selectChatId).messages.get(selectedMessageId).senderUsername.equals(LoginMenuController.getLoggedInUser().getUsername())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Error access");
                        alert.setContentText("you are not the sender of this message");
                        alert.show();
                    } else {
                        Database.chats.get(selectChatId).messages.get(selectedMessageId).setPayam(textField.getText());
                        submit();
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < Database.chats.get(selectChatId).messages.get(messageIndex).seenUsernames.size(); i++) {
            arrayList.add(Database.chats.get(selectChatId).messages.get(messageIndex).seenUsernames.get(i));
            for (int k = 0; k < Database.chats.get(selectChatId).messages.get(messageIndex).reaction.size(); k++) {
                if (Database.chats.get(selectChatId).messages.get(messageIndex).reaction.get(k).getUsername().equals(Database.chats.get(selectChatId).messages.get(messageIndex).seenUsernames.get(i))) {
                    String react = "";
                    if (Database.chats.get(selectChatId).messages.get(messageIndex).reaction.get(k).getReaction().equals(ReactionType.LAUGH)) {
                        react = ":)";
                    } else if (Database.chats.get(selectChatId).messages.get(messageIndex).reaction.get(k).getReaction().equals(ReactionType.SAD)) {
                        react = ":(";
                    } else if (Database.chats.get(selectChatId).messages.get(messageIndex).reaction.get(k).getReaction().equals(ReactionType.KISS)) {
                        react = ":*";
                    } else if (Database.chats.get(selectChatId).messages.get(messageIndex).reaction.get(k).getReaction().equals(ReactionType.POKER)) {
                        react = ":|";
                    }
                    arrayList.set(arrayList.size() - 1, arrayList.get(arrayList.size() - 1) + " " + react);
                }
            }
            String online;
            if (User.getUserByUsername(Database.chats.get(selectChatId).messages.get(messageIndex).seenUsernames.get(i)).getLastSeenTime().equals("")) {
                online = "online";
            } else {
                online = User.getUserByUsername(Database.chats.get(selectChatId).messages.get(messageIndex).seenUsernames.get(i)).getLastSeenTime();
            }
            arrayList.set(arrayList.size() - 1, arrayList.get(arrayList.size() - 1) + " " + "(" + online + ")");
        }
        ListView<String> listView = new ListView<>();
        listView.setStyle("-fx-background-color: #f5f5f5;");
        listView.setMaxHeight(75);
        listView.getItems().addAll(arrayList);
        listView.setTranslateX(25);
        listView.setTranslateY(150);
        pane.getChildren().addAll(button1, button2, button3, button4, textField, button, listView, remove);
        Scene scene = new Scene(pane, 300, 230);
        stage.setScene(scene);
        stage.show();
    }

    private void enter() throws Exception {
        if (!messageField.getText().equals("")) {
            if (selectChatId == -1) {
                return;
            }
            Database.chats.get(selectChatId).messages.add(new Message(LoginMenuController.getLoggedInUser().getUsername(), messageField.getText()));
            submit();
        }
        messageField.setText("");
    }

    private void submit() throws Exception {
        Connection.handelClient("submit changes");
    }

    private void newRoom() {
        Stage stage = new Stage();
        Pane pane = new Pane();

        TextField roomName = new TextField();
        roomName.setPromptText("enter name of room");
        roomName.setTranslateX(90);
        roomName.setTranslateY(40);
        Button okButton = new Button("OK");
        okButton.setTranslateX(140);
        okButton.setTranslateY(120);

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (Objects.equals(roomName.getText(), "")) {
                    } else {
                        newRoom1(roomName.getText());
                        stage.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        pane.getChildren().addAll(roomName, okButton);

        Scene scene = new Scene(pane, 300, 230);
        stage.setScene(scene);
        stage.show();
    }

    private void newRoom1(String roomName) {
        final int[] numberOfUsersInTheRoom = {1};
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(LoginMenuController.getLoggedInUser().getUsername());
        Chat room = new Chat(usernames, ChatType.ROOM);
        room.setName(roomName);
        Stage stage = new Stage();
        Pane pane = new Pane();
        TextField privateChatUsername = new TextField();
        privateChatUsername.setPromptText("enter username");
        privateChatUsername.setTranslateX(90);
        privateChatUsername.setTranslateY(50);

        Button okButton = new Button("OK");
        okButton.setTranslateX(140);
        okButton.setTranslateY(160);

        Label error = new Label();
        error.setTextFill(Color.RED);
        error.setLayoutX(60);
        error.setLayoutY(190);

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (numberOfUsersInTheRoom[0] == 1) {
                        error.setText("you should add at least two users to the room");
                    } else {
                        error.setText("");
                        Database.chats.add(room);
                        submit();
                        stage.close();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        Button privateChatButton = new Button("add");
        privateChatButton.setTranslateX(140);
        privateChatButton.setTranslateY(120);


        privateChatButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    boolean userExists = false;
                    if (User.getUserByUsername(privateChatUsername.getText()) != null)
                        userExists = true;

                    if (!userExists)
                        error.setText("The user doesn't exist!");

                    else {
                        error.setText("");
                        numberOfUsersInTheRoom[0]++;
                        if (numberOfUsersInTheRoom[0] >= 2) {
                            room.usernames.add(privateChatUsername.getText());
                            privateChatUsername.setText("");
                        }
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


        pane.getChildren().addAll(okButton, privateChatButton, privateChatUsername, error);
        Scene scene = new Scene(pane, 300, 230);
        stage.setScene(scene);
        stage.show();
    }

    private void newPrivateChat() {
        Stage stage = new Stage();
        Pane pane = new Pane();
        TextField privateChatUsername = new TextField();
        privateChatUsername.setPromptText("enter username");
        privateChatUsername.setTranslateX(90);
        privateChatUsername.setTranslateY(50);
        Button privateChatButton = new Button("enter");
        privateChatButton.setTranslateX(140);
        privateChatButton.setTranslateY(120);
        Label error = new Label();
        error.setTextFill(Color.RED);
        error.setLayoutX(100);
        error.setLayoutY(190);
        privateChatButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    if (User.getUserByUsername(privateChatUsername.getText()) == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("this user doesn't exits");
                        alert.setContentText("this user doesn't exits");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("success");
                        alert.setHeaderText("ok");
                        alert.setContentText("chat is now available!");
                        alert.show();
                        makeNewPrivateChat(privateChatUsername.getText());
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().addAll(privateChatButton, privateChatUsername, error);
        Scene scene = new Scene(pane, 300, 230);
        stage.setScene(scene);
        stage.show();
    }

    private void makeNewPrivateChat(String username) throws Exception {
        ArrayList<String> usernames = new ArrayList<>();
        usernames.add(username);
        usernames.add(LoginMenuController.getLoggedInUser().getUsername());
        Database.chats.add(new Chat(usernames, ChatType.PRIVATE_CHAT));
        submit();
    }

    private class AvatarListCell extends javafx.scene.control.ListCell<String> {

        private final ImageView imageView = new ImageView();

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {
                //  System.out.println(item);
                Image image = new Image(LoginMenu.class.getResource(item).toExternalForm());
                imageView.setImage(image);
                imageView.setFitWidth(18);
                imageView.setFitHeight(18);
                setGraphic(imageView);
            }
        }
    }
}
