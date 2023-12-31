package com.example.flight;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private Label label_loginmessage;

    @FXML
    private Button button_login;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String loggedInUserId; // Store the logged-in user's ID

    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    public void login(ActionEvent event) throws IOException{
        String username = tf_username.getText();
        String password = tf_password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            label_loginmessage.setText("Enter Your Username And Password.");
            highlightTextField(tf_username);
            highlightTextField(tf_password);
        } else if (validateLogin(username, password)) {
            label_loginmessage.setText("Login successful!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            root = loader.load();

            HomeController homeController = loader.getController();
            homeController.displayname(username);

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            gotoHome();
        } else {
            label_loginmessage.setText("Invalid credentials.");
        }
    }



    private void highlightTextField(TextField textField) {
        textField.setStyle("-fx-border-color: red;");
    }

    private void highlightEmptyFields() {
        if (tf_username.getText().isEmpty()) {
            highlightTextField(tf_username);
        }
        if (tf_password.getText().isEmpty()) {
            highlightTextField(tf_password);
        }
    }

    private void clearFieldStyles() {
        tf_username.setStyle("");
        tf_password.setStyle("");
    }

    private boolean validateLogin(String username, String password) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Store the logged-in user's ID
                UserSession.setLoggedInUserId(rs.getInt("user_id"));
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @FXML
    private void goToRegistration() {
        try {
            // Load the registration.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Stage stage = (Stage) tf_username.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            RegistrationController registrationController = loader.getController();
            registrationController.setMainApp(mainApp);
            mainApp.showRegistration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoHome() {
        try {
            String username = tf_username.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) tf_username.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            HomeController homeController = loader.getController();
            homeController.setMainApp(mainApp);

            // Pass the logged-in user's ID to the home controller
            homeController.displayname(username);
            homeController.setLoggedInUserId(UserSession.getLoggedInUserId());

            mainApp.showHome();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoforgotpassword(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ForgotPassword.fxml"));
            Stage stage = (Stage) tf_username.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            ForgotPasswordController forgotPasswordController = loader.getController();
            forgotPasswordController.setMainApp(mainApp);
            mainApp.showForgotPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
