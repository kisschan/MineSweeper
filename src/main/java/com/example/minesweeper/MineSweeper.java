package com.example.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Random;

public class MineSweeper extends Application {

	GridPane gridPane = new GridPane();
	Random random = new Random();

	private final double gridPaneSize;
	BackGroundNumbersArrayClass backGroundNumbersArrayClass = new BackGroundNumbersArrayClass(new int[10][10]);


	public MineSweeper() {
		this.gridPaneSize = 50;
	}


	public void backGroundNumbersArrayLoop(){
		for(int x = 0; x < 10; x++){
			for (int y = 0; y < 10; y++){
				this.isPutBomb(x,y);
			}
		}
	}

	public  void isPutBomb(int line,int column){
		if(this.getRandomBomb() < 10){
			this.nearBombPanesBackGroundNumbersLoop(line,column);
		}
	}


	public void nearBombPanesBackGroundNumbersLoop(int line, int column){
			for(int k= -1 ; k < 2; k++){
				for (int l = -1 ; l < 2; l++){
					this.backGroundNumbers(line,column,k,l);
				}
			}
	}

	public void backGroundNumbers(int line , int column, int currentLine, int currentColumn) {
		if (line + currentLine > -1 && column + currentColumn > -1 && line + currentLine < 10 && column + currentColumn < 10) {
			backGroundNumbersArrayClass.addBackGroundNumber(line + currentLine, column + currentColumn);
			this.isSetBombPane(line,column,currentLine,currentColumn);
		}
	}

	public void isSetBombPane(int line, int column, int currentLine, int currentColumn){
		if (line + currentLine == line && column + currentColumn == column) {
			backGroundNumbersArrayClass.setBackGroundNumber(line, column, 100);
		}
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
		if(backGroundNumbersArrayClass.getBackGroundNumber(line, column) != 0){
			nearBombNum.setText(String.valueOf(backGroundNumbersArrayClass.getBackGroundNumber(line,column)));
			nearBombNum.setFont(new Font(37));
			this.setNearBombTextColor(nearBombNum, line, column);
		}
	}

	public void setNearBombTextColor(Text nearBombNum, int line, int column){
		if(backGroundNumbersArrayClass.getBackGroundNumber(line,column) == 1){
			nearBombNum.setFill(Color.BLUE);
		}else if(backGroundNumbersArrayClass.getBackGroundNumber(line, column) == 2){
			nearBombNum.setFill(Color.GREEN);
		}else if(backGroundNumbersArrayClass.getBackGroundNumber(line, column) == 3){
			nearBombNum.setFill(Color.YELLOW);
		}else if(backGroundNumbersArrayClass.getBackGroundNumber(line, column) >= 4){
			nearBombNum.setFill(Color.RED);
		}
	}

	public void setGridPane(int line,int column, Text bombText, Text nearBombNumberText,Button button){
		if(backGroundNumbersArrayClass.getBackGroundNumber(line,column) >= 100){
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
		this.backGroundNumbersArrayLoop();
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