package view;

import controller.CurrencyController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Currency;

public class CurrencyView extends Application {

    private CurrencyController controller;

    @Override
    public void init() {
        controller = new CurrencyController();
    }

    @Override
    public void start(Stage stage) {
        Label titleLabel = new Label("Currency Converter");
        titleLabel.getStyleClass().add("title-label");

        Label instructionLabel = new Label(
                "Enter an amount, choose source and target currencies, then click Convert."
        );
        instructionLabel.setWrapText(true);

        Label amountLabel = new Label("Amount:");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        Label fromLabel = new Label("From Currency:");
        ComboBox<Currency> fromBox = new ComboBox<>();
        fromBox.getItems().addAll(controller.getCurrencies());

        Label toLabel = new Label("To Currency:");
        ComboBox<Currency> toBox = new ComboBox<>();
        toBox.getItems().addAll(controller.getCurrencies());

        Label resultLabel = new Label("Converted Amount:");
        TextField resultField = new TextField();
        resultField.setEditable(false);
        resultField.setPromptText("Result will appear here");

        Button convertButton = new Button("Convert");

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");

        // Default selections
        fromBox.getSelectionModel().selectFirst();
        toBox.getSelectionModel().select(1);

        // Prevent invalid input: allow only numbers and optional decimal point
        amountField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        }));

        convertButton.setOnAction(event -> {
            try {
                String input = amountField.getText();

                if (input == null || input.isEmpty()) {
                    messageLabel.setText("Please enter an amount.");
                    resultField.clear();
                    return;
                }

                double amount = Double.parseDouble(input);

                if (amount < 0) {
                    messageLabel.setText("Amount cannot be negative.");
                    resultField.clear();
                    return;
                }

                Currency source = fromBox.getValue();
                Currency target = toBox.getValue();

                if (source == null || target == null) {
                    messageLabel.setText("Please select both currencies.");
                    resultField.clear();
                    return;
                }

                double result = controller.convert(amount, source, target);
                resultField.setText(String.format("%.2f", result));
                messageLabel.setText("Conversion completed successfully.");

            } catch (NumberFormatException e) {
                messageLabel.setText("Invalid number format.");
                resultField.clear();
            } catch (Exception e) {
                messageLabel.setText("An unexpected error occurred.");
                resultField.clear();
            }
        });

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20));
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setAlignment(Pos.CENTER);

        layout.add(titleLabel, 0, 0, 2, 1);
        layout.add(instructionLabel, 0, 1, 2, 1);

        layout.add(amountLabel, 0, 2);
        layout.add(amountField, 1, 2);

        layout.add(fromLabel, 0, 3);
        layout.add(fromBox, 1, 3);

        layout.add(toLabel, 0, 4);
        layout.add(toBox, 1, 4);

        layout.add(convertButton, 1, 5);

        layout.add(resultLabel, 0, 6);
        layout.add(resultField, 1, 6);

        layout.add(messageLabel, 0, 7, 2, 1);

        Scene scene = new Scene(layout, 500, 350);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        stage.setTitle("Currency Converter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}