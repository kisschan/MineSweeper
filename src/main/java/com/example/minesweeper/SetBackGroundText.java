package com.example.minesweeper;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SetBackGroundText {

    private final BackGroundNumbersArrayClass backGroundNumbersArrayClass;
    private final double gridPaneSize;

    public SetBackGroundText(BackGroundNumbersArrayClass backGroundNumbersArrayClass, double gridPaneSize) {
        this.backGroundNumbersArrayClass = backGroundNumbersArrayClass;
        this.gridPaneSize = gridPaneSize;
    }

    public Text getBombText(Text bombText){
        bombText.setText("💣");
        bombText.setFont(new Font(37));
        return bombText;
    }

    public void setNearBombText(Text nearBombNum, Text notNearBombText, int line, int column){
        if(backGroundNumbersArrayClass.getBackGroundNumber(line, column) != 0){
            nearBombNum.setText(String.valueOf(backGroundNumbersArrayClass.getBackGroundNumber(line,column)));
            nearBombNum.setFont(new Font(37));
            this.setNearBombTextColor(nearBombNum, line, column);
        }else{
            this.setNotNearBombText(notNearBombText);
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
    private void setNotNearBombText(Text notNearBombText) {
        notNearBombText.setText(" ");
        notNearBombText.setFont(new Font(37));
    }


}
