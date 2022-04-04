package com.example.minesweeper;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class MineSweeper extends Application {

    GridPane gridPane = new GridPane();
    Random random = new Random();

    private final double gridpanesize;
    int[][] backGroundTextArray = new int[10][10];

    public MineSweeper() {
        this.gridpanesize = 50;
    }


    public void setGridpane(double gridpanesize) {
        gridPane.setPrefSize(gridpanesize, gridpanesize);
        gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(gridpanesize));
    }

    public void setButtonsSize(double gridpanesize, Button button) {
        button.setPrefHeight(gridpanesize);
        button.setPrefWidth(gridpanesize);
    }

    public void setText(Text textbomb){
        textbomb.setText("💣");
        textbomb.setFont(new Font(37));
    }

    public void setRandomBackGroundTextArray(){
        for(int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                int randomBomb = random.nextInt(100);
                if (randomBomb <= 10) {
                    for(int k= -1 ; k < 2; k++){
                        for (int l = -1 ; l < 2; l++){
                            if(x+k > -1 && y+l > -1 && x+k <10 && y+l <10) {
                                backGroundTextArray[x + k][y + l]++;
                                if(x+k == x && y+l == y){
                                    backGroundTextArray[x][y] = 100;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        setRandomBackGroundTextArray();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Text textbomb = new Text();
                Button button = new Button();
                setGridpane(gridpanesize);
                setButtonsSize(gridpanesize, button);
                setText(textbomb);
                Text nearBombNum = new Text(String.valueOf(backGroundTextArray[i][j]));
                nearBombNum.setFont(new Font(37));

                if(backGroundTextArray[i][j] >= 100){
                    gridPane.add(textbomb,i,j);
                    button.setId("BOOM");
                }else{
                    gridPane.add(nearBombNum,i,j);
                }
                button.setText(String.valueOf(i)+ j);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        button.setVisible(false);
                        if (button.getId() == "BOOM") {
                            gridPane.getChildren().removeAll(gridPane.lookupAll(".button"));
                        }
                    }
                });
                gridPane.add(button,i,j);
            }
        }
        gridPane.getChildren().addAll();
        Scene scene = new Scene(gridPane, gridpanesize * 10, gridpanesize * 10);
        stage.setScene(scene);
        stage.show();
    }

}