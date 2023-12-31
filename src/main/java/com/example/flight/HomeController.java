package com.example.flight;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    @FXML
    private Label tf_Home;

    @FXML
    private Label label_msg;
    @FXML
    private ChoiceBox<String> leavechoicebox;
    private String[] leavecity = {"Mumbai", "Delhi", "Chennai", "Jaipur"};
    @FXML
    private ChoiceBox<String> destinationchoicebox;
    private String[] departcity = {"Mumbai", "Delhi", "Chennai", "Jaipur"};

    @FXML
    private MenuButton BookingPage;
    @FXML
    private MenuItem MB_Action1;

    @FXML
    private MenuItem MB_Action2;

    @FXML
    private Button Button_Search;


    @FXML
    private MenuItem MB_TicketCancel;
    @FXML
    private ImageView Menu;

    @FXML
    private ImageView MenuBack;

    @FXML
    private ImageView Image_Home;
    @FXML
    private DatePicker datebox;

    @FXML
    private Button Button_gotobook;

    @FXML
    private VBox slider;

    @FXML
    private Label usernamecopy;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void displayname(String username) {
        usernamecopy.setText(username);
    }


    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    private Integer loggedInUserId;

    public void setLoggedInUserId(Integer userId) {
        loggedInUserId = userId;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image(Objects.requireNonNull(getClass().getResource("/Images/HomePageImage.jpg")).toString());
        Image_Home.setImage(image);

        Image image1 = new Image(Objects.requireNonNull(getClass().getResource("/Images/Menu.jpg")).toString());
        Menu.setImage(image1);

        Image image2 = new Image(Objects.requireNonNull(getClass().getResource("/Images/Menu.jpg")).toString());
        MenuBack.setImage(image2);


//        String leave = leavechoicebox.getItems().toString();
//        String destination = destinationchoicebox.getItems().toString();
//
//        if (leave.equals(destination)) {
//            label_msg.setText("Same");
//        }


        leavechoicebox.getItems().addAll(leavecity);
        destinationchoicebox.getItems().addAll(departcity);

        slider.setTranslateX(-176);
        Menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();

            slide.setDuration(Duration.seconds(0.4));

            slide.setNode(slider);

            slide.setToX(0);

            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) ->
            {
                Menu.setVisible(false);
                MenuBack.setVisible(true);

            });

        });
        MenuBack.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();

            slide.setDuration(Duration.seconds(0.4));

            slide.setNode(slider);

            slide.setToX(-176);

            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) ->
            {
                Menu.setVisible(true);
                MenuBack.setVisible(false);

            });

        });
    }

    @FXML
    private void GoToBookingPage(ActionEvent event) {
        try {
            // Load the registration.fxml file

            String username = label_msg.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ticketbooking.fxml"));
            Stage stage = (Stage) tf_Home.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            HomeController homeController = loader.getController();
            homeController.setLoggedInUserId(loggedInUserId);
            homeController.displayname(username);


            homeController.setMainApp(mainApp);
            mainApp.showBookingPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoToBookingCancelPage() {
        try {
            String username = usernamecopy.getText();

            // Load the registration.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookingcancel.fxml"));
            Stage stage = (Stage) tf_Home.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            HomeController homeController = loader.getController();
            homeController.displayname(username);
            homeController.setLoggedInUserId(loggedInUserId);
            homeController.setMainApp(mainApp);
            mainApp.showCancelPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToUpdatePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateprofile.fxml"));
            Stage stage = (Stage) tf_Home.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            HomeController homeController = loader.getController();
            homeController.setLoggedInUserId(loggedInUserId);

            homeController.setMainApp(mainApp);
            mainApp.showUpdatePage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Stage stage = (Stage) tf_Home.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            HomeController homeController = loader.getController();
            homeController.setMainApp(mainApp);
            mainApp.showLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoToFlightDetails() {
        try {
            String username = usernamecopy.getText();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("t.fxml")); // Update the FXML filename
            Stage stage = (Stage) usernamecopy.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the "Flight Details" page
            TicketDetailsController ticketDetailsController = loader.getController();
            ticketDetailsController.setLoggedInUserId(Integer.parseInt(String.valueOf(loggedInUserId))); // You may need to implement this method in FlightDetailsController
            ticketDetailsController.displayname(username); // You may need to implement this method in FlightDetailsController

            ticketDetailsController.setMainApp(mainApp);
            mainApp.showflightdetails(); // Make sure this method exists in your Main class
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void Search(ActionEvent event) throws IOException {
        String leave = leavechoicebox.getValue();
        String destination = destinationchoicebox.getValue();
        LocalDate date = datebox.getValue();

//        String leave = leavechoicebox.getItems().toString();
//        String destination = destinationchoicebox.getItems().toString();
        if (leave.equals(destination)) {
            label_msg.setText("Leave And Destination Are Same!");
        }
        else {

            String username = label_msg.getText();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("ticketbooking.fxml"));
            root = loader.load();

            TicketBookingController ticketBookingController = loader.getController();
            ticketBookingController.displayleave(leave);
            ticketBookingController.displaydestination(destination);
            LocalDate selectedDate = datebox.getValue();
            ticketBookingController.displaydate(selectedDate);
            ticketBookingController.displayname(username);


            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void GoToFlightStatus() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightStatus.fxml"));
            Stage stage = (Stage) tf_Home.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            HomeController homeController = loader.getController();
            homeController.setMainApp(mainApp);
            mainApp.showflightstatus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gotoHome() {
        try {
            String username = usernamecopy.getText();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) usernamecopy.getScene().getWindow();
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
    private void GotoFeedback() {
        try {
            // Load the registration.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Feedback.fxml"));
            Stage stage = (Stage) tf_Home.getScene().getWindow();
            stage.getScene().setRoot(loader.load());

            // Set the controller for the registration page
            FeedbackController feedbackController = loader.getController();
            feedbackController.setMainApp(mainApp);
            feedbackController.setLoggedInUserId(UserSession.getLoggedInUserId());

            mainApp.showfeedback();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

