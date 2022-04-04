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

    private final double gridPaneSize;
    int[][] backGroundTextArray = new int[10][10];


    public MineSweeper() {
        this.gridPaneSize = 50;
    }


    public void setGridpane(double gridPaneSize) {
        gridPane.setPrefSize(gridPaneSize, gridPaneSize);
        gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(gridPaneSize));
    }

    public void setButtonsSize(double gridpanesize, Button button) {
        button.setPrefHeight(gridpanesize);
        button.setPrefWidth(gridpanesize);
    }

    public void setText(Text textbomb){
        textbomb.setText("💣");
        textbomb.setFont(new Font(37));
    }

    public void BackGroundTextArrayLoop(){
        for(int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                this.isPutBomb(x,y);
            }
        }
    }

    public  void isPutBomb(int line,int column){
        if(this.getRandomBomb() < 10){
            this.setRandomBackGroundTextArrayLoop(line,column);
        }
    }


    public void setRandomBackGroundTextArrayLoop(int line, int column){
            for(int k= -1 ; k < 2; k++){
                for (int l = -1 ; l < 2; l++){
                    setRandomBackGroundTextArray(line,column,k,l);
                }
            }
    }

    public void setRandomBackGroundTextArray(int line , int column,int currentLine,int currentColumn) {
        if (line + currentLine > -1 && column + currentColumn > -1 && line + currentLine < 10 && column + currentColumn < 10) {
            this.addBackGroundTextArrayint(line + currentLine, column + currentColumn);
            if (line + currentLine == line && column + currentColumn == column) {
                this.setBackGroundTextArray(line, column, 100);
            }
        }
    }

    public double getGridPaneSize() {
        return gridPaneSize;
    }

    public int getBackGroundTextArrayint(int line,int column) {
        return backGroundTextArray[line][column];
    }

    public void addBackGroundTextArrayint(int line, int column){
        this.backGroundTextArray[line][column]++;
    }

    public void setBackGroundTextArray(int line, int column,int number) {
        this.backGroundTextArray[line][column] = number;
    }

    public int getRandomBomb() {
        int randomBomb = random.nextInt(100);
        return randomBomb;
    }


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        this.BackGroundTextArrayLoop();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Text textbomb = new Text();
                Button button = new Button();
                setGridpane(gridPaneSize);
                setButtonsSize(gridPaneSize, button);
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
                button.setOnAction(actionEvent -> {
                    button.setVisible(false);
                    if (button.getId() == "BOOM") {
                        gridPane.getChildren().removeAll(gridPane.lookupAll(".button"));
                    }
                });
                gridPane.add(button,i,j);
            }
        }
        gridPane.getChildren().addAll();
        Scene scene = new Scene(gridPane, gridPaneSize * 10, gridPaneSize * 10);
        stage.setScene(scene);
        stage.show();
    }

}