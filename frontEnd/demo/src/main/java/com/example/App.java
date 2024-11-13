package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    // Scenes and Windows
    private Stage window;
    private Scene loginWindow, createAccountWindow, dashboardWindow, forgotPasswordWindow;

    public static void main(String[] args) {
        launch(args);
    }

    
    // Main window
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        // primary window
        window = primaryStage;

        // Load logo image
        Image logo = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(200);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);
        logoView.setSmooth(true);
        logoView.setCache(true);

        // Navigation Bar
        HBox navBar = new HBox(10); // Spacing between components
        navBar.setPadding(new Insets(10)); // Padding for the navbar
        navBar.getChildren().addAll(logoView);

        // Buttons
        Button login = new Button("Login");

        // Hyperlinks
        Hyperlink createAccount = new Hyperlink("Create Account");
        createAccount.setOnAction(e -> window.setScene(createAccountWindow));

        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setOnAction(e -> window.setScene(forgotPasswordWindow));

        // Labels for UI
        Label homePage = new Label("Welcome Sun Devil!");
        Label text1 = new Label("Please login with your credentials");
        Label text2 = new Label("New to Pitchfork Pages? Click on Create Account below");
        Label userName = new Label("Username:");
        Label password = new Label("Password:");

        // GridPane for login info
        GridPane loginInfo = new GridPane();
        loginInfo.setPadding(new Insets(10, 10, 10, 10));
        loginInfo.setVgap(8);
        loginInfo.setHgap(10);

        // TextFields for username and password
        TextField userNameInput = new TextField();
        userNameInput.setPromptText("Username");

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Password");

        // Adding action event for login button
        login.setOnAction(e -> {
            if (isString(userNameInput, "Invalid username") && isString(passwordInput, "Invalid password")) {
                // If both username and password are valid, switch to the dashboard
                System.out.println("Login successful! Username: " + userNameInput.getText());
                window.setScene(dashboardWindow); // Proceed to dashboard
            } else {
                System.out.println("Please enter valid credentials.");
            }
        });

        // Adding elements to GridPane
        GridPane.setConstraints(navBar, 0, 1);
        GridPane.setConstraints(logoView, 0, 0);
        GridPane.setConstraints(homePage, 1, 1);
        GridPane.setConstraints(text1, 0, 3);
        GridPane.setConstraints(text2, 0, 4);
        GridPane.setConstraints(userName, 0, 5);
        GridPane.setConstraints(userNameInput, 0, 6);
        GridPane.setConstraints(password, 0, 7);
        GridPane.setConstraints(passwordInput, 0, 8);
        GridPane.setConstraints(login, 0, 9);
        GridPane.setConstraints(createAccount, 0, 10);
        GridPane.setConstraints(forgotPassword, 1, 10);

        loginInfo.getChildren().addAll(navBar, logoView, homePage, text1, text2, userName, userNameInput, password, passwordInput, login, createAccount, forgotPassword);

        // Create Account Window Setup
        GridPane createAcctPane = new GridPane();
        createAcctPane.setPadding(new Insets(10, 10, 10, 10));
        createAcctPane.setVgap(8);
        createAcctPane.setHgap(10);

        Label accountText1 = new Label("Welcome Sun Devil!");
        Label accountText2 = new Label("Please enter the required information");

        Label newUserName = new Label("Please enter a valid username:");
        TextField newUserNameInput = new TextField();
        newUserNameInput.setPromptText("Username");

        Label newPassword = new Label("Please enter a valid password:");
        PasswordField newPasswordInput = new PasswordField();
        newPasswordInput.setPromptText("Password");

        Button createAccountButton = new Button("Create Account");
        createAccountButton.setOnAction(e -> {
            // Add your account creation logic here
            System.out.println("Account created for: " + newUserNameInput.getText());
            window.setScene(loginWindow); // Return to login after creating the account
        });

        // Adding elements to create account GridPane
        createAcctPane.add(accountText1, 0, 0);
        createAcctPane.add(accountText2, 0, 1);
        createAcctPane.add(newUserName, 0, 2);
        createAcctPane.add(newUserNameInput, 0, 3);
        createAcctPane.add(newPassword, 0, 4);
        createAcctPane.add(newPasswordInput, 0, 5);
        createAcctPane.add(createAccountButton, 0, 6);

        createAccountWindow = new Scene(createAcctPane, 800, 800);

        // Forgot Password Window Setup
        GridPane forgotPasswordPane = new GridPane();
        forgotPasswordPane.setPadding(new Insets(10, 10, 10, 10));
        forgotPasswordPane.setVgap(8);
        forgotPasswordPane.setHgap(10);

        Label forgotPasswordText = new Label("Please enter your email to reset your password:");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Email");

        Button resetPasswordButton = new Button("Reset Password");
        resetPasswordButton.setOnAction(e -> {
            System.out.println("Reset link sent to: " + emailInput.getText());
            window.setScene(loginWindow); // Return to login after requesting a reset
        });

        // Adding elements to forgot password GridPane
        forgotPasswordPane.add(forgotPasswordText, 0, 0);
        forgotPasswordPane.add(emailInput, 0, 1);
        forgotPasswordPane.add(resetPasswordButton, 0, 2);

        forgotPasswordWindow = new Scene(forgotPasswordPane, 800, 800);

        // Set up dashboardWindow with a simple label
        dashboardWindow = new Scene(new StackPane(new Label("Welcome to your Dashboard!")), 800, 800);

        // Set up loginWindow
        loginWindow = new Scene(loginInfo, 800, 800);

        // Default window
        window.setScene(loginWindow);
        window.setTitle("Pitchforks Library");
        window.show();
    }

    private boolean isString(TextField input, String message) {
        String value = input.getText();
        if (value != null && !value.trim().isEmpty()) {
            return true; // Valid string
        } else {
            System.out.println(message);
            return false; // Invalid string
        }
    }
}
