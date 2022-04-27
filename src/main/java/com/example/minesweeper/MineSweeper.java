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

public class MineSweeper extends Application {

	GridPane gridPane = new GridPane();

	BackGroundNumbersArrayClass backGroundNumbersArrayClass = new BackGroundNumbersArrayClass(new int[10][10]);
	SetLoop setLoop = new SetLoop(new IsSetBomb(this.getBackGroundNumbersArrayClass()));
	private final double gridPaneSize;
	private SetBackGroundText setBackGroundText = new SetBackGroundText(this.getBackGroundNumbersArrayClass(), this.getGridPaneSize());


	public MineSweeper() {
		this.gridPaneSize = 50;
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



	public void setGridPane(int line,int column, Text bombText, Text nearBombNumberText,Text notNearBombText, Button button){
		if(backGroundNumbersArrayClass.getBackGroundNumber(line,column) >= 100){
			gridPane.add(bombText,line,column);
			button.setId("BOOM");
		}else if(backGroundNumbersArrayClass.getBackGroundNumber(line,column) > 0){
			gridPane.add(nearBombNumberText,line,column);
		}else{
			gridPane.add(notNearBombText,line,column);
		}
	}

	public BackGroundNumbersArrayClass getBackGroundNumbersArrayClass() {
		return backGroundNumbersArrayClass;
	}


	public static void main(String[] args) {
		launch(args);
	}



	@Override
	public void start(Stage stage) {
		setLoop.backGroundNumbersArrayLoop();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Text bombText = new Text();
				Text nearBombNumberText = new Text();
				Text notNearBombText = new Text();
				Button button = new Button();
				this.setGridPaneSize(this.getGridPaneSize());
				this.setButtonsSize(this.getGridPaneSize(), button);
				setBackGroundText.setNearBombText(nearBombNumberText,notNearBombText,i,j);
				setBackGroundText.getBombText(bombText);
				this.setGridPane(i,j,bombText,nearBombNumberText,notNearBombText,button);
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