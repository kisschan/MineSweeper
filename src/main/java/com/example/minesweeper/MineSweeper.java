package com.example.minesweeper;

import javafx.application.Application;
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


    public void setGridPane(double gridPaneSize) {
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

    public void BackGroundArrayNumberLoop(){
        for(int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                this.isPutBomb(x,y);
            }
        }
    }

    public  void isPutBomb(int line,int column){
        if(this.getRandomBomb() < 10){
            this.NearPanesArrayNumberLoop(line,column);
        }
    }


    public void NearPanesArrayNumberLoop(int line, int column){
            for(int k= -1 ; k < 2; k++){
                for (int l = -1 ; l < 2; l++){
                    BackGroundArrayNumber(line,column,k,l);
                }
            }
    }

    public void BackGroundArrayNumber(int line , int column, int currentLine, int currentColumn) {
        if (line + currentLine > -1 && column + currentColumn > -1 && line + currentLine < 10 && column + currentColumn < 10) {
            this.addBackGroundArrayNumber(line + currentLine, column + currentColumn);
            if (line + currentLine == line && column + currentColumn == column) {
                this.setBackGroundArrayNumber(line, column, 100);
            }
        }
    }

    public double getGridPaneSize() {
        return gridPaneSize;
    }

    public int getBackGroundArraysNumber(int line, int column) {
        return backGroundTextArray[line][column];
    }

    public void addBackGroundArrayNumber(int line, int column){
        this.backGroundTextArray[line][column]++;
    }

    public void setBackGroundArrayNumber(int line, int column, int number) {
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
        this.BackGroundArrayNumberLoop();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Text textbomb = new Text();
                Button button = new Button();
                setGridPane(this.getGridPaneSize());
                setButtonsSize(this.getGridPaneSize(), button);
                setText(textbomb);
                Text nearBombNum = new Text(String.valueOf(backGroundTextArray[i][j]));
                nearBombNum.setFont(new Font(37));

                if(this.getBackGroundArraysNumber(i,j) >= 100){
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