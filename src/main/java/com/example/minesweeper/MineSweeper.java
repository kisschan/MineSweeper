package com.example.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;

public class MineSweeper extends Application {

    GridPane gridPane = new GridPane();
    Random random = new Random();

    private final double gridPaneSize;
    int[][] backGroundTextArray = new int[10][10];


    public MineSweeper() {
        this.gridPaneSize = 50;
    }


    public void backGroundArrayNumberLoop(){
        for(int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                this.isPutBomb(x,y);
            }
        }
    }

    public  void isPutBomb(int line,int column){
        if(this.getRandomBomb() < 10){
            this.nearPanesArrayNumberLoop(line,column);
        }
    }


    public void nearPanesArrayNumberLoop(int line, int column){
            for(int k= -1 ; k < 2; k++){
                for (int l = -1 ; l < 2; l++){
                    this.backGroundArrayNumber(line,column,k,l);
                }
            }
    }

    public void backGroundArrayNumber(int line , int column, int currentLine, int currentColumn) {
        if (line + currentLine > -1 && column + currentColumn > -1 && line + currentLine < 10 && column + currentColumn < 10) {
            this.addBackGroundArrayNumber(line + currentLine, column + currentColumn);
            this.isSetBombPane(line,column,currentLine,currentColumn);
        }
    }

    public void isSetBombPane(int line, int column, int currentLine, int currentColumn){
        if (line + currentLine == line && column + currentColumn == column) {
            this.setBackGroundArrayNumber(line, column, 100);
        }
    }

    public void addBackGroundArrayNumber(int line, int column){
        this.backGroundTextArray[line][column]++;
    }

    public int getBackGroundArraysNumber(int line, int column) {
        return backGroundTextArray[line][column];
    }

    public void setBackGroundArrayNumber(int line, int column, int number) {
        this.backGroundTextArray[line][column] = number;
    }

    public int getRandomBomb() {
        return random.nextInt(100);
    }

    public double getGridPaneSize() {
        return gridPaneSize;
    }

    public void setGridPaneSize(double gridPaneSize) {
        gridPane.setPrefSize(gridPaneSize, gridPaneSize);
        gridPane.setGridLinesVisible(true);
        gridPane.getColumnConstraints().add(new ColumnConstraints(gridPaneSize));
    }

    public void setButtonsSize(double gridPaneSize, Button button) {
        button.setPrefHeight(gridPaneSize);
        button.setPrefWidth(gridPaneSize);
    }

    public void setBombText(Text bombText){
        bombText.setText("💣");
        bombText.setFont(new Font(37));
    }

    public void setNearBombText(Text nearBombNum, int line, int column){
        nearBombNum.setText(String.valueOf(this.getBackGroundArraysNumber(line,column)));
        nearBombNum.setFont(new Font(37));
    }

    public void setGridPane(int line,int column, Text bombText, Text nearBombNumberText,Button button){
        if(this.getBackGroundArraysNumber(line,column) >= 100){
            gridPane.add(bombText,line,column);
            button.setId("BOOM");
        }else{
            gridPane.add(nearBombNumberText,line,column);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) {
        this.backGroundArrayNumberLoop();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Text bombText = new Text();
                Text nearBombNumberText = new Text();
                Button button = new Button();
                this.setGridPaneSize(this.getGridPaneSize());
                this.setButtonsSize(this.getGridPaneSize(), button);
                this.setBombText(bombText);
                this.setNearBombText(nearBombNumberText,i,j);
                this.setGridPane(i,j,bombText,nearBombNumberText,button);

                button.setText(String.valueOf(i)+ j);
                button.setOnAction(actionEvent -> {
                    button.setVisible(false);
                    if (Objects.equals(button.getId(), "BOOM")) {
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