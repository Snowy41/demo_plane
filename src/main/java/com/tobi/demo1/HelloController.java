package com.tobi.demo1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Calendar;

public class HelloController {
    @FXML
    private TextField first;
    @FXML
    private Label flight;
    @FXML
    private AnchorPane anchorPane;
    static ArrayList<String> testText = new ArrayList<>();
    TextField editText;
    String text1;
    public static String text;

    public static ArrayList<String> flights = new ArrayList<>();
    public static ArrayList<Label> labels = new ArrayList<>();
    int offset = 0, caseL;
    String lastLabel;
    boolean open = false;
    @FXML
    protected void onHelloButtonClick() {
        flights.add(first.getText());
        for(String flight1: flights) {
            addLabel(flight1);
        }
        offset = 0;
    }
    public void addLabel(String text) {
        caseL = 1;
        //Loops through all created labels
        for(Label l : labels) {
            if(l.getText().equalsIgnoreCase(text)) {
                //If flight is already listed
                caseL = 2;
                offset = 0;
                break;
            } else {
                //If the flight is not listed
                caseL = 1;
                //Update offset for next label
                offset += 20;
            }
        }
        //Switch-Statement in case of more than these to cases
        switch (caseL) {
            case 1: {
                this.text = text;
                //Gets information from calender for logging
                Calendar calender = Calendar.getInstance();
                int year = calender.get(Calendar.YEAR);
                int month = calender.get(Calendar.MONTH + 1);
                int day = calender.get(Calendar.DAY_OF_MONTH);
                int second = calender.get(Calendar.SECOND);
                int minute = calender.get(Calendar.MINUTE);
                int hour = calender.get(Calendar.HOUR);

                //Creates label with all infos
                Label label1 = new Label("["+ day + "." + month + "." + year + ": " + hour + "." + minute + "." + second +"]");
                Label label = new Label(text);
                Button editButton = new Button("Edit");
                Button saveButton = new Button("Save");
                //Creates action for on "edit"-button click
                editButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //Checks if the flight got a name (NullPointer)
                        if(first.getText() != null) {
                            editText = new TextField(HelloController.text);
                            editText.setLayoutY(offset);
                            editText.setLayoutX(250);
                            editText.setMaxWidth(100);
                            if(saveButton != null) {
                                saveButton.setLayoutX(350);
                                if(!anchorPane.getChildren().contains(saveButton)) {
                                    anchorPane.getChildren().add(saveButton);
                                }
                            }
                            if(!anchorPane.getChildren().contains(editText)) {
                                anchorPane.getChildren().add(editText);
                            }
                        }
                    }
                });
                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        text1 = editText.getText();
                        if(text1 != null) {
                            HelloController.text = text1;
                        }

                    }
                });
                editButton.setLayoutX(200);
                editButton.setLayoutX(200);
                label.setLayoutX(110);
                label.setLayoutY(offset);
                label1.setLayoutY(offset);
                labels.add(label);
                //Adds the label to the achnorplane so it an be displayed.
                anchorPane.getChildren().add(editButton);
                anchorPane.getChildren().add(label1);
                anchorPane.getChildren().add(label);
                break;
            }
            case 2: {
                //When the flight is already displayed, break
                break;
            }
        }

    }

}